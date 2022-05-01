package cn.syncphotos;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import cn.syncphotos.entity.FileExif;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SyncPhotosApplicationTests {
    @Test
    void test() throws FileNotFoundException {
        String path2 = "/Users/liuxuyang/Movies/Wondershare UniConverter13/Recorded/凤凰卫视元宇宙.mp4";
        String path3 = "/Users/liuxuyang/Downloads/IMG_6212.MOV";
        FileInputStream fis = new FileInputStream(path2);
        TimeInterval timeInterval = new TimeInterval();
        String s = DigestUtil.md5Hex(fis);
        System.out.println(timeInterval.interval());
        System.out.println(s);
        fis = new FileInputStream(path3);
        timeInterval.start();
        s = DigestUtil.md5Hex(fis);
        System.out.println(timeInterval.interval());
    }
    //5f47215348b57765e177edea6e2d54df
    //1a7d53b2abb5cd5747d402558a285241
    @Test
    void contextLoads() throws ImageProcessingException, IOException {
        /**
         * 压缩
         */
        String path = "/Users/liuxuyang/IdeaProjects/SyncPhotos/target/classes/SyncPhotos/1520406402865115137.jpg";
        /**
         * 相机
         */
        String path2 = "/Users/liuxuyang/Pictures/IMG_4824.jpg";
        /**
         * 壁纸
         */
        String path3 = "/Users/liuxuyang/Pictures/wallhaven-j3dmdm.jpeg";

        String path4 = "/Users/liuxuyang/Desktop/宋玉艺术馆/游龙仙子杯.tif";
        Console.log("压缩图片");
        //getFileMetaData(path);
        Console.log("手机图片");
        getFileMetaData(path2);
        Console.log("Adobe图片");
        getFileMetaData(path3);
        Console.log("专业相机图片");
        getFileMetaData(path4);


    }

    void getFileMetaData(String path) throws IOException, ImageProcessingException {
        TimeInterval timeInterval = new TimeInterval();
        Map<String, String> tagList = new HashMap<>();
        FileInputStream fis = new FileInputStream(path);
        Metadata metadata = ImageMetadataReader.readMetadata(fis);
        metadata.getDirectories().forEach(directory -> {
            directory.getTags().forEach(tag -> {
                tagList.put(tag.getTagName(), tag.getDescription());
            });
        });
        FileExif fileExif = new FileExif();
        fileExif.setCamera(tagList.get("Model") != null ? tagList.get("Model") : tagList.get("Software"));
        fileExif.setResolution(trimGPSStr(tagList.get("Image Width")) + "x" + trimGPSStr(tagList.get("Image Height")));
        fileExif.setLatitude(tagList.get("GPS Latitude"));
        fileExif.setLongitude(tagList.get("GPS Longitude"));
        if (ObjectUtil.isNotNull(tagList.get("Date/Time"))) {
            fileExif.setOriginTime(DateUtil.parse(tagList.get("Date/Time"), "yyyy:MM:dd HH:mm:ss"));
        } else {
            fileExif.setOriginTime(DateUtil.date());
        }
        Console.log("执行总耗时 : " + timeInterval.interval());
        Console.log(fileExif.toString());
    }

    String trimGPSStr(String gpsStr) {
        return gpsStr.replaceAll(" pixels", "").trim();
    }


}
