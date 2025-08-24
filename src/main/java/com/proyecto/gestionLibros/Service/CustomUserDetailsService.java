package com.proyecto.gestionLibros.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService { // 👈 implementar la interfaz

    @Autowired
    private UsuarioService usuarioService; // tu servicio que accede a la tabla usuario

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.proyecto.gestionLibros.entity.Usuario usuario = usuarioService.buscarByUsuario(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return org.springframework.security.core.userdetails.User.withUsername(usuario.getUsername())
                .password("{noop}" + usuario.getClave()) // por ahora sin encriptar
                .roles(usuario.getRol().getDescripcion()) // usa el rol de la DB
                .build();
    }
}
