package com.wt.wata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;


@SpringBootApplication
public class WataApplication {

	/**
	 * 启动方法
	 * @param args
	 */
    public static void main(String[] args) {
        SpringApplication.run(WataApplication.class, args);
    }

}

