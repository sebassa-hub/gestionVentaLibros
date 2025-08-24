package com.proyecto.gestionLibros.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.proyecto.gestionLibros.enums.TipoDocumento_Cliente;
import com.proyecto.gestionLibros.enums.TipoGenero_Cliente;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table( name = "Clientes")
public class Cliente {

	
	@Id
	@Column(name = "id_Cliente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombres_Clientes", nullable=false)
	private String nombres;
	
	
	@Column(name = "apellidos_Clientes", nullable=false)
	private String apellidos;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_Documento")
	private TipoDocumento_Cliente tipoDocumento_Cliente;
	
	
	@Column(name = "numeroDocumento_Cliente", nullable=false)
	private String numeroDocumento;
	
	
	@Column(name = "telefono_Cliente")
	private String telefono;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "genero_Cliente")
	private TipoGenero_Cliente tipoGenero_Cliente;
	
	
	@Column(name = "fechaNacimiento_Cliente")
	private LocalDate fechaNacimiento;
	
	
	@Column(name = "correo_Cliente", nullable=false)
	private String correo;

	@JsonBackReference
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Venta> ventas = new ArrayList<>();
	
	
}
