package com.mca.landmarketproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LandmarketprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LandmarketprojectApplication.class, args);
    }

//    @Bean
//    WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("http://localhost:5173");
//            }
//        };
//    }
}
