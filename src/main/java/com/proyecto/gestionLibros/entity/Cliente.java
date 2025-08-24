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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public TipoDocumento_Cliente getTipoDocumento_Cliente() {
		return tipoDocumento_Cliente;
	}

	public void setTipoDocumento_Cliente(TipoDocumento_Cliente tipoDocumento_Cliente) {
		this.tipoDocumento_Cliente = tipoDocumento_Cliente;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public TipoGenero_Cliente getTipoGenero_Cliente() {
		return tipoGenero_Cliente;
	}

	public void setTipoGenero_Cliente(TipoGenero_Cliente tipoGenero_Cliente) {
		this.tipoGenero_Cliente = tipoGenero_Cliente;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
	
	
}
