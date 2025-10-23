package com.example.wagonmanager.repository;

import com.example.wagonmanager.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    Optional<InventoryItem> findByUuid(String uuid);
    List<InventoryItem> findAllByGroupUuid(String groupUuid);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO inventory_item (uuid, group_uuid, wagon_uuid, name, description, quantity, photos, created_at, updated_at, sync_status) " +
            "VALUES (:uuid, :groupUuid, :wagonUuid, :name, :description, :quantity, :photos, :createdAt, :updatedAt, :syncStatus) " +
            "ON CONFLICT (uuid) DO UPDATE SET " +
            "group_uuid = EXCLUDED.group_uuid, " +
            "wagon_uuid = EXCLUDED.wagon_uuid, " +
            "name = EXCLUDED.name, " +
            "description = EXCLUDED.description, " +
            "quantity = EXCLUDED.quantity, " +
            "photos = EXCLUDED.photos, " +
            "created_at = EXCLUDED.created_at, " +
            "updated_at = EXCLUDED.updated_at, " +
            "sync_status = EXCLUDED.sync_status ",
            nativeQuery = true)
    int upsertInventoryItem(
            @Param("uuid") String uuid,
            @Param("groupUuid") String groupUuid,
            @Param("wagonUuid") String wagonUuid,
            @Param("name") String name,
            @Param("description") String description,
            @Param("quantity") int quantity,
            @Param("photos") String photos,
            @Param("createdAt") java.util.Date createdAt,
            @Param("updatedAt") java.util.Date updatedAt,
            @Param("syncStatus") String syncStatus
    );
}