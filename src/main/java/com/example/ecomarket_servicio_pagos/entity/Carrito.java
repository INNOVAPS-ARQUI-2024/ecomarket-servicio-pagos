package com.example.ecomarket_servicio_pagos.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document(collection = "carritos")
@Builder
public class Carrito {

    @Id
    private String carritoId;
    private String userId; // El carrito estará vinculado a un usuario
    private List<Pedido> pedidos; // Lista de pedidos en el carrito
    private double totalAmount; // Monto total del carrito

    public void agregarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
        this.totalAmount += pedido.getTotalAmount();
    }

    public void vaciarCarrito() {
        this.pedidos.clear();
        this.totalAmount = 0;
    }
    public void eliminarPedido(String orderId) {
        this.pedidos.removeIf(pedido -> pedido.getOrderId().equals(orderId));
        recalcularTotal();
    }
    
    private void recalcularTotal() {
        this.totalAmount = this.pedidos.stream().mapToDouble(Pedido::getTotalAmount).sum();
    }
    
}
