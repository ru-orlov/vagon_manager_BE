package com.example.wagonmanager.repository;

import com.example.wagonmanager.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findAllByItem_Uuid(String itemUuid);
}
