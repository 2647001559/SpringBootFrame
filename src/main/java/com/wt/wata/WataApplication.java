package com.wt.wata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

/**
 * springboot启动类
 * @author 添柴灬少年
 *
 */
@SpringBootApplication
public class WataApplication {

    public static void main(String[] args) {
        SpringApplication.run(WataApplication.class, args);
    }

}

