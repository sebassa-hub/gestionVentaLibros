package com.proyecto.gestionLibros.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

import com.proyecto.gestionLibros.security.Role;

@Entity 
@Table(name="users")
@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false, length = 60)
  @NotBlank
  private String username;

  @Column(unique = true, nullable = false)
  @Email
  private String email;

  @Column(nullable = false)
  private String password;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name="user_roles", joinColumns = @JoinColumn(name="user_id"))
  @Enumerated(EnumType.STRING)
  @Column(name="role")
  private Set<Role> roles;

  @Builder.Default
  private boolean enabled = true;

  @Builder.Default
  private LocalDateTime createdAt = LocalDateTime.now();

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="cliente_id")
  private Cliente cliente;
}
