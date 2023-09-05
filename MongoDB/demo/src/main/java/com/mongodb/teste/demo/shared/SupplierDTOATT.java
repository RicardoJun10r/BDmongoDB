package com.mongodb.teste.demo.shared;

import java.time.LocalDate;

public record SupplierDTOATT(String name, String address, String SAME_ZIP_CODE, String city, LocalDate deliverDate) {
    
}
