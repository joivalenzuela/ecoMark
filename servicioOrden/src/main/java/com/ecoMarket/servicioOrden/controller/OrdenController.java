package com.ecoMarket.servicioOrden.controller;


import com.ecoMarket.servicioOrden.model.Orden;
import com.ecoMarket.servicioOrden.service.OrdenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ordenes")
@Tag(name = "Ordenes",description = "Operaciones relacionadas con las ordenes.")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping
    @Operation(summary = "Listar ordenes",description = "Obtiene una lista con las ordenes")
    public ResponseEntity<List<Orden>> Listar(){
        List<Orden> ordenes = ordenService.findAll();
        if(ordenes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ordenes);
    }

    @PostMapping
    @Operation(summary = "Guardar ordenes", description = "Guarda una orden")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden guardada exitosamente.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Orden.class)))
    })
    public ResponseEntity<Orden> guardar(@RequestBody Orden orden){
        Orden productoNuevo = ordenService.save(orden);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Buscar una orden",description = "Obtiene una orden por su codigo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Orden encontrada exitosamente"),
            @ApiResponse(responseCode = "404",description = "Orden no encontrada.")
    })
    public ResponseEntity<Orden> buscar(@PathVariable Long id){
        try {
            Orden orden = ordenService.findById(id);
            return ResponseEntity.ok(orden);
        } catch ( Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una orden", description = "Actualiza una ordenn existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden actualizada correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Orden.class))),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada.")
    })

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
    @Operation(summary = "Eliminar una orden", description = "Elimina una orden existente por su codigo.")
    @ApiResponses (value = {
            @ApiResponse(responseCode = "200", description = "Orden eliminada exitosamente."),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada.")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            ordenService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
