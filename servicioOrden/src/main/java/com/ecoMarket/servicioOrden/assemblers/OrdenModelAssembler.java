package com.ecoMarket.servicioOrden.assemblers;


import com.ecoMarket.servicioOrden.controller.OrdenControllerV2;
import com.ecoMarket.servicioOrden.model.Orden;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class OrdenModelAssembler implements RepresentationModelAssembler<Orden, EntityModel<Orden>> {

    @Override
    public EntityModel<Orden> toModel(Orden orden) {
        return EntityModel.of(orden,
                linkTo(methodOn(OrdenControllerV2.class).getOrdenById(orden.getId())).withSelfRel(),
                linkTo(methodOn(OrdenControllerV2.class).getAllOrdenes()).withRel("ordenes"));
    }
}
