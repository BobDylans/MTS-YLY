package com.mtsyl;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MtsYlApplication {

    public static void main(String[] args) {
        SpringApplication.run(MtsYlApplication.class, args);
    }

}
