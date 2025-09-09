package com.example.wagonmanager.service;

import com.example.wagonmanager.model.InventoryItem;
import com.example.wagonmanager.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryItemService {

    private final InventoryItemRepository itemRepository;

    @Autowired
    public InventoryItemService(InventoryItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<InventoryItem> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<InventoryItem> getItemByUuid(String uuid) {
        return itemRepository.findByUuid(uuid);
    }

    public List<InventoryItem> getItemsByGroupUuid(String groupUuid) {
        return itemRepository.findAllByGroup_Uuid(groupUuid);
    }

    public InventoryItem saveItem(InventoryItem item) {
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
