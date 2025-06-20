package com.ecoMarket.servicioOrden.service;

import com.ecoMarket.servicioOrden.model.Orden;
import com.ecoMarket.servicioOrden.repository.OrdenRepository;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OrdenServiceTest {

    //Inyecta el servicio de Orden para ser probado.
    //Crea un mock del repositorio de Orden para simular su comportamiento.
    @Mock
    private OrdenRepository ordenRepository;

    @InjectMocks
    private OrdenService ordenService;

    @Test
    public void testfindAll() {

        //crear orden falsa con datos reales
        Orden ordenMock = new Orden();
        ordenMock.setId(1L);
        ordenMock.setEstado("Pendiente");
        ordenMock.setTotal(15000);
        ordenMock.setFecha(new Date());

        //Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Orden.
        when(ordenRepository.findAll()).thenReturn(List.of(ordenMock));

        //Llama al metodo findAll() del servicio.
        List<Orden> ordenes = ordenService.findAll();


        //Verifica que la lista que devuelva no sea nula y contenga exactamente una Orden.
        assertNotNull(ordenes);
        assertEquals(1, ordenes.size());
        assertEquals("Pendiente", ordenes.get(0).getEstado());
        assertEquals(15000, ordenes.get(0).getTotal());

    }


}