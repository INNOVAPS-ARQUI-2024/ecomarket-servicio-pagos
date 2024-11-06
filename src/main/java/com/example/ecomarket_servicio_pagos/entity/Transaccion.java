package com.example.ecomarket_servicio_pagos.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document(collection = "transacciones")
@Builder
public class Transaccion {

    @Id
    private String transactionId;
    private String buyerId;
    private String sellerId;
    private String itemId;
    private String itemType;
    private double amount;
    private String currency;
    private String status;
    private String paymentMethodId;
    private String description;
    private Date createdAt;
    private Date updatedAt;
}
