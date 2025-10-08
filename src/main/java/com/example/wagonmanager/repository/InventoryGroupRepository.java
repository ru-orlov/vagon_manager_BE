package com.example.wagonmanager.repository;

import com.example.wagonmanager.model.InventoryGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface InventoryGroupRepository extends JpaRepository<InventoryGroup, Long> {
    Optional<InventoryGroup> findByUuid(String uuid);
    List<InventoryGroup> findAllByWagonUuid(String wagonUuid);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO inventory_group (uuid, name, description, wagon_uuid, created_at, updated_at, sync_status) " +
            "VALUES (:uuid, :name, :description, :wagonUuid, :createdAt, :updatedAt, :syncStatus) " +
            "ON CONFLICT (uuid) DO UPDATE SET " +
            "name = EXCLUDED.name, " +
            "description = EXCLUDED.description, " +
            "wagon_uuid = EXCLUDED.wagon_uuid, " +
            "created_at = EXCLUDED.created_at, " +
            "updated_at = EXCLUDED.updated_at, " +
            "sync_status = EXCLUDED.sync_status ",
            nativeQuery = true)
    int upsertInventoryGroup(
            @Param("uuid") String uuid,
            @Param("name") String name,
            @Param("description") String description,
            @Param("wagonUuid") String wagonUuid,
            @Param("createdAt") java.util.Date createdAt,
            @Param("updatedAt") java.util.Date updatedAt,
            @Param("syncStatus") String syncStatus
    );
}
