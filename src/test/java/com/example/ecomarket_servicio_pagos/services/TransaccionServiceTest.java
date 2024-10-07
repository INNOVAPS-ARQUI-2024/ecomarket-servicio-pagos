package com.example.ecomarket_servicio_pagos.services;


import com.example.ecomarket_servicio_pagos.entity.Transaccion;
import com.example.ecomarket_servicio_pagos.repository.TransaccionRepository;
import com.example.ecomarket_servicio_pagos.service.TransaccionService;
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
public class TransaccionServiceTest {

    @Mock
    private TransaccionRepository transaccionRepository;
    @InjectMocks
    private TransaccionService transaccionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGivenRepositoryLoadedWithTransaccionesWhenObtenerTransaccionesIsCalledThenReturnAllTransacciones() {
        //Arrange
        Mockito.when(transaccionRepository.findAll()).thenReturn(TestUtils.mockTransacciones());

        //Act
        List<Transaccion> response = transaccionService.obtenerTransacciones();

        //Assert
        assertNotNull(response);
        assertEquals(2, response.size());
        verify(transaccionRepository, times(1)).findAll();

    }

    @Test
    public void testGivenValidIDWhenObtenerTransaccionesporIDisCalledThenReturnTransaccion() {
        //Arrange
        Mockito.when(transaccionRepository.findById("123")).thenReturn(Optional.ofNullable(TestUtils.mockTransaccion()));

        //Act
        Transaccion response = transaccionService.obtenerTransaccionPorId("123");

        //Assert
        assertNotNull(response);
        verify(transaccionRepository, times(1)).findById(eq("123"));
        assertEquals("123", response.getTransactionId());

    }

    @Test
    public void testGivenNonValidIDWhenObtenerTransaccionesporIDisCalledThenReturnNull() {
        //Arrange
        Mockito.when(transaccionRepository.findById("123")).thenReturn(Optional.ofNullable(TestUtils.mockTransaccion()));

        //Act
        Transaccion response = transaccionService.obtenerTransaccionPorId("1234");

        //Assert
        assertNull(response);
        verify(transaccionRepository, times(1)).findById(eq("1234"));
    }

    @Test
    public void testGivenValidTransaccionObjectWhenGuardarTransaccionIsCallThenReturnTransaccion() {
        //Arrange
        Mockito.when(transaccionRepository.save(any(Transaccion.class))).thenReturn(TestUtils.mockTransaccion());

        //Act
        Transaccion response = transaccionService.guardarTransaccion(TestUtils.mockTransaccion());

        //Assert
        assertNotNull(response);
        verify(transaccionRepository, times(1)).save(any(Transaccion.class));
    }

    @Test
    public void testGivenValidTransaccionObjectWhenActualizarTransaccionIsCallThenReturnUpdateTransaccion() {
        //Arrange
        String transaccionID = "123";
        Transaccion transaccionExist = TestUtils.mockTransaccion();
        Transaccion updateTransaccion = TestUtils.mockTransaccion();

        Mockito.when(transaccionRepository.findById(eq(transaccionID))).thenReturn(Optional.ofNullable(transaccionExist));
        Mockito.when(transaccionRepository.save(any(Transaccion.class))).thenReturn(updateTransaccion);

        //Act
        Transaccion response = transaccionService.actualizarTransaccion(transaccionID, updateTransaccion);

        //Assert
        assertNotNull(response);
        assertEquals(updateTransaccion.getTransactionId(), response.getTransactionId());
        verify(transaccionRepository, times(1)).findById(eq(transaccionID));
        verify(transaccionRepository, times(1)).save(any(Transaccion.class));

    }

    @Test
    public void testGivenNonValidTransaccionObjectWhenActualizarTransaccionIsCallThenReturnNull() {
        //Arrange
        Transaccion updateTransaccion = TestUtils.mockTransaccion();

        Mockito.when(transaccionRepository.findById(eq("123"))).thenReturn(Optional.ofNullable(TestUtils.mockTransaccion()));

        //Act
        Transaccion response = transaccionService.actualizarTransaccion("1234", updateTransaccion);

        //Assert
        assertNull(response);
        verify(transaccionRepository, times(1)).findById(eq("1234"));
        verify(transaccionRepository, never()).save(any(Transaccion.class));


    }

    @Test
    public void tstGivenExistingTransaccionWhenEliminarTransaccionIsCallThenReturnTrue(){
        //Arrange
        Mockito.when(transaccionRepository.existsById(eq("123"))).thenReturn(true);

        //Act
        boolean response = transaccionService.eliminarTransaccion("123");

        //Assert
        assertTrue(response);
        verify(transaccionRepository, times(1)).existsById(eq("123"));
        verify(transaccionRepository, times(1)).deleteById("123");
    }





}
