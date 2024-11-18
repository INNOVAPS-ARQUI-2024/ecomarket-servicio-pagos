package com.example.ecomarket_servicio_pagos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomarket_servicio_pagos.entity.Transaccion;
import com.example.ecomarket_servicio_pagos.repository.TransaccionRepository;


@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    public List<Transaccion> obtenerTransacciones() {
        return transaccionRepository.findAll();
    }

    public Transaccion obtenerTransaccionPorId(String id) {
        return transaccionRepository.findById(id).orElse(null);
    }

    public Transaccion guardarTransaccion(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    public Transaccion actualizarTransaccion(String id, Transaccion detallesTransaccion) {
        Transaccion transaccion = transaccionRepository.findById(id).orElse(null);
        if (transaccion != null) {
            transaccion.setAmount(detallesTransaccion.getAmount());
            transaccion.setCurrency(detallesTransaccion.getCurrency());
            transaccion.setStatus(detallesTransaccion.getStatus());
            transaccion.setDescription(detallesTransaccion.getDescription());
            return transaccionRepository.save(transaccion);
        } else {
            return null;
        }
    }

    public boolean eliminarTransaccion(String id) {
        if (transaccionRepository.existsById(id)) {
            transaccionRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    public List<Transaccion> obtenerArticulosEnCarrito() {
        return transaccionRepository.findByStatus("IN_CART");
    }
    // Vaciar el carrito
    public boolean vaciarCarrito() {
        List<Transaccion> transaccionesEnCarrito = transaccionRepository.findByStatus("IN_CART");
        if (!transaccionesEnCarrito.isEmpty()) {
            transaccionRepository.deleteAll(transaccionesEnCarrito);
            return true;
        }
        return false;
    }
}
