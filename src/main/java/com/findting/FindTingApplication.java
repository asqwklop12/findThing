package com.findting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FindTingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FindTingApplication.class, args);
    }

}
