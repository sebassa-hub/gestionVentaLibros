package com.proyecto.gestionLibros.controller;

import com.proyecto.gestionLibros.Service.LibroService;
import com.proyecto.gestionLibros.entity.Libro;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros() {
        return ResponseEntity.ok(libroService.listarLibro());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibro(@PathVariable Long id) {
        var libro = libroService.obternerporId(id);
        return (libro == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(libro);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> agregarLibro(@Valid @RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.guardar(libro));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarLibro(@PathVariable Long id, @Valid @RequestBody Libro libro) {
        libro.setId_libro(id);
        return ResponseEntity.ok(libroService.actualizar(libro));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/imagen")
    public ResponseEntity<?> upload(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
      var libro = libroService.obternerporId(id);
    if (libro == null) return ResponseEntity.notFound().build();
  if (file.isEmpty()) return ResponseEntity.badRequest().body(Map.of("error", "Archivo vacío"));

  String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
  String nombre = "libro_" + id + (ext != null ? "." + ext : "");

  Path uploads = Paths.get(System.getProperty("user.dir"), "uploads");
  Files.createDirectories(uploads);

  Path destino = uploads.resolve(nombre);
  file.transferTo(destino.toFile());

  libro.setImageUrl("/uploads/" + nombre);
  libroService.actualizar(libro);

  return ResponseEntity.ok(Map.of("imageUrl", libro.getImageUrl()));
}
}
