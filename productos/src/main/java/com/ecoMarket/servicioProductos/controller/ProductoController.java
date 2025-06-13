package com.ecoMarket.servicioProductos.controller;

import com.ecoMarket.servicioProductos.model.Producto;
import com.ecoMarket.servicioProductos.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
@RequestMapping("/api/v1/productos")
@Tag(name="Productos", description = "Operaciones relacionadas con los productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;


    @GetMapping()
    @Operation(summary = "Obtener todos los productos",
            description = "Obtiene una lista de todos los productos, con posibilidad de filtrar por categoria y/o marca")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos listados exitosamente",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema
                                    (schema = @Schema(implementation = Producto.class)),
                            examples = @ExampleObject(
                                    name = "Lista de Ejemplo",
                                    description = "Ejemplo de listado de los productos, sin usar filtros",
                                    value = """
                                            [
                                                {
                                                    "id": 1,
                                                    "prodCode": 1,
                                                    "nombre": "Hamburguesa Vacuno 100g",
                                                    "marca": "PF",
                                                    "precio": 750,
                                                    "categoria": "hamburguesas",
                                                    "esEco": false,
                                                    "stock": 134
                                                },
                                                {
                                                    "id": 2,
                                                    "prodCode": 2,
                                                    "nombre": "yoghurt chirimolla",
                                                    "marca": "Colun",
                                                    "precio": 326,
                                                    "categoria": "Lacteos y Quesos",
                                                    "esEco": true,
                                                    "stock": 456
                                                }
                                            ]
                                            """
                            ))),
            @ApiResponse(responseCode = "204", description = "No se encontraron Productos",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<List<Producto>> listar(
            @Parameter(
                    name = "Categoria",
                    description = "Categoria del producto",
                    required = false,
                    example = "Jugos",
                    schema = @Schema(type = "String")
                    )
            @RequestParam(required = false) String categoria,
            @Parameter(
                    name = "Marca" ,
                    description = "Marca del producto",
                    required = false,
                    example = "Watts",
                    schema = @Schema(type = "String")
            )
            @RequestParam(required = false) String marca)
    {
        List<Producto> productos;

        categoria = "".equals(categoria) ? null : categoria;
        marca = "".equals(marca) ? null : marca;

        if(categoria == null && marca == null){
            productos = productoService.findAll();
        }
        else {
            productos = productoService.buscarPorFiltro(categoria,marca);
        }

        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Integer id) {
        try {
            Producto producto = productoService.findById(id);
            return ResponseEntity.ok(producto);
        } catch ( Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/codigo/{prodCode}")
    public ResponseEntity<Producto> buscarPorCodigo(@PathVariable String prodCode) {
        try {
            Producto producto = productoService.findByProdCode(prodCode);
            return ResponseEntity.ok(producto);
        } catch ( Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto) {
        Producto productoNuevo = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }

    @PutMapping
    public ResponseEntity<Producto> actualizar(
            @PathVariable Integer id,
            @RequestBody Producto producto) {
        try {
            Producto pro = productoService.findById(id);
            pro.setId(producto.getId());
            pro.setCategoria(producto.getCategoria());
            pro.setNombre(producto.getNombre());
            pro.setEsEco(producto.getEsEco());
            pro.setPrecio(producto.getPrecio());
            pro.setStock(producto.getStock());

            productoService.save(pro);
            return ResponseEntity.ok(producto);
        } catch ( Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable long id) {
        try {
            productoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch ( Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/codigo/{prodCode}")
    public ResponseEntity<?> eliminarPorCodigo(@PathVariable String prodCode){
        try {
            productoService.deleteByProdCode(prodCode);
            return ResponseEntity.noContent().build();
        } catch ( Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }
}
