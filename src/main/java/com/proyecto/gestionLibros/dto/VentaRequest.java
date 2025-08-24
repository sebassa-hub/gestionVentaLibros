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
		public Long getId_libro() {
			return id_libro;
		}
		public void setId_libro(Long id_libro) {
			this.id_libro = id_libro;
		}
		public int getCantidad() {
			return cantidad;
		}
		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
        
        
    }

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public List<LibroCompra> getLibros() {
		return libros;
	}

	public void setLibros(List<LibroCompra> libros) {
		this.libros = libros;
	}
    
    
}
