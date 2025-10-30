package com.example.wagonmanager.repository;

import com.example.wagonmanager.model.InventoryGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventoryGroupRepository extends JpaRepository<InventoryGroup, Long> {
    Optional<InventoryGroup> findByUuid(UUID uuid);
    List<InventoryGroup> findAllByWagonUuid(UUID wagonUuid);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO inventory_group (uuid, name, description, wagon_uuid, userName, created_at, updated_at, sync_status) " +
            "VALUES (:uuid, :name, :description, :wagonUuid, :username, :createdAt, :updatedAt, :syncStatus) " +
            "ON CONFLICT (uuid) DO UPDATE SET " +
            "name = EXCLUDED.name, " +
            "description = EXCLUDED.description, " +
            "wagon_uuid = EXCLUDED.wagon_uuid, " +
            "userName = EXCLUDED.userName, " +
            "created_at = EXCLUDED.created_at, " +
            "updated_at = EXCLUDED.updated_at, " +
            "sync_status = EXCLUDED.sync_status " +
            "WHERE (inventory_group.name IS DISTINCT FROM EXCLUDED.name OR " +
            "inventory_group.description IS DISTINCT FROM EXCLUDED.description OR " +
            "inventory_group.wagon_uuid IS DISTINCT FROM EXCLUDED.wagon_uuid OR " +
            "inventory_group.userName IS DISTINCT FROM EXCLUDED.userName OR " +
            "inventory_group.created_at IS DISTINCT FROM EXCLUDED.created_at OR " +
            "inventory_group.updated_at IS DISTINCT FROM EXCLUDED.updated_at OR " +
            "inventory_group.sync_status IS DISTINCT FROM EXCLUDED.sync_status)",
            nativeQuery = true)

    int upsertInventoryGroup(
            @Param("uuid") UUID uuid,
            @Param("name") String name,
            @Param("description") String description,
            @Param("wagonUuid") UUID wagonUuid,
            @Param("username") String username,
            @Param("createdAt") java.util.Date createdAt,
            @Param("updatedAt") java.util.Date updatedAt,
            @Param("syncStatus") String syncStatus
    );
}
