package com.wt.wata.common.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 添柴灬少年
 * @date 2019/6/4 - 17:34
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
    @Value("${file.static-access-path}")
    private String staticAccessPath;
    @Value("${file.upload-folder}")
    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticAccessPath).addResourceLocations("file:"+uploadFolder);
    }
}
