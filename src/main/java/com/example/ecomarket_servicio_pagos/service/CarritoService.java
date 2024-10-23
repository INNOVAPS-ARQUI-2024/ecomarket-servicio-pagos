package com.example.ecomarket_servicio_pagos.service;

import com.example.ecomarket_servicio_pagos.entity.Carrito;
import com.example.ecomarket_servicio_pagos.entity.Pedido;
import com.example.ecomarket_servicio_pagos.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public Carrito agregarPedidoAlCarrito(String userId, Pedido pedido) {
        Carrito carrito = carritoRepository.findByUserId(userId);
        if (carrito == null) {
            carrito = Carrito.builder()
                    .userId(userId)
                    .pedidos(Arrays.asList(pedido))
                    .totalAmount(pedido.getTotalAmount())
                    .build();
        } else {
            carrito.agregarPedido(pedido);
        }
        return carritoRepository.save(carrito);
    }

    public Carrito obtenerCarrito(String userId) {
        return carritoRepository.findByUserId(userId);
    }

    public void vaciarCarrito(String userId) {
        Carrito carrito = carritoRepository.findByUserId(userId);
        if (carrito != null) {
            carrito.vaciarCarrito();
            carritoRepository.save(carrito);
        }
    }

    public Carrito eliminarPedidoDelCarrito(String userId, String orderId) {
        Carrito carrito = carritoRepository.findByUserId(userId);
        if (carrito != null) {
            // Elimina el pedido cuyo orderId coincida

            carrito.getPedidos().removeIf(pedido -> orderId.equals(pedido.getOrderId()));
            // Recalcula el total del carrito
            double nuevoTotal = carrito.getPedidos().stream()
                                      .mapToDouble(Pedido::getTotalAmount)
                                      .sum();
            carrito.setTotalAmount(nuevoTotal);
    
            // Guarda los cambios en el repositorio
            carritoRepository.save(carrito);
        }
        return carrito;
    }
        
    
}
