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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.teste.demo.model.Product;
import com.mongodb.teste.demo.model.Supplier;
import com.mongodb.teste.demo.service.interfaces.ProductService;
import com.mongodb.teste.demo.shared.ProductDTO;
import com.mongodb.teste.demo.shared.ProductDTOATT;
import com.mongodb.teste.demo.shared.SupplierDTO;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @PostMapping
    public String save(@RequestBody ProductDTO product){

        Product p = new Product(null, product.nome(), product.obs(), product.quantity(), product.price(), product.suppliers());
        return this.productService.save(p);

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id){

        return this.productService.delete(id);

    }

    @DeleteMapping("/{id}/{zip}")
    public String deleteSupplier(@PathVariable String id, @PathVariable String zip){

        return this.productService.deleteSupplier(id, zip);

    }

    @GetMapping
    public List<Product> findAll(){

        return this.productService.listAll();
    
    }

    @GetMapping("/{minPrice}/{maxPrice}")
    public List<Product> findByPriceBetween(@PathVariable Double minPrice, @PathVariable Double maxPrice){

        return this.productService.listByPrice(minPrice, maxPrice);
    
    }

    @GetMapping("/buscar")
    public Product findById(@RequestParam String id){

        return this.productService.findByid(id);
    
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

    @PutMapping("/att")
    public String attProduct(@RequestParam String id, @RequestBody ProductDTOATT novo){
        Product p = new Product(null, novo.nome(), novo.obs(), novo.quantity(), novo.price(), null);
        return this.productService.attProduct(id, p);
    }

    @PostMapping("/supplier")
    public String addSupplier(@RequestParam String id, @RequestBody SupplierDTO novo){
        Supplier supplier = new Supplier(novo.name(), novo.address(), novo.ZIP_CODE(), novo.city(), novo.deliverDate());
        return this.productService.addSupplier(id, supplier);
    }

    @PutMapping("/att/supplier")
    public String attSupplier(@RequestParam String id, @RequestBody SupplierDTO novo){
        Supplier supplier = new Supplier(novo.name(), novo.address(), novo.ZIP_CODE(), novo.city(), novo.deliverDate());
        return this.productService.attSupplier(id, supplier);
    }

}
