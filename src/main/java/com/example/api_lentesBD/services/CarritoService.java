package com.example.api_lentesBD.services;

import com.example.api_lentesBD.models.CarritoModels;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.ApiFutureCallback;
import com.google.firebase.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CarritoService {

    private final DatabaseReference databaseReference;

    @Autowired
    public CarritoService(FirebaseDatabase firebaseDatabase) {
        this.databaseReference = firebaseDatabase.getReference("carritos");
    }

    public CompletableFuture<CarritoModels> saveCarrito(CarritoModels carrito) {
        CompletableFuture<CarritoModels> future = new CompletableFuture<>();
        String id = databaseReference.push().getKey();
        carrito.setId_carrito(id);
        ApiFuture<Void> apiFuture = databaseReference.child(id).setValueAsync(carrito);
        ApiFutures.addCallback(apiFuture, new ApiFutureCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                future.complete(carrito);
            }

            @Override
            public void onFailure(Throwable t) {
                future.completeExceptionally(t);
            }
        }, Runnable::run);
        return future;
    }

    public CompletableFuture<CarritoModels> getCarritoById(String id) {
        CompletableFuture<CarritoModels> future = new CompletableFuture<>();
        databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    CarritoModels carrito = snapshot.getValue(CarritoModels.class);
                    future.complete(carrito);
                } else {
                    future.completeExceptionally(new Exception("Carrito no encontrado"));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                future.completeExceptionally(error.toException());
            }
        });
        return future;
    }

    public CompletableFuture<Void> updateCarrito(String id, CarritoModels carrito) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        ApiFuture<Void> apiFuture = databaseReference.child(id).setValueAsync(carrito);
        ApiFutures.addCallback(apiFuture, new ApiFutureCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                future.complete(null);
            }

            @Override
            public void onFailure(Throwable t) {
                future.completeExceptionally(t);
            }
        }, Runnable::run);
        return future;
    }

    public CompletableFuture<Void> deleteCarrito(String id) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        ApiFuture<Void> apiFuture = databaseReference.child(id).removeValueAsync();
        ApiFutures.addCallback(apiFuture, new ApiFutureCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                future.complete(null);
            }

            @Override
            public void onFailure(Throwable t) {
                future.completeExceptionally(t);
            }
        }, Runnable::run);
        return future;
    }

    public CompletableFuture<List<CarritoModels>> getAllCarritos() {
        CompletableFuture<List<CarritoModels>> future = new CompletableFuture<>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<CarritoModels> carritos = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CarritoModels carrito = dataSnapshot.getValue(CarritoModels.class);
                    carritos.add(carrito);
                }
                future.complete(carritos);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                future.completeExceptionally(error.toException());
            }
        });
        return future;
    }
}