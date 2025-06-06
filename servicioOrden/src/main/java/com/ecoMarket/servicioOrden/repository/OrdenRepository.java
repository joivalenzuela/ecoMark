package com.ecoMarket.servicioOrden.repository;


import com.ecoMarket.servicioOrden.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
