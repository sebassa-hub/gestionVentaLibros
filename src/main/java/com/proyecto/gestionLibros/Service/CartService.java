package com.proyecto.gestionLibros.Service;

import com.proyecto.gestionLibros.entity.*;
import com.proyecto.gestionLibros.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CartService {
  private final ShoppingCartRepository carts;
  private final CartItemRepository items;
  private final UserRepository users;
  private final LibroRepository libros;
  private final VentaService ventas;

  public CartService(ShoppingCartRepository c, CartItemRepository i, UserRepository u,
                     LibroRepository l, VentaService v){
    this.carts=c; this.items=i; this.users=u; this.libros=l; this.ventas=v;
  }

  public ShoppingCart getOrCreateCart(Long userId){
    return carts.findByUser_Id(userId).orElseGet(() -> {
      var cart = new ShoppingCart();
      cart.setUser(users.findById(userId).orElseThrow());
      return carts.save(cart);
    });
  }

  public ShoppingCart addItem(Long userId, Long libroId, int cantidad){
    var cart = getOrCreateCart(userId);
    var libro = libros.findById(libroId).orElseThrow();
    var item = cart.getItems().stream()
        .filter(ci -> ci.getLibro().getId_libro().equals(libroId))
        .findFirst().orElseGet(() -> {
          var ci = new CartItem(); ci.setCart(cart); ci.setLibro(libro); ci.setCantidad(0); cart.getItems().add(ci);
          return ci;
        });
    item.setCantidad(item.getCantidad()+cantidad);
    cart.setUpdatedAt(java.time.LocalDateTime.now());
    return carts.save(cart);
  }

  public ShoppingCart updateItem(Long userId, Long itemId, int cantidad){
    var cart = getOrCreateCart(userId);
    var item = cart.getItems().stream().filter(i -> i.getId().equals(itemId)).findFirst().orElseThrow();
    if (cantidad<=0) { cart.getItems().remove(item); items.delete(item); }
    else { item.setCantidad(cantidad); }
    return carts.save(cart);
  }

  @Transactional
  public Venta checkout(Long userId){
    var cart = getOrCreateCart(userId);
    if (cart.getItems().isEmpty()) throw new RuntimeException("Carrito vacío");
    var venta = ventas.createFromCart(cart);
    cart.getItems().clear();
    carts.save(cart);
    return venta;
  }
}
