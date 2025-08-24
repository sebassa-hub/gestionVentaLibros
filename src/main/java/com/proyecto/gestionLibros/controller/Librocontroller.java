package com.proyecto.gestionLibros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.gestionLibros.Service.LibroService;
import com.proyecto.gestionLibros.entity.Libro;

@CrossOrigin(origins = "http://localhost:4200") // Permite llamadas desde Angular
@RestController
@RequestMapping("/api/libros")
public class Librocontroller {
	
	@Autowired
	private LibroService libroService;
	
	@GetMapping
	public ResponseEntity<List<Libro>> listarLibros(){
		return ResponseEntity.ok(libroService.listarLibro());
	}
	
	@GetMapping("/{id}")
	public Libro obtenerLibro(@PathVariable Long id) {
		return libroService.obternerporId(id);
	}
	
	@PostMapping
	public ResponseEntity<?> agregarLibro(@RequestBody Libro libro){
		try {
			return ResponseEntity.ok(libroService.guardar(libro));
		} catch(Exception e){
			return ResponseEntity.badRequest().body("Error al guardar el libro");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarLibro(@RequestBody Libro libro){
		try {
			return ResponseEntity.ok(libroService.actualizar(libro));
		} catch(Exception e){
			return ResponseEntity.badRequest().body("Error al actualizar el libro");
		}
	}
}
