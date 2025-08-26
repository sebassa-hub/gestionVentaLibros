package com.proyecto.gestionLibros.dto;

import jakarta.validation.constraints.*;

public record RegisterRequest(
    @NotBlank @Size(min=3,max=60) String username,
    @NotBlank @Email String email,
    @NotBlank @Size(min=6,max=100) String password,
    String nombres, String apellidos
) { }
