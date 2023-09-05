package com.mongodb.teste.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    
    @Id
    private String productId;

    private String name;

    private String obs;

    private Integer quantity;

    private Double price;

    private List<Supplier> suppliers;

}
