package com.ecoMarket.servicioOrden.controller;


import com.ecoMarket.servicioOrden.assemblers.OrdenModelAssembler;
import com.ecoMarket.servicioOrden.model.Orden;
import com.ecoMarket.servicioOrden.service.OrdenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("/api/v2/ordenes")
public class OrdenControllerV2 {

    @Autowired
    private OrdenService ordenService;

    @Autowired
    private OrdenModelAssembler Assembler;


    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Orden>> getAllOrdenes() {
        List<EntityModel<Orden>> ordenes = ordenService.findAll().stream()
                .map(Assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(ordenes,
                linkTo(methodOn(OrdenControllerV2.class).getAllOrdenes()).withSelfRel());


    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Orden> getOrdenById(@PathVariable long id) {
        Orden orden = ordenService.findById(id);
        return Assembler.toModel(orden);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Orden>> addOrden(@RequestBody Orden orden) {
        Orden newOrden = ordenService.save(orden);
        return ResponseEntity
                .created(linkTo(methodOn(OrdenControllerV2.class).getOrdenById(newOrden.getId())).toUri())
                .body(Assembler.toModel(newOrden));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Orden>> updateOrden(@PathVariable long id, @RequestBody Orden orden) {
        orden.setId(id);
        Orden updatedOrden = ordenService.save(orden);
        return ResponseEntity
                .ok(Assembler.toModel(updatedOrden));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteOrden(@PathVariable long id) {
        ordenService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
