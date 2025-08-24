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

import com.proyecto.gestionLibros.Service.EditorialService;
import com.proyecto.gestionLibros.entity.Editorial;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/editorial")
public class EditorialController {

	@Autowired
	private EditorialService editServ;
	
	@GetMapping
	public ResponseEntity<List<Editorial>> listarEditorial(){
		return ResponseEntity.ok(editServ.listar());
	}
	
	@GetMapping("/{id}")
	public Editorial obtenerEditorial(@PathVariable Long id) {
		return editServ.obtenerPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<?> agregarEditorial(@RequestBody Editorial edit){
		try {
			return ResponseEntity.ok(editServ.guardar(edit));
		} catch(Exception e){
			return ResponseEntity.badRequest().body("Error al guardar la editorial");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarEditorial(@RequestBody Editorial edit){
		try {
			return ResponseEntity.ok(editServ.actualizar(edit));
		} catch(Exception e){
			return ResponseEntity.badRequest().body("Error al actualizar la editorial");
		}
	}
}
