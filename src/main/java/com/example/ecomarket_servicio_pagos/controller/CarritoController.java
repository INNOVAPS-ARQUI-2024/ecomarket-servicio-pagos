package com.example.ecomarket_servicio_pagos.controller;

import com.example.ecomarket_servicio_pagos.entity.Carrito;
import com.example.ecomarket_servicio_pagos.entity.Pedido;
import com.example.ecomarket_servicio_pagos.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
@CrossOrigin(origins = "http://localhost:4200")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping("/{userId}/agregar")
    public Carrito agregarPedidoAlCarrito(@PathVariable String userId, @RequestBody Pedido pedido) {
        return carritoService.agregarPedidoAlCarrito(userId, pedido);
    }

    @GetMapping("/{userId}")
    public Carrito obtenerCarrito(@PathVariable String userId) {
        return carritoService.obtenerCarrito(userId);
    }

    @DeleteMapping("/{userId}/vaciar")
    public void vaciarCarrito(@PathVariable String userId) {
        carritoService.vaciarCarrito(userId);
    }

    @DeleteMapping("/{userId}/eliminar-pedido/{orderId}")
    public Carrito eliminarPedidoDelCarrito(@PathVariable String userId, @PathVariable String orderId) {
        return carritoService.eliminarPedidoDelCarrito(userId, orderId);
    }

}
