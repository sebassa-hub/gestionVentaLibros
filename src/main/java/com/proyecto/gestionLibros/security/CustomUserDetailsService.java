package com.proyecto.gestionLibros.security;

import com.proyecto.gestionLibros.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  private final UserRepository repo;
  public CustomUserDetailsService(UserRepository repo){ this.repo = repo; }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = repo.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    var authorities = user.getRoles().stream()
        .map(r -> "ROLE_" + r.name())
        .map(org.springframework.security.core.authority.SimpleGrantedAuthority::new)
        .toList();
    return new org.springframework.security.core.userdetails.User(
        user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
  }
}
