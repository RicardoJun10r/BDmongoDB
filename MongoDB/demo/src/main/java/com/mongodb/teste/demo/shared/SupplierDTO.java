package com.mongodb.teste.demo.shared;

import java.time.LocalDate;

public record SupplierDTO(String name, String address, String ZIP_CODE, String city, LocalDate deliverDate) {
    
}
