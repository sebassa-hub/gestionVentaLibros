package com.proyecto.gestionLibros.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaResponse {

	 private Long id;
	    private BigDecimal total;
	    private String clienteNombre;
	    private String clienteApellido;
	    private LocalDateTime fechaVenta;

	
	
}
