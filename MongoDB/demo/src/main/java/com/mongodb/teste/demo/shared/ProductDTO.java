package com.mongodb.teste.demo.shared;

import java.util.List;

import com.mongodb.teste.demo.model.Supplier;

public record ProductDTO(String nome, String obs, Integer quantity, Double price, List<Supplier> suppliers) {
    
}
