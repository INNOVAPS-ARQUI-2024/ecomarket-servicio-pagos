package com.example.ecomarket_servicio_pagos.controller;

import com.example.ecomarket_servicio_pagos.service.PedidoService;
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


@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PedidoService pedidoService;

    @Test
    public void testGivenRequestForallOrdersWhenObtenerPedidosEndpointIsCalledThenReturnListOfPedidos () throws Exception {
        // Arrange
        Mockito.when(pedidoService.obtenerPedidos()).thenReturn(TestUtils.mockPedidos());
        // Act
        RequestBuilder request = MockMvcRequestBuilders.get("/ecomarket-pagos/pedido")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGivenValidOrderIdWhenObtenerPedidosEndpointIsCalledThenReturnListOfPedidos () throws Exception {
        // Arrange
        Mockito.when(pedidoService.obtenerPedidoPorId(eq("123"))).thenReturn(TestUtils.mockPedido());
        // Act
        RequestBuilder request = MockMvcRequestBuilders.get("/ecomarket-pagos/pedido/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);


        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGivenValidPedidoWhenPedidoIsPostThenReturnOk () throws Exception {
        // Arrange
        Mockito.when(pedidoService.guardarPedido(any())).thenReturn(TestUtils.mockPedido());
        String json = TestUtils.asJsonString(TestUtils.mockPedido());
        // Act
        RequestBuilder request = MockMvcRequestBuilders.post("/ecomarket-pagos/pedido")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
