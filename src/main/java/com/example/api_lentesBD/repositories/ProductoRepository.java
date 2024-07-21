package com.example.api_lentesBD.repositories;

import com.example.api_lentesBD.models.ProductoModels;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends MongoRepository<ProductoModels, String> {
    ProductoModels findByCodUnico(String codUnico);
}