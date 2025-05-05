package com.ecoMarket.servicioProductos.service;

import com.ecoMarket.servicioProductos.model.Producto;
import com.ecoMarket.servicioProductos.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public List<Producto> findByFilter(String categoria,String marca){
        return productoRepository.findByFilter(categoria,marca);
    }

    public Producto findById(long id) {
        return productoRepository.findById(id).get();
    }

    public Producto findByProdCode(String prodCode) {
        return productoRepository.findByProdCode(prodCode);
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public void delete(Long id) {
        productoRepository.deleteById(id);

    }

    public void deleteByProdCode(String prodCode) {
        productoRepository.deleteByProdCode(prodCode);
    }




}
