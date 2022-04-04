package com.example.graf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class GrafApplication {


    public static void main(String[] args) {
        ClassLoader classLoader = GrafApplication.class.getClassLoader();

        SpringApplication.run(GrafApplication.class, args);
    }

}
