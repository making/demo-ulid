package com.example.demoulid;

import de.huxhorn.sulky.ulid.ULID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoUlidApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoUlidApplication.class, args);
    }

    @Bean
    public ULID ulid() {
        return new ULID();
    }


}
