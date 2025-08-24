package com.libro.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libro.demo.entity.Usuario;
import com.libro.demo.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    // ✅ Endpoint POST para login real
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        Usuario usuario = usuarioService.findByUsername(username);

        Map<String, Object> response = new HashMap<>();
        if (usuario != null && usuario.getPassword().equals(password)) {
            response.put("status", "success");
            response.put("message", "Login correcto");
            response.put("usuario", usuario.getUsername());
        } else {
            response.put("status", "error");
            response.put("message", "Credenciales inválidas");
        }
        return response;
    }

    // ✅ Endpoint GET para probar desde navegador
    @GetMapping("/login")
    public Map<String, Object> loginDemo() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Usa POST /api/login con {\"username\":\"...\", \"password\":\"...\"}");
        response.put("ejemplo", "/api/login -> {\"username\":\"admin\", \"password\":\"1234\"}");
        return response;
    }
}
