package cn.syncphotos;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

@Slf4j
@SpringBootTest
public class VideoMetaDataTests {

    @Test
    void case1() throws IOException, ImageProcessingException {
        HashMap<String, String> map = new HashMap<>();
        String path3 = "/Users/liuxuyang/Downloads/IMG_6328.MOV";
        String path4 = "/Users/liuxuyang/Movies/Wondershare UniConverter13/Recorded/凤凰卫视元宇宙.mp4";
        FileInputStream fis = new FileInputStream(path3);
        TimeInterval timeInterval = new TimeInterval();
        Metadata metadata = ImageMetadataReader.readMetadata(fis);
        metadata.getDirectories().forEach(directory -> {
            directory.getTags().forEach(tag -> {
                map.put(tag.getTagName(), tag.getDescription());
            });
        });
        System.out.println(timeInterval.interval());
        System.out.println(map.get("Duration in Seconds"));
        System.out.println(map.get("Width"));
        System.out.println(map.get("Height"));
        System.out.println(map.get("Model"));
        System.out.println(map.get("Software"));
    }

    public static void main(String[] args) {
        DateTime parse = DateUtil.parse("周五 4月 29 11:42:58 +08:00 2022", "");
        System.out.println(parse.toString());
    }
}
