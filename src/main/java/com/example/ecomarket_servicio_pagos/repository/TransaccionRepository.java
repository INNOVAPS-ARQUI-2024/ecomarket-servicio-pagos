package com.example.ecomarket_servicio_pagos.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ecomarket_servicio_pagos.entity.Transaccion;

public interface TransaccionRepository extends MongoRepository<Transaccion, String> {
    List<Transaccion> findByStatus(String status);

}
