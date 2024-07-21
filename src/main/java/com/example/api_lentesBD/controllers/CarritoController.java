package com.example.api_lentesBD.controllers;

import com.example.api_lentesBD.models.CarritoModels;
import com.example.api_lentesBD.services.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    // Crear un nuevo carrito
    @PostMapping
    public ResponseEntity<CarritoModels> saveCarrito(@RequestBody CarritoModels carrito) throws ExecutionException, InterruptedException {
        CarritoModels savedCarrito = carritoService.saveCarrito(carrito).get();
        return ResponseEntity.ok(savedCarrito);
    }

    // Obtener todos los carritos
    @GetMapping
    public ResponseEntity<List<CarritoModels>> getAllCarritos() throws ExecutionException, InterruptedException {
        List<CarritoModels> carritos = carritoService.getAllCarritos().get();
        return ResponseEntity.ok(carritos);
    }

    // Obtener un carrito por ID
    @GetMapping("/{id}")
    public ResponseEntity<CarritoModels> getCarritoById(@PathVariable String id) throws ExecutionException, InterruptedException {
        CarritoModels carrito = carritoService.getCarritoById(id).get();
        if (carrito == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrito);
    }

    // Actualizar un carrito por ID
    @PutMapping("/{id}")
    public ResponseEntity<CarritoModels> updateCarrito(@PathVariable String id, @RequestBody CarritoModels carrito) throws ExecutionException, InterruptedException {
        carrito.setId_carrito(id);
        carritoService.updateCarrito(id, carrito).get();
        return ResponseEntity.ok(carrito);
    }

    // Eliminar un carrito por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrito(@PathVariable String id) throws ExecutionException, InterruptedException {
        carritoService.deleteCarrito(id).get();
        return ResponseEntity.noContent().build();
    }


}

