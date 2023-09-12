package com.mongodb.teste.demo.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mongodb.teste.demo.model.Product;
import com.mongodb.teste.demo.model.Supplier;

public interface ProductService {
    
    String save(Product product);

    List<Product> listAll();

    List<Product> listByPrice(Double minPrice, Double maxPrice);

    Page<Product> search(String name, Integer quantity, Double price, Pageable pageable);

    String delete(String id);

    String deleteSupplier(String id, String zip_code);

    Product findByid(String id);

    String attProduct(String id, Product novo);

    String addSupplier(String id, Supplier supplier);

    String attSupplier(String id, Supplier supplier);

}
