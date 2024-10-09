package com.example.ecomarket_servicio_pagos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ecomarket_servicio_pagos.entity.Transaccion;

public interface TransaccionRepository extends MongoRepository<Transaccion, String> {

}
