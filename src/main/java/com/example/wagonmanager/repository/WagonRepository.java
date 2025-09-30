package com.example.wagonmanager.repository;

import com.example.wagonmanager.model.Wagon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WagonRepository extends JpaRepository<Wagon, Long> {
    Optional<Wagon> findByUuid(String uuid);
}
