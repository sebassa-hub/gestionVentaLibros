package com.proyecto.gestionLibros.controller;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.proyecto.gestionLibros.repository.VentaRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/ventas/total")
  public Map<String,Object> totalVentas(VentaRepository repo){
    var total = repo.findAll().stream().map(v->v.getTotal()).reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
    return Map.of("totalVentas", total);
  }
  
}
