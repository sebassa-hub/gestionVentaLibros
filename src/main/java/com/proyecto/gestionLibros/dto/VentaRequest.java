package com.proyecto.gestionLibros.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaRequest {

    private Long clienteId;
    private List<LibroCompra> libros;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LibroCompra {
        private Long id_libro;
        private int cantidad;
    }
}
