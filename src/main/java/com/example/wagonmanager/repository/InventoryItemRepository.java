package com.example.wagonmanager.repository;

import com.example.wagonmanager.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    Optional<InventoryItem> findByUuid(UUID uuid);
    List<InventoryItem> findAllByGroupUuid(UUID groupUuid);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO inventory_item (uuid, group_uuid, wagon_uuid, name, description, quantity, photos, username, created_at, updated_at, sync_status) " +
            "VALUES (:uuid, :groupUuid, :wagonUuid, :name, :description, :quantity, :photos, :userName, :createdAt, :updatedAt, :syncStatus) " +
            "ON CONFLICT (uuid) DO UPDATE SET " +
            "group_uuid = EXCLUDED.group_uuid, " +
            "wagon_uuid = EXCLUDED.wagon_uuid, " +
            "name = EXCLUDED.name, " +
            "description = EXCLUDED.description, " +
            "quantity = EXCLUDED.quantity, " +
            "photos = EXCLUDED.photos, " +
            "username = EXCLUDED.username, " +
            "created_at = EXCLUDED.created_at, " +
            "updated_at = EXCLUDED.updated_at, " +
            "sync_status = EXCLUDED.sync_status " +
            "WHERE (EXCLUDED.name IS DISTINCT FROM inventory_item.name) " +
            "OR (EXCLUDED.description IS DISTINCT FROM inventory_item.description) " +
            "OR (EXCLUDED.quantity IS DISTINCT FROM inventory_item.quantity) " +
            "OR (EXCLUDED.photos IS DISTINCT FROM inventory_item.photos) " +
            "OR (EXCLUDED.username IS DISTINCT FROM inventory_item.username) " +
            "OR (EXCLUDED.created_at IS DISTINCT FROM inventory_item.created_at) " +
            "OR (EXCLUDED.updated_at IS DISTINCT FROM inventory_item.updated_at) " +
            "OR (EXCLUDED.sync_status IS DISTINCT FROM inventory_item.sync_status)",
            nativeQuery = true)


    int upsertInventoryItem(
            @Param("uuid") UUID uuid,
            @Param("groupUuid") UUID groupUuid,
            @Param("wagonUuid") UUID wagonUuid,
            @Param("name") String name,
            @Param("description") String description,
            @Param("quantity") int quantity,
            @Param("photos") String photos,
            @Param("userName") String userName,
            @Param("createdAt") java.util.Date createdAt,
            @Param("updatedAt") java.util.Date updatedAt,
            @Param("syncStatus") String syncStatus
    );
}