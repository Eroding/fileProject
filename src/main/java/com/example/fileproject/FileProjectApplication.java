package com.example.fileproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.fileproject.mapper")
public class FileProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileProjectApplication.class, args);
    }

}
