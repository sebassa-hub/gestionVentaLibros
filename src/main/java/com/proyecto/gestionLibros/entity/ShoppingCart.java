package com.proyecto.gestionLibros.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity @Table(name="shopping_carts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ShoppingCart {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne @JoinColumn(name="user_id", unique = true)
  private User user;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CartItem> items = new ArrayList<>();

  private LocalDateTime updatedAt = LocalDateTime.now();
}