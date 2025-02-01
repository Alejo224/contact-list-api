package com.backend.ContactListApi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ContactListApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactListApiApplication.class, args);
	}

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("*").allowedHeaders("*");
            }
        };
    }

    @Bean
    ModelMapper modelMapper(){
    //instancia de ModelMapper
        return new ModelMapper();

    }

}
