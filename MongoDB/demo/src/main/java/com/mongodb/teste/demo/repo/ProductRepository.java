package com.mongodb.teste.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.teste.demo.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    
}
