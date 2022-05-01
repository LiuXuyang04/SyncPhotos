package cn.syncphotos.service;

import cn.syncphotos.entity.SysFile;
import cn.syncphotos.vo.FileInfoVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

public interface SysFileService extends IService<SysFile> {

    /**
     * 保存文件
     *
     * @param sysFile
     * @return
     */
    boolean saveFile(MultipartFile file, String suffix, SysFile sysFile);

    IPage<FileInfoVO> findPage(Page page);

}
