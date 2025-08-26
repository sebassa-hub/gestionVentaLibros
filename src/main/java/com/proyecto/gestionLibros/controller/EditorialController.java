package com.proyecto.gestionLibros.controller;

import com.proyecto.gestionLibros.Service.EditorialService;
import com.proyecto.gestionLibros.entity.Editorial;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editoriales")
@CrossOrigin
public class EditorialController {

    @Autowired
    private EditorialService editServ;

    @GetMapping
    public ResponseEntity<List<Editorial>> listarEditorial() {
        return ResponseEntity.ok(editServ.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editorial> obtenerEditorial(@PathVariable Long id) {
        var edit = editServ.obtenerPorId(id);
        return (edit == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(edit);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> agregarEditorial(@Valid @RequestBody Editorial edit) {
        return ResponseEntity.ok(editServ.guardar(edit));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEditorial(@PathVariable Long id, @Valid @RequestBody Editorial edit) {
        edit.setId_editorial(id);
        return ResponseEntity.ok(editServ.actualizar(edit));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEditorial(@PathVariable Long id) {
        editServ.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
