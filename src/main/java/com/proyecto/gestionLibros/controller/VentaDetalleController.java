package com.proyecto.gestionLibros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.gestionLibros.Service.VentaDetalleService;
import com.proyecto.gestionLibros.entity.VentaDetalle;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/ventaDetalle")
public class VentaDetalleController {

	@Autowired
	private VentaDetalleService ventaDetalleService;
	
	
	@GetMapping
	public List<VentaDetalle> listarDetalles(){
		return ventaDetalleService.listarTodos();
	}
	
	@PostMapping
	public VentaDetalle crearDetalleVenta(@RequestBody VentaDetalle ventaDetalle) {
		return ventaDetalleService.guardar(ventaDetalle);
	}
	
	
	@GetMapping("/{id}")
	public VentaDetalle obtenerVentaDetalle(@PathVariable Long id) {
		return ventaDetalleService.obtenerPorId(id);
	}
	
	@DeleteMapping
	public void eliminarDetalleVenta(@PathVariable Long id) {
		ventaDetalleService.eliminar(id);
	}
	
}
