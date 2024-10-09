package com.example.ecomarket_servicio_pagos.controller;

import com.example.ecomarket_servicio_pagos.entity.Transaccion;
import com.example.ecomarket_servicio_pagos.service.TransaccionService;
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


@WebMvcTest(TransaccionController.class)
public class TransaccionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TransaccionService transaccionService;


    @Test
    public void testGivenRequestForallTransactionsWhenObtenerTransaccionesEndpointIsCalledThenReturnListOfTransacciones() throws Exception {
        //Arrange
        Mockito.when(transaccionService.obtenerTransacciones()).thenReturn(TestUtils.mockTransacciones());

        //Act
        RequestBuilder request = MockMvcRequestBuilders.get("/carrito")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        //Acr & Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGivenValidTransactionIDWhenObtenerTransaccionesEndpointIsCalledThenReturnListOfTransacciones() throws Exception {
        //Arrange
        Mockito.when(transaccionService.obtenerTransaccionPorId(eq("123"))).thenReturn(TestUtils.mockTransaccion());
        //Act
        RequestBuilder request = MockMvcRequestBuilders.get("/carrito/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        //Act & Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testGivenNonValidTransactionIDWhenObtenerTransaccionesEndpointIsCalledThenReturnNotFound() throws Exception {
        //Arrange
        Mockito.when(transaccionService.obtenerTransaccionPorId(eq("999"))).thenReturn(TestUtils.mockTransaccion());
        //Act
        RequestBuilder request = MockMvcRequestBuilders.get("/carrito/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        //Act & Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGivenTransaccionWhenTransaccionIsPostThenReturnOK() throws Exception {
        //Arrange
        Mockito.when(transaccionService.guardarTransaccion(any())).thenReturn(TestUtils.mockTransaccion());
        String json = TestUtils.asJsonString(TestUtils.mockTransaccion());
        //Act
        RequestBuilder request = MockMvcRequestBuilders.post("/carrito")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        //Act & Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGivenValidTransaccionWhenTransaccionIsPutThenReturnOk() throws Exception {
        //Arrange
        Mockito.when(transaccionService.actualizarTransaccion(eq("123"), any(Transaccion.class))).thenReturn(TestUtils.mockTransaccion());
        String json = TestUtils.asJsonString(TestUtils.mockTransaccion());

        //Act
        RequestBuilder request = MockMvcRequestBuilders.put("/carrito/123")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        //Act & Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGivenNonValidTransaccionWhenTransaccionIsPutThenReturnNotFound() throws Exception {
        //Arrange
        Mockito.when(transaccionService.actualizarTransaccion(eq("123"), any(Transaccion.class))).thenReturn(null);
        String json = TestUtils.asJsonString(TestUtils.mockTransaccion());

        //Act
        RequestBuilder request = MockMvcRequestBuilders.put("/carrito/123")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        //Act & Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGivenValidTransactionIdWhenEliminarTransaccionEndpointIsCalledThenReturnOK () throws Exception {
        // Arrange
        Mockito.when(transaccionService.eliminarTransaccion(eq("123"))).thenReturn(true);
        // Act
        RequestBuilder request = MockMvcRequestBuilders.delete("/carrito/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);


        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGivenNonValidTransactionIdWhenEliminarTransaccionEndpointIsCalledThenReturnNotFound () throws Exception {
        // Arrange
        Mockito.when(transaccionService.eliminarTransaccion(eq("123"))).thenReturn(false);
        // Act
        RequestBuilder request = MockMvcRequestBuilders.delete("/carrito/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);


        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }



}
