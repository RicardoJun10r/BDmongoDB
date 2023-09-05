package com.mongodb.teste.demo.model;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Supplier {
 
    private String name;

    private String address;

    private String ZIP_CODE;

    private String city;

    private LocalDate deliverDate;

}
