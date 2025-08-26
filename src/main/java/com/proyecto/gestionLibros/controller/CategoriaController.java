package com.proyecto.gestionLibros.controller;

import com.proyecto.gestionLibros.Service.CategoriaService;
import com.proyecto.gestionLibros.entity.Categoria;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin
public class CategoriaController {

    @Autowired
    private CategoriaService catServ;

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategoria() {
        return ResponseEntity.ok(catServ.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoria(@PathVariable Long id) {
        var cat = catServ.obtenerPorId(id);
        return (cat == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(cat);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> agregarCategoria(@Valid @RequestBody Categoria cat) {
        return ResponseEntity.ok(catServ.guardar(cat));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCategoria(@PathVariable Long id, @Valid @RequestBody Categoria cat) {
        cat.setId_categoria(id);
        return ResponseEntity.ok(catServ.actualizar(cat));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        catServ.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
