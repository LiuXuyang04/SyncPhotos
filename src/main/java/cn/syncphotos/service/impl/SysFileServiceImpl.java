package cn.syncphotos.service.impl;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.syncphotos.constant.CommonConstants;
import cn.syncphotos.entity.FileExif;
import cn.syncphotos.entity.SysFile;
import cn.syncphotos.mapper.SysFileMapper;
import cn.syncphotos.service.FileExifService;
import cn.syncphotos.service.SysFileService;
import cn.syncphotos.vo.FileInfoVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements SysFileService {

    private final SysFileMapper sysFileMapper;
    private final FileExifService fileExifService;

    @Override
    @Transactional
    @SneakyThrows
    public boolean saveFile(MultipartFile file, String suffix, SysFile sysFile) {
        long id = IdUtil.getSnowflakeNextId();
        sysFile.setName(id + "." + suffix);
        sysFile.setMd5hex(DigestUtil.md5Hex(file.getInputStream()));
        Assert.isTrue(this.save(sysFile), "服务器内部错误，请重试！");
        String path = StrFormatter.format(CommonConstants.FILEPATH, sysFile.getId(), suffix);
        FileWriter writer = new FileWriter(path);
        writer.writeFromStream(file.getInputStream());
        FileExif imageMetaData = null;
        if (sysFile.getFileType().equals("0")) {
            //图片解析方法
            imageMetaData = fileExifService.getImageMetaData(file.getInputStream());
            imageMetaData.setFileId(sysFile.getId());
        } else {
            //视频解析方法
            imageMetaData = fileExifService.getVideoMetaData(file.getInputStream());
            imageMetaData.setFileId(sysFile.getId());
        }
        fileExifService.save(imageMetaData);
        return true;
    }

    @Override
    public IPage<FileInfoVO> findPage(Page page) {
        return sysFileMapper.findPage(page);
    }

}
