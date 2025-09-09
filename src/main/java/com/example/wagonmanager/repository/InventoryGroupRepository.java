package com.example.wagonmanager.repository;

import com.example.wagonmanager.model.InventoryGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryGroupRepository extends JpaRepository<InventoryGroup, Long> {
    Optional<InventoryGroup> findByUuid(String uuid);
    List<InventoryGroup> findAllByVagon_Uuid(String vagonUuid);
}
