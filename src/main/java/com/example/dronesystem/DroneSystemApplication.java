package com.example.dronesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.dronesystem.mapper")
public class DroneSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DroneSystemApplication.class, args);
    }
}