package com.mongodb.teste.demo.service.interfaces;

import java.util.List;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mongodb.teste.demo.model.Product;

public interface ProductService {
    
    String save(Product product);

    List<Product> listAll();

    Page<Product> search(String name, Integer quantity, Double price, Pageable pageable);

    // List<Document> getSuppliers();

    String delete(String id);

}
