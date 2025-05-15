package com.ecoMarket.servicioOrden.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name= "Orden")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable= false)
    private Date fecha;

    @Column(nullable= false)
    private String estado;

    @Column(nullable= false)
    private float total;


}
