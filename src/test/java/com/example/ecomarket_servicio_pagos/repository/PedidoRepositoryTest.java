package com.example.ecomarket_servicio_pagos.repository;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.example.ecomarket_servicio_pagos.entity.Pedido;
import com.example.ecomarket_servicio_pagos.utils.TestUtils;

@DataMongoTest
@SpringBootApplication
public class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @BeforeEach
    public void setUp() {
        pedidoRepository.save(TestUtils.mockPedido());
    }

    @Test
    public void testGivenValidOrderIDWhenFindByIDisCalledThenReturnPedidoEntity(){
        //Act
        Optional<Pedido> pedido = pedidoRepository.findById("123");

        //Assert
        assertTrue(pedido.isPresent());
        assertEquals("123", pedido.get().getOrderId());
    }
}
