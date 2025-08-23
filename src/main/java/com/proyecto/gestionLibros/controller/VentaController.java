package com.proyecto.gestionLibros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.gestionLibros.Service.VentaService;
import com.proyecto.gestionLibros.dto.VentaRequest;
import com.proyecto.gestionLibros.entity.Venta;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

	@Autowired
	private VentaService ventaService;
	
	@GetMapping
	public List<Venta> listarVentas(){
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
	
	
	
}
