package com.ssafy.devoca.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${allowed-origins}")
    private String allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //모든 경로에 대해
                .allowedOrigins(allowedOrigins.split(",")) // 허용할 출처
                .allowedHeaders("*")
                .allowedMethods("*") // 허용할 HTTP method
                .allowCredentials(true) // 쿠키/인증 정보 포함 허용
                .maxAge(3000); // 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
//        registry
//                .addMapping("/**")
//                .allowedOrigins("http://localhost:5173", "http://localhost:5174")
//			    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD")
//                .allowCredentials(true)
//                .maxAge(1800); // Pre-flight Caching
    }

}
