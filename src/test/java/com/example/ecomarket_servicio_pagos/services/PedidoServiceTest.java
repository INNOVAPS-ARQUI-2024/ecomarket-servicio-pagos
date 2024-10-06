package com.example.ecomarket_servicio_pagos.services;

import com.example.ecomarket_servicio_pagos.entity.Pedido;
import com.example.ecomarket_servicio_pagos.repository.PedidoRepository;
import com.example.ecomarket_servicio_pagos.service.PedidoService;
import com.example.ecomarket_servicio_pagos.utils.TestUtils;
import jakarta.ws.rs.core.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootApplication
@ContextConfiguration(classes = Application.class)
public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;
    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGivenRepositoryLoadedWithPedidosWhenObtenerPedidosIsCalledThenReturnAllPedidos() {
        // Arrange
        Mockito.when(pedidoRepository.findAll()).thenReturn(TestUtils.mockPedidos());

        // Act
        List<Pedido> response = pedidoService.obtenerPedidos();

        // Assert
        assertNotNull(response);
        assertEquals(2, response.size());
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    public void testGivenValidIDWhenObtenerPedidosPorIDIsCalledThenReturnPedido(){
        //Arrange
        Mockito.when(pedidoRepository.findById(eq("123"))).thenReturn(Optional.ofNullable(TestUtils.mockPedido()));

        //Act
        Pedido response = pedidoService.obtenerPedidoPorId("123");

        //Assert
        assertNotNull(response);
        verify(pedidoRepository, times(1)).findById(eq("123"));
        assertEquals("123", response.getOrderId());
    }

    @Test
    public void testGivenNonValidIDWhenObtenerPedidosPorIDIsCalledThenReturnNull(){
        //Arrange
        Mockito.when(pedidoRepository.findById(eq("123"))).thenReturn(Optional.ofNullable(TestUtils.mockPedido()));

        //Act
        Pedido response = pedidoService.obtenerPedidoPorId("1234");

        //Assert
        assertNull(response);
        verify(pedidoRepository, times(1)).findById(eq("1234"));
    }

    @Test
    public void testGivenValidPedidoObjectWhenGuardarPedidoIsCallThenReturnPedido(){
        //Arrange
        Mockito.when(pedidoRepository.save(any(Pedido.class))).thenReturn(TestUtils.mockPedido());

        //Act
        Pedido response = pedidoService.guardarPedido(TestUtils.mockPedido());

        //Assert
        assertNotNull(response);
        verify(pedidoRepository, times(1)).save(any(Pedido.class));

    }
}
