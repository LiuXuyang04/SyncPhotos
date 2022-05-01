package cn.syncphotos.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.syncphotos.entity.FileExif;
import cn.syncphotos.mapper.FileExifMapper;
import cn.syncphotos.service.FileExifService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileExifServiceImpl extends ServiceImpl<FileExifMapper, FileExif> implements FileExifService {

    @Override
    @SneakyThrows
    public FileExif getImageMetaData(InputStream stream) {
        Map<String, String> tagList = new HashMap<>();
        Metadata metadata = ImageMetadataReader.readMetadata(stream);
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
//        System.out.println(tagList.size());
//        System.out.println(tagList.get("Image Width"));
//        System.out.println(tagList.get("Image Height"));
//        System.out.println(tagList.get("Date/Time"));
//        System.out.println(tagList.get("GPS Latitude"));
//        System.out.println(tagList.get("GPS Longitude"));
//        System.out.println(tagList.get("Model"));
//        System.out.println(tagList.get("Software"));
        return fileExif;
    }

    @Override
    @SneakyThrows
    public FileExif getVideoMetaData(InputStream stream) {
        Map<String, String> tagList = new HashMap<>();
        Metadata metadata = ImageMetadataReader.readMetadata(stream);
        metadata.getDirectories().forEach(directory -> {
            directory.getTags().forEach(tag -> {
                tagList.put(tag.getTagName(), tag.getDescription());
            });
        });
        FileExif fileExif = new FileExif();
        fileExif.setCamera(tagList.get("Model") != null ? tagList.get("Model") : tagList.get("Software"));
        fileExif.setResolution(trimGPSStr(tagList.get("Width")) + "x" + trimGPSStr(tagList.get("Height")));
        fileExif.setOriginTime(DateUtil.date());
        fileExif.setDuration(tagList.get("Duration in Seconds"));
        return fileExif;
    }

    /**
     * 移除分辨率单位
     *
     * @param gpsStr
     * @return
     */
    private String trimGPSStr(String gpsStr) {
        return gpsStr.replaceAll(" pixels", "").trim();
    }

}
