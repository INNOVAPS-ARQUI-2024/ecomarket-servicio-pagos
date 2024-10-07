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
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGivenRepositoryLoadedWithTransaccionesWhenObtenerTransaccionesIsCalledThenReturnAllTransacciones(){
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
    public void testGivenValidIDWhenObtenerTransaccionesporIDisCalledThenReturnTransaccion(){
        //Arrange
        Mockito.when(transaccionRepository.findById("123")).thenReturn(Optional.ofNullable(TestUtils.mockTransaccion()));

        //Act
        Transaccion response = transaccionService.obtenerTransaccionPorId("123");

        //Assert
        assertNotNull(response);

    }

}
