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
import static org.mockito.Mockito.*;

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

    @Test
    public void testGivenValidPedidoObjectWhenModificarPedidoIsCallThenReturnUpdatePedido(){
        //Arrange
        String pedidoID = "123";
        Pedido pedidoExiste = TestUtils.mockPedido();
        Pedido updatePedido = TestUtils.mockPedido();

        Mockito.when(pedidoRepository.findById(eq(pedidoID))).thenReturn(Optional.ofNullable(pedidoExiste));
        Mockito.when(pedidoRepository.save(any(Pedido.class))).thenReturn(updatePedido);

        //Act
        Pedido response = pedidoService.actualizarPedido(pedidoID, updatePedido);

        //Assert
        assertNotNull(response);
        assertEquals(updatePedido.getOrderId(), response.getOrderId());
        verify(pedidoRepository, times(1)).findById(eq(pedidoID));
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    public void testGivenNonValidPedidoObjectWhenModificarPedidoIsCallThenReturnNull(){
        //Arrange
        Pedido updatePedido = TestUtils.mockPedido();

        Mockito.when(pedidoRepository.findById(eq("123"))).thenReturn(Optional.ofNullable(TestUtils.mockPedido()));

        //Act
        Pedido response = pedidoService.actualizarPedido("1234", updatePedido);

        //Assert
        assertNull(response);
        verify(pedidoRepository, times(1)).findById(eq("1234"));
        verify(pedidoRepository, never()).save(any(Pedido.class));

    }

    @Test
    public void testGivenExistingPedidoWhenEliminarPedidoIsCallThenReturnTrue(){
        //Arrange

        Mockito.when(pedidoRepository.existsById(eq("123"))).thenReturn(true);
        Mockito.doNothing().when(pedidoRepository).deleteById(eq("123"));

        //Act
        boolean response = pedidoService.eliminarPedido("123");

        //Assert
        assertTrue(response);
        verify(pedidoRepository, times(1)).existsById(eq("123"));
        verify(pedidoRepository, times(1)).deleteById(eq("123"));

    }
}
