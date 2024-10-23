package com.example.ecomarket_servicio_pagos.controller;

import com.example.ecomarket_servicio_pagos.entity.Carrito;
import com.example.ecomarket_servicio_pagos.entity.Pedido;
import com.example.ecomarket_servicio_pagos.service.CarritoService;
import com.example.ecomarket_servicio_pagos.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@WebMvcTest(CarritoController.class)
public class CarritoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarritoService carritoService;

    @Test
    public void testAgregarPedidoAlCarrito() throws Exception {
        // Arrange
        Pedido pedido = TestUtils.mockPedido();
        Carrito carrito = TestUtils.mockCarrito();
        Mockito.when(carritoService.agregarPedidoAlCarrito(eq("123"), any(Pedido.class)))
                .thenReturn(carrito);
        String json = TestUtils.asJsonString(pedido);

        // Act
        RequestBuilder request = MockMvcRequestBuilders.post("/carrito/123/agregar")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        // Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testObtenerCarrito() throws Exception {
        // Arrange
        Carrito carrito = TestUtils.mockCarrito();
        Mockito.when(carritoService.obtenerCarrito(eq("123")))
                .thenReturn(carrito);

        // Act
        RequestBuilder request = MockMvcRequestBuilders.get("/carrito/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        // Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testVaciarCarrito() throws Exception {
        // Arrange
        Mockito.doNothing().when(carritoService).vaciarCarrito(eq("123"));

        // Act
        RequestBuilder request = MockMvcRequestBuilders.delete("/carrito/123/vaciar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        // Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testEliminarPedidoDelCarrito() throws Exception {
        // Arrange
        Carrito carrito = TestUtils.mockCarrito();
        Mockito.when(carritoService.eliminarPedidoDelCarrito(eq("123"), eq("456")))
                .thenReturn(carrito);

        // Act
        RequestBuilder request = MockMvcRequestBuilders.delete("/carrito/123/eliminar-pedido/456")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        // Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
