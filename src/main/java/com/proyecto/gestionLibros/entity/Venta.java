package com.proyecto.gestionLibros.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "Ventas")
public class Venta {

	
	@Id
	@Column(name = "id_Venta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_Cliente", nullable = false)
	private Cliente cliente;
	
	
	@Column(name = "fecha_Venta", nullable=false)
	private LocalDateTime fechaVenta;
	
	
	@Column(name="subtotal")
	private BigDecimal subtotal;
	
	@Column(name="igv")
	private BigDecimal igv;
	
	@Column(name="total")
	private BigDecimal total;
	
	
	//Relacion con DetalleVenta
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
	private List<VentaDetalle> detalles = new ArrayList<>();
	
}
