package com.example.ecomarket_servicio_pagos.utils;

import com.example.ecomarket_servicio_pagos.entity.Pedido;
import com.example.ecomarket_servicio_pagos.entity.Transaccion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestUtils {
// PEDIDOS
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Pedido mockPedido() {
        return Pedido.builder()
                .orderId("123")
                .userId("user1")
                .items(Arrays.asList("prod1", "serv1"))
                .totalAmount(100.5)
                .currency("USD")
                .status("PENDING")
                .paymentMethodId("pm1")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

    public static List<Pedido> mockPedidos() {
        Pedido pedido1 = Pedido.builder()
                .userId("user1")
                .items(Arrays.asList("prod1", "serv1"))
                .totalAmount(100.5)
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
        return Lists.newArrayList(pedido1, pedido2);
    }

    //TRANSACCIONES

    public static Transaccion mockTransaccion(){
        return Transaccion.builder()
                .transactionId("123")
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
    }

    public static List<Transaccion> mockTransacciones(){
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
        return Lists.newArrayList(transaccion1, transaccion2);
    }
}
