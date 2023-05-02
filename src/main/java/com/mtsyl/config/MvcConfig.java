package com.mtsyl.config;


import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MvcConfig implements WebMvcConfigurer {



//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //登录拦截器
//        registry.addInterceptor(new LoginInterceptor())
//                .excludePathPatterns(
//                        /**
//                         * 这里面用来填写放行的路径
//                         */
//                )
//                .order(1);
//
//    }
}
