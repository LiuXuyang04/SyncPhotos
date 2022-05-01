package cn.syncphotos.service;

import cn.syncphotos.entity.FileExif;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;

public interface FileExifService extends IService<FileExif> {
    /**
     * 根据文件获取元数据
     * @return
     */
    FileExif getImageMetaData(InputStream stream);


    /**
     * 根据文件获取元数据
     * @return
     */
    FileExif getVideoMetaData(InputStream stream);
}
