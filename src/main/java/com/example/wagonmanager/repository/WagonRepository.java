package com.example.wagonmanager.repository;

import com.example.wagonmanager.dto.WagonDto;
import com.example.wagonmanager.model.Wagon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface WagonRepository extends JpaRepository<Wagon, Long> {
    Optional<Wagon> findByUuid(UUID uuid);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO wagon (uuid, number, type, username, created_at, updated_at) " +
            "VALUES (:uuid, :number, :type, :username, :createdAt, :updatedAt) " +
            "ON CONFLICT (uuid) DO UPDATE SET " +
            "number = EXCLUDED.number, " +
            "type = EXCLUDED.type, " +
            "username = EXCLUDED.username, " +
            "created_at = EXCLUDED.created_at, " +
            "updated_at = EXCLUDED.updated_at " +
            "WHERE (wagon.username IS DISTINCT FROM EXCLUDED.username) " +
            "OR (wagon.created_at IS DISTINCT FROM EXCLUDED.created_at) " +
            "OR (wagon.updated_at IS DISTINCT FROM EXCLUDED.updated_at)",
            nativeQuery = true)
    int upsertWagon(
            @Param("uuid") UUID uuid,
            @Param("number") String number,
            @Param("type") String type,
            @Param("username") String username,
            @Param("createdAt") java.util.Date createdAt,
            @Param("updatedAt") java.util.Date updatedAt
    );

    Page<Wagon> findByNumberContainingIgnoreCaseOrTypeContainingIgnoreCase(String number, String type, Pageable pageable);
}
