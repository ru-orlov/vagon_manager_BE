package com.example.wagonmanager.repository;

import com.example.wagonmanager.model.Wagon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface WagonRepository extends JpaRepository<Wagon, Long> {
    Optional<Wagon> findByUuid(String uuid);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO wagon (uuid, number, type, username, created_at, updated_at) " +
            "VALUES (:uuid, :number, :type, :username, :createdAt, :updatedAt) " +
            "ON CONFLICT (uuid) DO UPDATE SET " +
            "number = EXCLUDED.number, " +
            "type = EXCLUDED.type, " +
            "username = EXCLUDED.username, " +
            "created_at = EXCLUDED.created_at, " +
            "updated_at = EXCLUDED.updated_at ",
            nativeQuery = true)
    int upsertWagon(
            @Param("uuid") String uuid,
            @Param("number") String number,
            @Param("type") String type,
            @Param("username") String username,
            @Param("createdAt") java.util.Date createdAt,
            @Param("updatedAt") java.util.Date updatedAt
    );
}
