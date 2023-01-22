package com.itheima.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * web mvc配置
 *
 * @author 14220
 * @date 2023/01/22
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 添加资源处理程序
     *
     * @param registry 注册表
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");

    }
}
