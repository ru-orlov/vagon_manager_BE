package com.example.wagonmanager.service;

import com.example.wagonmanager.model.InventoryGroup;
import com.example.wagonmanager.repository.InventoryGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<InventoryGroup> getGroupByUuid(String uuid) {
        return groupRepository.findByUuid(uuid);
    }

    public List<InventoryGroup> getGroupsByVagonUuid(String vagonUuid) {
        return groupRepository.findAllByVagon_Uuid(vagonUuid);
    }

    public InventoryGroup saveGroup(InventoryGroup group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}
