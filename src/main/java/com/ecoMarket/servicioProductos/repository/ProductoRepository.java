package com.ecoMarket.servicioProductos.repository;

import com.ecoMarket.servicioProductos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    @Query( "SELECT p FROM producto p WHERE " +
            "(:categoria IS NULL OR p.categoria = :categoria) AND " +
            "(:marca IS NULL OR p.marca = :marca)")
    List<Producto> findByFilter(
            @Param("categoria") String categoria,
            @Param("marca") String marca);

    Producto findByProdCode(String prodCode);

    void deleteByProdCode(String prodCode);

}
