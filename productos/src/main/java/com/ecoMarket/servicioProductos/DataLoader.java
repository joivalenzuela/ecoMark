package com.ecoMarket.servicioProductos;

import com.ecoMarket.servicioProductos.model.Producto;
import com.ecoMarket.servicioProductos.repository.ProductoRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;


@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        for(int i = 0; i<50; i++) {
            Producto producto = new Producto();
            producto.setProdCode(String.valueOf(i + 1));
            producto.setCategoria(faker.commerce().department());
            producto.setNombre(faker.commerce().productName());
            producto.setMarca(faker.commerce().brand());
            producto.setStock(faker.number().numberBetween(0,999));
            producto.setPrecio(faker.number().numberBetween(1000,99999));
            producto.setEsEco(faker.random().nextBoolean());
            productoRepository.save(producto);
        }
        List<Producto> productos = productoRepository.findAll();
    }
}
