package com.proyecto.gestionLibros.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    String uploads = java.nio.file.Paths.get(System.getProperty("user.dir"), "uploads")
        .toUri().toString();
    registry.addResourceHandler("/uploads/**")
            .addResourceLocations(uploads);
  }
}
