package com.example.api_lentesBD.repositories;

import com.example.api_lentesBD.models.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
    Cliente findByDni(String dni);
}
