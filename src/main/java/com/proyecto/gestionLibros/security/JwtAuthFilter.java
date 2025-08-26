package com.proyecto.gestionLibros.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
  private final JwtService jwt;
  private final UserDetailsService uds;

  public JwtAuthFilter(JwtService jwt, UserDetailsService uds) {
    this.jwt = jwt; this.uds = uds;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws java.io.IOException, ServletException {
    final String auth = req.getHeader("Authorization");
    if (auth != null && auth.startsWith("Bearer ")) {
      String token = auth.substring(7);
      try {
        String username = jwt.getSubject(token);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          UserDetails ud = uds.loadUserByUsername(username);
          var authToken = new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      } catch (Exception ignored) { }
    }
    chain.doFilter(req, res);
  }
}
