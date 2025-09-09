package com.example.wagonmanager.controller;

import com.example.wagonmanager.model.Photo;
import com.example.wagonmanager.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/photos")
public class PhotoController {

    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/item/{itemUuid}")
    public List<Photo> getPhotosByItemUuid(@PathVariable String itemUuid) {
        return photoService.getPhotosByItemUuid(itemUuid);
    }

    @PostMapping
    public Photo addPhoto(@RequestBody Photo photo) {
        return photoService.savePhoto(photo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long id) {
        photoService.deletePhoto(id);
        return ResponseEntity.noContent().build();
    }
}
