package com.example.wagonmanager.service;

import com.example.wagonmanager.model.InventoryGroup;
import com.example.wagonmanager.model.Wagon;
import com.example.wagonmanager.repository.InventoryGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryGroupService {

    private final InventoryGroupRepository groupRepository;

    @Autowired
    public InventoryGroupService(InventoryGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<InventoryGroup> getAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<InventoryGroup> getGroupByUuid(UUID uuid) {
        return groupRepository.findByUuid(uuid);
    }

    public List<InventoryGroup> getGroupsByVagonUuid(String wagonUuid) {
        return groupRepository.findAllByWagonUuid(UUID.fromString(wagonUuid));
    }

    public InventoryGroup saveGroup(InventoryGroup group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    @Transactional
    public int batchSaveOrUpdate(List<InventoryGroup> inventoryGroups, String userName) {
        int affected = 0;
        for (InventoryGroup inventoryGroup : inventoryGroups) {
            affected += groupRepository.upsertInventoryGroup(
                    inventoryGroup.getUuid(),
                    inventoryGroup.getName(),
                    inventoryGroup.getDescription(),
                    inventoryGroup.getWagonUuid(),
                    userName,
                    inventoryGroup.getUpdatedAt(),
                    inventoryGroup.getCreatedAt(),
                    inventoryGroup.getSyncStatus()
            );
        }
        return affected;
    }
}
