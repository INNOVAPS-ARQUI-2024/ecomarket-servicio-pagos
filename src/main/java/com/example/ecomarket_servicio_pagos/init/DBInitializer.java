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
            Pedido pedido1 = Pedido.builder()
                    .userId("user1")
                    .items(Arrays.asList("prod1", "serv1"))
                    .totalAmount(100.50)
                    .currency("USD")
                    .status("PENDING")
                    .paymentMethodId("pm1")
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();

            Pedido pedido2 = Pedido.builder()
                    .userId("user2")
                    .items(Arrays.asList("prod2", "serv2"))
                    .totalAmount(200.75)
                    .currency("EUR")
                    .status("COMPLETED")
                    .paymentMethodId("pm2")
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();

            pedidoRepository.save(pedido1);
            pedidoRepository.save(pedido2);
        }

        // Inicializar Transacciones
        if (transaccionRepository.count() == 0) {
            Transaccion transaccion1 = Transaccion.builder()
                    .buyerId("user1")
                    .sellerId("user2")
                    .itemId("prod1")
                    .itemType("PRODUCT")
                    .amount(100.50)
                    .currency("USD")
                    .status("PENDING")
                    .paymentMethodId("pm1")
                    .description("Compra de prueba 1")
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();

            Transaccion transaccion2 = Transaccion.builder()
                    .buyerId("user2")
                    .sellerId("user1")
                    .itemId("serv2")
                    .itemType("SERVICE")
                    .amount(150.75)
                    .currency("EUR")
                    .status("COMPLETED")
                    .paymentMethodId("pm2")
                    .description("Compra de prueba 2")
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();

            transaccionRepository.save(transaccion1);
            transaccionRepository.save(transaccion2);
        }

        System.out.println("Datos iniciales cargados en la base de datos.");
    }
}
