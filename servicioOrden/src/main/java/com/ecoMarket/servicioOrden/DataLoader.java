package com.ecoMarket.servicioOrden;

import com.ecoMarket.servicioOrden.model.*;
import com.ecoMarket.servicioOrden.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Profile("default")
@Component
public class DataLoader implements CommandLineRunner {
    LocalDate fechaInicial = LocalDate.now();

    @Autowired
    private OrdenRepository ordenRepository;


    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();
        String[] estados = {"Pendiente", "Procesando", "Enviado", "Entregado", "Cancelado"};
        //Generar Orden
        for (int i = 0; i < 5; i++) {
            LocalDate fechaOrden = fechaInicial.plusDays(i);
            Date fechaDate = Date.from(fechaOrden.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Orden orden = new Orden();
            orden.setFecha(fechaDate);
            String estadoAleatorio = estados[random.nextInt(estados.length)];
            orden.setEstado(estadoAleatorio);
            orden.setTotal(faker.number().numberBetween(9990,20990));
            ordenRepository.save(orden);
        }
    }
}