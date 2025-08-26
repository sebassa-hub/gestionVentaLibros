package com.proyecto.gestionLibros.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.gestionLibros.entity.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
  Optional<ShoppingCart> findByUser_Id(Long userId);
}