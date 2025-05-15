package com.ecoMarket.servicioOrden.controller;


import com.ecoMarket.servicioOrden.model.Orden;
import com.ecoMarket.servicioOrden.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping
    public ResponseEntity<List<Orden>> Listar(){
        List<Orden> ordenes = ordenService.findAll();
        if(ordenes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ordenes);
    }

    @PostMapping
    public ResponseEntity<Orden> guardar(@RequestBody Orden orden){
        Orden productoNuevo = ordenService.save(orden);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Orden> buscar(@PathVariable Long id){
        try {
            Orden orden = ordenService.findById(id);
            return ResponseEntity.ok(orden);
        } catch ( Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orden> actualizar(@PathVariable Long id, @RequestBody Orden orden) {
        try {
            Orden or = ordenService.findById(id);
            or.setId(id);
            or.setEstado(orden.getEstado());
            or.setFecha(orden.getFecha());
            or.setTotal(orden.getTotal());

            ordenService.save(or);
            return ResponseEntity.ok(orden);
        }catch ( Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            ordenService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
