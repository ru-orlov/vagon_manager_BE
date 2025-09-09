package com.example.wagonmanager.service;

import com.example.wagonmanager.model.Photo;
import com.example.wagonmanager.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public List<Photo> getPhotosByItemUuid(String itemUuid) {
        return photoRepository.findAllByItem_Uuid(itemUuid);
    }

    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }
}
