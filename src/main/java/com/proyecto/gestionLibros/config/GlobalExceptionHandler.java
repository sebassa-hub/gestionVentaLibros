package com.proyecto.gestionLibros.config;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex){
    var errors = ex.getBindingResult().getFieldErrors().stream()
        .collect(Collectors.toMap(e->e.getField(), e->e.getDefaultMessage(), (a,b)->a));
    return ResponseEntity.badRequest().body(Map.of("mensaje","Validación fallida","errores",errors));
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<?> handleRuntime(RuntimeException ex){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
  }
}
