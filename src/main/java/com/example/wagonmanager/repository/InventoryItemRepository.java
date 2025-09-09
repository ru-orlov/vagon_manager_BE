package com.example.wagonmanager.repository;

import com.example.wagonmanager.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    Optional<InventoryItem> findByUuid(String uuid);
    List<InventoryItem> findAllByGroup_Uuid(String groupUuid);
}