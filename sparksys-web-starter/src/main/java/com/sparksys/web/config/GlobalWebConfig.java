package com.sparksys.web.config;

import com.sparksys.web.interceptor.ResponseResultInterceptor;
import com.sparksys.web.support.GlobalExceptionHandler;
import com.sparksys.web.support.ResponseResultHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * description: WebConfig全局配置
 *
 * @author zhouxinlei
 * @date 2020-05-24 13:43:12
 */
@Configuration
@Import({ResponseResultHandler.class, GlobalExceptionHandler.class})
public class GlobalWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(responseResultInterceptor());
    }

    @Bean
    public ResponseResultInterceptor responseResultInterceptor() {
        return new ResponseResultInterceptor();
    }
}
