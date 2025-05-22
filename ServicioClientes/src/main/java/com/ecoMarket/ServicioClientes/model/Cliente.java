package com.ecoMarket.ServicioClientes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 8, nullable=false)
    private int run;

    @Column(unique = false, length = 1, nullable = false)
    private String dv;

    @Column(unique = false, length = 15, nullable = false)
    private String nombre;

    @Column(unique = false, length = 15, nullable = false)
    private String apellido_p;

    @Column(unique = false, length = 15, nullable = true)
    private String apellido_m;

    @Column(unique = false, length = 255, nullable = false)
    private String direccion;

    @Column(unique = false, length = 255, nullable = false)
    private String user;

    @Column(unique = false, length = 255, nullable = false)
    private String password;



    @PrePersist
    @PreUpdate
    private void hashPassword() {
        if (this.password != null && !this.password.startsWith("$2a$")) { // Verifica si no está ya hasheada
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            this.password = encoder.encode(this.password);
        }
    }

}

