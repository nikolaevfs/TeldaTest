package com.example.telda;

import com.example.telda.model.Directory;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@MapperScan("com.example.telda.mapper")
@MappedTypes(Directory.class)
@SpringBootApplication
@EnableCaching
public class TeldaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeldaApplication.class, args);
    }

}
