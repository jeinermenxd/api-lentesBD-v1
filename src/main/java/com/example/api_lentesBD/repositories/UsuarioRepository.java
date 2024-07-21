package com.example.api_lentesBD.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.api_lentesBD.models.UsuarioModels;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioModels, String> {

}
