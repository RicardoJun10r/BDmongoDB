package com.mongodb.teste.demo.service;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.teste.demo.model.Photo;
import com.mongodb.teste.demo.repo.PhotoRepository;
import com.mongodb.teste.demo.service.interfaces.PhotoService;

@Service
public class PhotoServiceImple implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public String addPhoto(String fileName, MultipartFile image) throws IOException {
        
        Photo photo = new Photo();
        photo.setTitle(fileName);
        photo.setPicture(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        return this.photoRepository.save(photo).getId();

    }

    @Override
    public Photo getPhoto(String id) {

        return this.photoRepository.findById(id).get();

    }
    
}
