package com.proyecto.gestionLibros.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;



@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http
        .csrf(csrf -> csrf.disable()) // Para pruebas con Postman
        .cors(withDefaults())
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll() // Protege todas las rutas
            
            /*.anyRequest().authenticated()*/
            
        )
        .formLogin(withDefaults()) // Usa login.html si está en templates
        .httpBasic(withDefaults());  // Para Postman (Basic Auth)
    return http.build();
		
	}

    // Usuario en memoria para pruebas (admin/clave1234)
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("admin")
                .password("{noop}clave1234") // {noop} = sin codificación
                .roles("admin")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
	

	    // Necesario si quieres usar AuthenticationManager
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
	
}
