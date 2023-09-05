package com.mongodb.teste.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.teste.demo.model.Photo;
import com.mongodb.teste.demo.service.interfaces.PhotoService;

@RestController
@RequestMapping("/api/photo")
public class PhotoController {
    
    @Autowired
    private PhotoService photoService;

    @PostMapping
    public String addPhoto(@RequestParam("image") MultipartFile image) throws IOException {

        return this.photoService.addPhoto(image.getOriginalFilename(), image);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> download(@PathVariable String id) {
        
        Photo photo = this.photoService.getPhoto(id);
        Resource resource = new ByteArrayResource(photo.getPicture().getData());

        return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photo.getTitle() + "\"")
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(resource);

    }
}
