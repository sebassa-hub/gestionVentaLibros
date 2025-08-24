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

import com.proyecto.gestionLibros.Service.CategoriaService;
import com.proyecto.gestionLibros.entity.Categoria;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService catServ;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategoria(){
		return ResponseEntity.ok(catServ.listar());
	}
	
	@GetMapping("/{id}")
	public Categoria obtenerCategoria(@PathVariable Long id) {
		return catServ.obtenerPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<?> agregarCategoria(@RequestBody Categoria cat){
		try {
			return ResponseEntity.ok(catServ.guardar(cat));
		} catch(Exception e){
			return ResponseEntity.badRequest().body("Error al guardar la categoria");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarCategoria(@RequestBody Categoria cat){
		try {
			return ResponseEntity.ok(catServ.actualizar(cat));
		} catch(Exception e){
			return ResponseEntity.badRequest().body("Error al actualizar la categoria");
		}
	}
}
