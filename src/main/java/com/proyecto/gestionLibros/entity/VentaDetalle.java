package com.proyecto.gestionLibros.entity;


import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Detalle_Ventas")
public class VentaDetalle {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Detalle")
    private Long id;
	
	
	@ManyToOne
	@JoinColumn(name = "id_Libro", nullable = false)
	private Libro libro;
	
	
	@ManyToOne
	@JoinColumn(name = "id_Venta", nullable=false)
	private Venta venta;
	
	@Column(name = "cantidad", nullable=false)
	private int cantidad;
	
	 @Column(name = "precio_Unitario", precision=10, scale=2, nullable = false)
	 private BigDecimal precioUnitario;

	 @Column(name = "subtotal", precision=10, scale=2, nullable = false)
	 private BigDecimal subtotal;
	
}
