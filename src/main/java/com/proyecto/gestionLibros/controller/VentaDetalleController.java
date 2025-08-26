package com.proyecto.gestionLibros.controller;

import com.proyecto.gestionLibros.Service.VentaDetalleService;
import com.proyecto.gestionLibros.entity.VentaDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventaDetalle")
@CrossOrigin
public class VentaDetalleController {

    @Autowired
    private VentaDetalleService ventaDetalleService;

    @GetMapping
    public ResponseEntity<List<VentaDetalle>> listarDetalles() {
        return ResponseEntity.ok(ventaDetalleService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDetalle> obtenerVentaDetalle(@PathVariable Long id) {
        var det = ventaDetalleService.obtenerPorId(id);
        return (det == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(det);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> crearDetalleVenta(@RequestBody VentaDetalle ventaDetalle) {
        return ResponseEntity.ok(ventaDetalleService.guardar(ventaDetalle));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalleVenta(@PathVariable Long id) {
        ventaDetalleService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
