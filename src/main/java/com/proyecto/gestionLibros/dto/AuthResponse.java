package com.proyecto.gestionLibros.dto;

import com.proyecto.gestionLibros.security.Role;
import java.util.Set;

public record AuthResponse(
    String token,
    String username,
    Set<Role> roles
) {}
