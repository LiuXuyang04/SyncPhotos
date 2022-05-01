package cn.syncphotos.api;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.lang.Assert;
import cn.syncphotos.entity.SysFile;
import cn.syncphotos.service.SysFileService;
import cn.syncphotos.utils.R;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final SysFileService sysFileService;

    @PostMapping
    @SneakyThrows
    public R save(@RequestPart MultipartFile file) {
        Assert.isTrue(file.getSize() > 0, "上传文件不能为空！");
        String suffix = FileTypeUtil.getType(file.getInputStream(), file.getOriginalFilename());
        String contentType = file.getContentType();
        SysFile sysFile = new SysFile();
        if (contentType.contains("image")) {
            sysFile.setFileType("0");
        } else if (contentType.contains("video")) {
            sysFile.setFileType("1");
        } else {
            throw new IllegalArgumentException("只支持上传图片和视频文件！");
        }
        sysFile.setFileSize(file.getSize());
        sysFile.setOriginalName(file.getOriginalFilename());
        return R.ok(sysFileService.saveFile(file, suffix, sysFile));
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Long id) {
        return R.ok(id);
    }


    @GetMapping("/page")
    public R getPage(Page<SysFile> page) {
        return R.ok(sysFileService.findPage(page));
    }

}
