package com.dukbab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SirenOrdukApplication {
    public static void main(String[] args) {
        SpringApplication.run(SirenOrdukApplication.class, args);
    }
}
