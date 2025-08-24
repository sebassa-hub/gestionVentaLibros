package com.proyecto.gestionLibros.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> info = new HashMap<>();
        info.put("mensaje", "📚 Bienvenido a la API de Gestión de Libros");
        
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("Clientes", "/api/clientes");
        endpoints.put("Libros", "/api/libros");
        endpoints.put("Ventas", "/api/ventas");
        endpoints.put("Detalle de Ventas", "/api/ventaDetalle");
        endpoints.put("Editoriales", "/api/editoriales (pendiente)");
        endpoints.put("Categorías", "/api/categorias (pendiente)");
        
        info.put("endpoints_disponibles", endpoints);
        return info;
    }
}
