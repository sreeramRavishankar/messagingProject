package com.example.ram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class RamApplication {

    public static void main(String[] args) {
        SpringApplication.run(RamApplication.class, args);
    }

}
