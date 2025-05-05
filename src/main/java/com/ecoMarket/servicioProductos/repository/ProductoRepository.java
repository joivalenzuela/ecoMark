package com.ecoMarket.servicioProductos.repository;

import com.ecoMarket.servicioProductos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    List<Producto> findByMarca(String marca);

    List<Producto> findByCategoria(String categoria);

    Producto findByProdCode(String prodCode);

}
