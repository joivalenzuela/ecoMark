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

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @MockitoBean
    private ProductoRepository productoRepository;

    @Test
    public void testFindAll() {
         Mockito.when(productoRepository.findAll()).thenReturn(List.of(new Producto(1,"1","nombre","marca",1000,"categoria",true,10)));

         List<Producto> productos = productoService.findAll();

         assertNotNull(productos);
         assertEquals(1,productos.size());
    }
}
