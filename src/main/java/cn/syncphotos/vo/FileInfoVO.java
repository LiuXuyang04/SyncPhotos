package cn.syncphotos.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FileInfoVO implements Serializable {
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
     * 时长
     */
    private String duration;

    /**
     * 经度
     */
    private String latitude;

    /**
     * 纬度
     */
    private String longitude;

    /**
     * 相机
     */
    private String camera;

    /**
     * 分辨率
     */
    private String resolution;

    private Date originTime;
}
