package cn.syncphotos.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("file")
public class SysFile implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 文件现在名字
     */
    private String name;
    /**
     * 文件原名
     */
    private String originalName;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 文件类型 { 0-图片，1-视频 }
     */
    private String fileType;

    /**
     * 文件exif文件状态
     */
    private String md5hex;

    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;

}
