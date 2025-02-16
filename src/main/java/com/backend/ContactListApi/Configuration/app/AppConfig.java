package com.backend.ContactListApi.Configuration.app;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {

    /**
     * Configuración de CORS.
     * <p>
     * Este método crea un `WebMvcConfigurer` que permite el acceso a la API
     * desde el dominio `http://localhost:4200`, que corresponde a la interfaz de usuario (Angular).
     * Se permite el uso de cualquier método HTTP y encabezado en las solicitudes.
     *
     * @return Un `WebMvcConfigurer` con la configuración de CORS aplicada.
     */

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**")// Permite solicitudes a todos los endpoints de la API
                        .allowedOrigins("http://localhost:4200") // Especifica el origen permitido (frontend)
                        .allowedMethods("*") // Permite todos los métodos HTTP (GET, POST, PUT, DELETE, etc.)
                        .allowedHeaders("*"); // Permite cualquier tipo de encabezado en las solicitudes
            }
        };
    }

    /**
     * Bean de ModelMapper.
     * <p>
     * Proporciona una instancia de `ModelMapper`, utilizada para mapear automáticamente
     * objetos de entidades a DTOs y viceversa.
     *
     * @return Una instancia de `ModelMapper`.
     */
    @Bean
    ModelMapper modelMapper(){
//        instancia del modelMapper
        return new ModelMapper();
    }
}
