package com.example.ecomarket_servicio_pagos.repository;

import com.example.ecomarket_servicio_pagos.entity.Carrito;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends MongoRepository<Carrito, String> {
    Carrito findByUserId(String userId); // Opcional, para obtener el carrito del usuario.
}
