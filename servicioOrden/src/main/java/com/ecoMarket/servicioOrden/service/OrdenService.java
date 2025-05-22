package com.ecoMarket.servicioOrden.service;


import com.ecoMarket.servicioOrden.model.Orden;
import com.ecoMarket.servicioOrden.repository.OrdenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    public List<Orden> findAll(){
        return ordenRepository.findAll();
    }

    public Orden findById(Long id){
        return ordenRepository.findById(id).get();
    }

    public Orden save(Orden orden){
        return ordenRepository.save(orden);
    }

    public void delete(Long id){
        ordenRepository.deleteById(id);
    }
}
