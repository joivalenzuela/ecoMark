package com.ecoMarket.servicioProductos;

import com.ecoMarket.servicioProductos.model.Producto;
import com.ecoMarket.servicioProductos.repository.ProductoRepository;
import com.ecoMarket.servicioProductos.service.ProductoService;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @MockitoBean
    private ProductoRepository productoRepository;

    @Test
    public void testFindAll() {
         when(productoRepository.findAll()).thenReturn(List.of(new Producto(1,"1","Caja",
                 "Box'ers",3999,"contenedor",true,99)));

         List<Producto> productos = productoService.findAll();

         assertNotNull(productos);
         assertEquals(1,productos.size());
    }

    @Test
    public void testFindbyId() {
        long id = 1;
        Producto producto = new Producto(id,"1","Caja",
                "Box'ers",3999,"contenedor",true,99);
        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));
        Producto found = productoService.findById(id);
        assertNotNull(found);
        assertEquals(id,found.getId());
    }

    @Test
    public void testSave() {
        Producto producto = new Producto(1,"1","Caja",
                "Box'ers",3999,"contenedor",true,99);
        when(productoRepository.save(producto)).thenReturn(producto);
        Producto saved = productoService.save(producto);
        assertNotNull(saved);
        assertEquals("Caja",saved.getNombre());
    }

    @Test
    public void testDeleteById() {
        long id = 1;
        doNothing().when(productoRepository).deleteById(id);
        productoService.delete(id);
        verify(productoRepository,times(1)).deleteById(id);
    }




}
