package com.mongodb.teste.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.teste.demo.model.Photo;

public interface PhotoRepository extends MongoRepository<Photo, String> {
    
}
