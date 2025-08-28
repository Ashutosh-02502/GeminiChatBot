package com.ai.Gemini.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://geminichatbot-frontend1.onrender.com")
                .allowedMethods("GET", "PUT", "POST", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
