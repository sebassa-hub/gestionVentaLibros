package com.proyecto.gestionLibros.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.proyecto.gestionLibros.enums.Estados_Libro;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="libro")
public class Libro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_libro;
	
	@Column(nullable = false,length = 255,name="titulo")
	private String titulo;
	
	@Column(nullable = false,length = 150,name="autor")
	private String autor;
	
	@ManyToOne
	@JoinColumn(name="id_editorial")
	private Editorial editorial;
	
	@ManyToOne
	@JoinColumn(name="id_categoria")
	private Categoria categoria;
	
	@Column(nullable = false, precision=10, scale=2,name="precio")
	private BigDecimal precio;
	
	@Column(nullable = false,name="stock")
	private int stock;
	
	@Column(columnDefinition = "TEXT", name="descripcion")
	private String descripcion;
	
	@Enumerated(EnumType.STRING)
	@Column(name="estado")
	private Estados_Libro estado;
	
	@JsonBackReference
	@OneToMany(mappedBy = "libro", cascade=CascadeType.ALL)
	private List<VentaDetalle> detalles = new ArrayList<>();

	public Long getId_libro() {
		return id_libro;
	}

	public void setId_libro(Long id_libro) {
		this.id_libro = id_libro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Estados_Libro getEstado() {
		return estado;
	}

	public void setEstado(Estados_Libro estado) {
		this.estado = estado;
	}

	public List<VentaDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<VentaDetalle> detalles) {
		this.detalles = detalles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
