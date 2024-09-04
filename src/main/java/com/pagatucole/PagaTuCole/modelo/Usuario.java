package com.pagatucole.PagaTuCole.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="tusuario")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {
    @Id
    @Column(name="id_usuario")
    private String userid;
    @Column(name="usuario")
    private String username;
    @Column(name="contraseña")
    private String password;
    @Column(name="role")
    private String role;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Persona persona;
}
