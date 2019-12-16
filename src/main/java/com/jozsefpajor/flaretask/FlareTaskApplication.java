package com.jozsefpajor.flaretask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FlareTaskApplication {

    public static void main( String[] args ) {
        SpringApplication.run(FlareTaskApplication.class, args);
    }

}
