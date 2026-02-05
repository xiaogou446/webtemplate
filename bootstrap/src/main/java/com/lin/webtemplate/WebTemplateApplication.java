package com.lin.webtemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class WebTemplateApplication {

    public static void main(String[] args) {

        SpringApplication.run(WebTemplateApplication.class, args);
        log.info("WebTemplateApplication started successfully");
    }

}
