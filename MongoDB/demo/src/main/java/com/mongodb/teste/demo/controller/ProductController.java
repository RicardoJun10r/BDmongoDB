package com.mongodb.teste.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.teste.demo.model.Product;
import com.mongodb.teste.demo.service.interfaces.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @PostMapping
    public String save(@RequestBody Product product){
        
        return this.productService.save(product);

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id){

        return this.productService.delete(id);

    }

    @GetMapping
    public List<Product> findAll(){

        return this.productService.listAll();
    
    }

    @GetMapping("/search")
    public Page<Product> searchProduct(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) Integer quantity,
        @RequestParam(required = false) Double price,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "5") Integer size
    ){
        Pageable pageable = PageRequest.of(page, size);

        return this.productService.search(name, quantity, price, pageable);
    
    }

}
