package com.example.api_lentesBD.services;

import com.example.api_lentesBD.models.UsuarioModels;
import com.example.api_lentesBD.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Guardar un nuevo usuario
    public UsuarioModels saveUsuario(UsuarioModels usuario) {
        // Asegurarse de que el ID sea nulo para que MongoDB genere uno nuevo automáticamente
        usuario.setId(null);
        return usuarioRepository.save(usuario);
    }

    // Obtener todos los usuarios
    public List<UsuarioModels> getUsuarios() {
        return usuarioRepository.findAll();
    }

    // Obtener un usuario por ID
    public UsuarioModels getUsuarioById(String id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Actualizar un usuario existente
    public UsuarioModels updateUsuario(String id, UsuarioModels usuario) {
        if (!usuarioRepository.existsById(id)) {
            return null;
        }
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    // Eliminar un usuario por ID
    public boolean deleteUsuario(String id) {
        if (!usuarioRepository.existsById(id)) {
            return false;
        }
        usuarioRepository.deleteById(id);
        return true;
    }
}
