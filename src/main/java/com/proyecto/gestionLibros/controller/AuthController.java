package com.proyecto.gestionLibros.controller;

import com.proyecto.gestionLibros.dto.*;
import com.proyecto.gestionLibros.entity.User;
import com.proyecto.gestionLibros.repository.UserRepository;
import com.proyecto.gestionLibros.security.JwtService;
import com.proyecto.gestionLibros.security.Role;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
  private final UserRepository users;
  private final PasswordEncoder encoder;
  private final AuthenticationManager authManager;
  private final JwtService jwt;

  public AuthController(UserRepository users, PasswordEncoder encoder, AuthenticationManager am, JwtService jwt) {
    this.users = users; this.encoder = encoder; this.authManager = am; this.jwt = jwt;
  }

  @PostMapping("/register")
public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
  if (users.existsByUsername(req.username()) || users.existsByEmail(req.email()))
    return ResponseEntity.badRequest().body(Map.of("error","Usuario o email ya existe"));

  var user = User.builder()
      .username(req.username())
      .email(req.email())
      .password(encoder.encode(req.password()))
      .roles(Set.of(Role.CLIENTE))
      .enabled(true)
      .build();
  users.save(user);

  String token = jwt.generate(user.getUsername(), Map.of("roles", user.getRoles()));

  return ResponseEntity.ok(new AuthResponse(token, user.getUsername(), user.getRoles()));
}


  @PostMapping("/login")
public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest req) {
  var tokenReq = new UsernamePasswordAuthenticationToken(req.usernameOrEmail(), req.password());
  authManager.authenticate(tokenReq); // lanza excepción si falla

  var user = users.findByUsername(req.usernameOrEmail())
      .or(() -> users.findAll().stream()
          .filter(u -> u.getEmail().equalsIgnoreCase(req.usernameOrEmail()))
          .findFirst())
      .orElseThrow();

  String token = jwt.generate(user.getUsername(), Map.of("roles", user.getRoles()));

  return ResponseEntity.ok(new AuthResponse(token, user.getUsername(), user.getRoles()));
}


}
