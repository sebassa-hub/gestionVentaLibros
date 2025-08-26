package com.proyecto.gestionLibros.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity 
@Table(name="cart_items")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class CartItem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne @JoinColumn(name="cart_id")
  private ShoppingCart cart;

  @ManyToOne @JoinColumn(name="libro_id")
  private Libro libro;

  @Column(nullable=false)
  private int cantidad;
}
