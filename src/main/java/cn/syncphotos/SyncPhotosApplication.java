package cn.syncphotos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.syncphotos.mapper")
public class SyncPhotosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyncPhotosApplication.class, args);
    }

}
