package com.proyecto.gestionLibros.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.proyecto.gestionLibros.Service.CustomUserDetailsService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Rutas públicas
                .requestMatchers("/login", "/register", "/register/save").permitAll()
                // Todo lo demás requiere autenticación
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")                  // carga tu login.html
                .loginProcessingUrl("/login")         // Spring Security procesa POST /login
                .usernameParameter("username")        // coincide con tu form field
                .passwordParameter("clave")           // coincide con tu form field
                .defaultSuccessUrl("/usuario", true)  // redirige al listado de usuarios
                .failureUrl("/login?error=true")      // redirige con parámetro en caso de error
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/cerrar")                 // coincide con tu UsuarioController
                .logoutSuccessUrl("/login?logout=true") // mensaje de logout
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(customUserDetailsService)
                   .and()
                   .build();
    }
}
