package com.example.api_lentesBD.services;

import com.example.api_lentesBD.models.ProductoModels;
import com.example.api_lentesBD.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Guardar un nuevo producto
    public ProductoModels saveProducto(ProductoModels producto) {
        // Asegurarse de que el ID sea nulo para que MongoDB genere uno nuevo automáticamente
        producto.setId(null);
        return productoRepository.save(producto);
    }

    // Obtener todos los productos
    public List<ProductoModels> getProductos() {
        return productoRepository.findAll();
    }

    // Obtener un producto por ID
    public ProductoModels getProductosById(String id) {
        return productoRepository.findById(id).orElse(null);
    }

    // Obtener un producto por código único
    public ProductoModels getProductoByCodUnico(String codUnico) {
        return productoRepository.findByCodUnico(codUnico);
    }

    // Actualizar un producto existente
    public ProductoModels updateProducto(String id, ProductoModels producto) {
        if (!productoRepository.existsById(id)) {
            return null;
        }
        producto.setId(id);
        return productoRepository.save(producto);
    }

    // Eliminar un producto por código único
    public boolean deleteProducto(String id) {
        if (!productoRepository.existsById(id)) {
            return false;
        }
        productoRepository.deleteById(id);
        return true;
    }
}
