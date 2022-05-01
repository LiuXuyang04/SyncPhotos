package cn.syncphotos.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("file_exif")
public class FileExif implements Serializable {

    /**
     * 文件ID
     */
    @TableId
    private Long fileId;

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
