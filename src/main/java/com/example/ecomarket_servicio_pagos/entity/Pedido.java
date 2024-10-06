package com.example.ecomarket_servicio_pagos.entity;

import java.util.Date;
import java.util.List;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "pedidos")
@Setter
@Getter
@Builder
public class Pedido {

    @Id
    private String orderId;
    private String userId;
    private List<String> items; // array de productIds/serviceIds
    private double totalAmount;  // monto total
    private String currency;      // moneda
    private String status;        // estado
    private String paymentMethodId;
    private Date createdAt;      // fecha de creación
    private Date updatedAt;      // fecha de actualización
}

