package com.example.fittoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FitToServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitToServerApplication.class, args);
    }

}
