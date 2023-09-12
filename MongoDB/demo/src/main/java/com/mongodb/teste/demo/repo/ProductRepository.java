package com.mongodb.teste.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mongodb.teste.demo.model.Product;
import java.util.List;


public interface ProductRepository extends MongoRepository<Product, String> {
    
    @Query(value = "{'price' : {$gt: ?0, $lt : ?1}}", fields = "{suppliers: 0}")
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
}
