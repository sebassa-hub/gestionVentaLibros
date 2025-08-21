package com.proyecto.gestionLibros.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@Column(nullable = false,name="precio")
	private double precio;
	
	@Column(nullable = false,name="stock")
	private int stock;
	
	@Column(columnDefinition = "TEXT", name="descripcion")
	private String descripcion;
}
