package com.example.ecomarket_servicio_pagos.init;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ecomarket_servicio_pagos.entity.Pedido;
import com.example.ecomarket_servicio_pagos.entity.Transaccion;
import com.example.ecomarket_servicio_pagos.repository.PedidoRepository;
import com.example.ecomarket_servicio_pagos.repository.TransaccionRepository;

@Component
public class DBInitializer implements CommandLineRunner {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Override
    public void run(String... args) throws Exception {
        
        // Inicializar Pedidos
        if (pedidoRepository.count() == 0) {
            Pedido pedido1 = new Pedido();
            pedido1.setUserId("user1");
            pedido1.setItems(Arrays.asList("prod1", "serv1"));
            pedido1.setTotalAmount(100.50);
            pedido1.setCurrency("USD");
            pedido1.setStatus("PENDING");
            pedido1.setPaymentMethodId("pm1");
            pedido1.setCreatedAt(new Date());
            pedido1.setUpdatedAt(new Date());

            Pedido pedido2 = new Pedido();
            pedido2.setUserId("user2");
            pedido2.setItems(Arrays.asList("prod2", "serv2"));
            pedido2.setTotalAmount(200.75);
            pedido2.setCurrency("EUR");
            pedido2.setStatus("COMPLETED");
            pedido2.setPaymentMethodId("pm2");
            pedido2.setCreatedAt(new Date());
            pedido2.setUpdatedAt(new Date());

            pedidoRepository.save(pedido1);
            pedidoRepository.save(pedido2);
        }


        // Inicializar Transacciones
        if (transaccionRepository.count() == 0) {
            Transaccion transaccion1 = new Transaccion();
            transaccion1.setBuyerId("user1");
            transaccion1.setSellerId("user2");
            transaccion1.setItemId("prod1");
            transaccion1.setItemType("PRODUCT");
            transaccion1.setAmount(100.50);
            transaccion1.setCurrency("USD");
            transaccion1.setStatus("PENDING");
            transaccion1.setPaymentMethodId("pm1");
            transaccion1.setDescription("Compra de prueba 1");
            transaccion1.setCreatedAt(new Date());
            transaccion1.setUpdatedAt(new Date());

            Transaccion transaccion2 = new Transaccion();
            transaccion2.setBuyerId("user2");
            transaccion2.setSellerId("user1");
            transaccion2.setItemId("serv2");
            transaccion2.setItemType("SERVICE");
            transaccion2.setAmount(150.75);
            transaccion2.setCurrency("EUR");
            transaccion2.setStatus("COMPLETED");
            transaccion2.setPaymentMethodId("pm2");
            transaccion2.setDescription("Compra de prueba 2");
            transaccion2.setCreatedAt(new Date());
            transaccion2.setUpdatedAt(new Date());

            transaccionRepository.save(transaccion1);
            transaccionRepository.save(transaccion2);
        }

        System.out.println("Datos iniciales cargados en la base de datos.");
    }
}

