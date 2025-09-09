package com.example.wagonmanager.repository;

import com.example.wagonmanager.model.Vagon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VagonRepository extends JpaRepository<Vagon, Long> {
    Optional<Vagon> findByUuid(String uuid);
}
