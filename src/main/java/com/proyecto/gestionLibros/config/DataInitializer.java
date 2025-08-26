package com.proyecto.gestionLibros.config;

import com.proyecto.gestionLibros.entity.User;
import com.proyecto.gestionLibros.repository.UserRepository;
import com.proyecto.gestionLibros.security.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initAdmin(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.count() == 0) {
                User admin = User.builder()
                        .username("admin")
                        .email("admin@example.com")
                        .password(encoder.encode("admin123"))
                        .roles(Set.of(Role.ADMIN))
                        .enabled(true)
                        .build();
                repo.save(admin);
                System.out.println("Usuario ADMIN creado: admin/admin123");
            }
        };
    }
}
