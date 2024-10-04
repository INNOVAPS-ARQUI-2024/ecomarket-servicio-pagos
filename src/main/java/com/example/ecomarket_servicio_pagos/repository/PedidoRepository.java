package com.example.ecomarket_servicio_pagos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ecomarket_servicio_pagos.entity.Pedido;

public interface PedidoRepository extends MongoRepository<Pedido, String> {
}
