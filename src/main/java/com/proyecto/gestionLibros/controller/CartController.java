package com.proyecto.gestionLibros.controller;

import com.proyecto.gestionLibros.Service.CartService;
import com.proyecto.gestionLibros.entity.ShoppingCart;
import com.proyecto.gestionLibros.entity.Venta;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/cart") @CrossOrigin
public class CartController {
  private final CartService cart;
  private final com.proyecto.gestionLibros.repository.UserRepository users;

  public CartController(CartService c, com.proyecto.gestionLibros.repository.UserRepository u){ this.cart=c; this.users=u; }

  private Long currentUserId(UserDetails ud) {
    var u = users.findByUsername(ud.getUsername()).orElseThrow();
    return u.getId();
  }

  @GetMapping
  public ShoppingCart get(@AuthenticationPrincipal UserDetails ud){
    return cart.getOrCreateCart(currentUserId(ud));
  }

  @PostMapping("/items")
  public ShoppingCart add(@AuthenticationPrincipal UserDetails ud, @RequestParam Long libroId, @RequestParam int cantidad){
    return cart.addItem(currentUserId(ud), libroId, cantidad);
  }

  @PutMapping("/items/{itemId}")
  public ShoppingCart update(@AuthenticationPrincipal UserDetails ud, @PathVariable Long itemId, @RequestParam int cantidad){
    return cart.updateItem(currentUserId(ud), itemId, cantidad);
  }

  @PostMapping("/checkout")
  public Venta checkout(@AuthenticationPrincipal UserDetails ud){
    return cart.checkout(currentUserId(ud));
  }
}
