package com.example.ecomarket_servicio_pagos.repository;


import com.example.ecomarket_servicio_pagos.entity.Pedido;
import com.example.ecomarket_servicio_pagos.utils.TestUtils;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
@SpringBootApplication
public class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Before
    public void setUp() {
        pedidoRepository.save(TestUtils.mockPedido());
    }

    @Test
    public void testGivenValidOrderIDWhenFindByIDisCalledThenReturnPedidoEntity(){
        //Act
        Optional<Pedido> pedido = pedidoRepository.findById("12345");

        //Assert
        assertTrue(pedido.isPresent());
        assertEquals("12345", pedido.get().getOrderId());
    }
}
