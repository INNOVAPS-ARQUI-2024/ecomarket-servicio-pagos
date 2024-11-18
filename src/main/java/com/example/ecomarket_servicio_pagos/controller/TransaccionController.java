package com.example.ecomarket_servicio_pagos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecomarket_servicio_pagos.entity.Transaccion;
import com.example.ecomarket_servicio_pagos.service.TransaccionService;

@RestController
@RequestMapping("/carrito")
@CrossOrigin(origins = "http://localhost:4200")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping
    public List<Transaccion> obtenerTransacciones() {
        return transaccionService.obtenerTransacciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> obtenerTransaccionPorId(@PathVariable String id) {
        Transaccion transaccion = transaccionService.obtenerTransaccionPorId(id);
        if (transaccion != null) {
            return ResponseEntity.ok(transaccion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Transaccion crearTransaccion(@RequestBody Transaccion transaccion) {
        return transaccionService.guardarTransaccion(transaccion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaccion> actualizarTransaccion(@PathVariable String id, @RequestBody Transaccion detallesTransaccion) {
        Transaccion transaccionActualizada = transaccionService.actualizarTransaccion(id, detallesTransaccion);
        if (transaccionActualizada != null) {
            return ResponseEntity.ok(transaccionActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTransaccion(@PathVariable String id) {
        boolean fueEliminado = transaccionService.eliminarTransaccion(id);
        if (fueEliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Transaccion> obtenerArticulosCarrito() {
        return transaccionService.obtenerArticulosEnCarrito();
    }

    // Agregar un artículo al carrito
    @PostMapping("/agregar")
    public Transaccion agregarArticuloAlCarrito(@RequestBody Transaccion transaccion) {
        transaccion.setStatus("IN_CART");
        return transaccionService.guardarTransaccion(transaccion);
    }

    // Actualizar la cantidad de un artículo en el carrito
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Transaccion> actualizarArticuloEnCarrito(@PathVariable String id, @RequestBody Transaccion detallesTransaccion) {
        Transaccion transaccionActualizada = transaccionService.actualizarTransaccion(id, detallesTransaccion);
        if (transaccionActualizada != null) {
            return ResponseEntity.ok(transaccionActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un artículo específico del carrito
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarArticuloDelCarrito(@PathVariable String id) {
        boolean fueEliminado = transaccionService.eliminarTransaccion(id);
        if (fueEliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Vaciar todo el carrito
    @DeleteMapping("/vaciar")
    public ResponseEntity<Void> vaciarCarrito() {
        boolean fueVaciado = transaccionService.vaciarCarrito();
        if (fueVaciado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(500).build();
        }
    }
}