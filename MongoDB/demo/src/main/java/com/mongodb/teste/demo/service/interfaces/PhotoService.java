package com.mongodb.teste.demo.service.interfaces;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.mongodb.teste.demo.model.Photo;

public interface PhotoService {
    
    String addPhoto(String fileName, MultipartFile image) throws IOException;

    Photo getPhoto(String id);

}
