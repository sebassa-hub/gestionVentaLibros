package com.proyecto.gestionLibros.controller;

import com.proyecto.gestionLibros.Service.VentaService;
import com.proyecto.gestionLibros.dto.VentaRequest;
import com.proyecto.gestionLibros.entity.Venta;
import com.proyecto.gestionLibros.repository.UserRepository;
import com.proyecto.gestionLibros.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private UserRepository users;

    @Autowired
    private VentaRepository ventaRepository;

    @GetMapping
    public List<Venta> listarVentas() {
        return ventaService.listarTodos();
    }

    @PostMapping("/comprar")
    public ResponseEntity<Venta> comprar(@RequestBody VentaRequest request) {
        Venta venta = ventaService.comprar(request);
        return ResponseEntity.ok(venta);
    }

    @GetMapping("/{id}")
    public Venta obtenerVenta(@PathVariable Long id) {
        return ventaService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarVenta(@PathVariable Long id) {
        ventaService.eliminar(id);
    }

    // Historial de compras del usuario autenticado
    @GetMapping("/me")
    public List<Venta> misCompras(@AuthenticationPrincipal UserDetails ud) {
        var user = users.findByUsername(ud.getUsername()).orElseThrow();
        var cliente = user.getCliente();
        if (cliente == null) return List.of();
        return ventaRepository.findByCliente_Id(cliente.getId());
    }
}
