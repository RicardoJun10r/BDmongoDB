package com.mongodb.teste.demo.model;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
 
    private String name;

    private String address;

    @Indexed(unique = true)
    private String ZIP_CODE;

    private String city;

    private LocalDate deliverDate;

}
