package com.ecoMarket.servicioProductos.controller;

import com.ecoMarket.servicioProductos.model.Producto;
import com.ecoMarket.servicioProductos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping()
    public ResponseEntity<List<Producto>> listar(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String marca) {

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
