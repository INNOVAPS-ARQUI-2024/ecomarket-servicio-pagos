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
@RequestMapping("/ecomarket-carrito")
@CrossOrigin(origins = "http://localhost:4200")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping("/transacciones")
    public List<Transaccion> obtenerTransacciones() {
        return transaccionService.obtenerTransacciones();
    }

    @GetMapping("/transaccion/{id}")
    public ResponseEntity<Transaccion> obtenerTransaccionPorId(@PathVariable String id) {
        Transaccion transaccion = transaccionService.obtenerTransaccionPorId(id);
        if (transaccion != null) {
            return ResponseEntity.ok(transaccion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/transaccion")
    public Transaccion crearTransaccion(@RequestBody Transaccion transaccion) {
        return transaccionService.guardarTransaccion(transaccion);
    }

    @PutMapping("/transaccion/{id}")
    public ResponseEntity<Transaccion> actualizarTransaccion(@PathVariable String id, @RequestBody Transaccion detallesTransaccion) {
        Transaccion transaccionActualizada = transaccionService.actualizarTransaccion(id, detallesTransaccion);
        if (transaccionActualizada != null) {
            return ResponseEntity.ok(transaccionActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/transaccion/{id}")
    public ResponseEntity<Void> eliminarTransaccion(@PathVariable String id) {
        boolean fueEliminado = transaccionService.eliminarTransaccion(id);
        if (fueEliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}