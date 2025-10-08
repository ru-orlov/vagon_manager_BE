package com.example.wagonmanager.service;

import com.example.wagonmanager.model.InventoryItem;
import com.example.wagonmanager.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryItemService {

    @Autowired
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
        return itemRepository.findAllByGroupUuid(groupUuid);
    }

    public InventoryItem saveItem(InventoryItem item) {
        return itemRepository.save(item);
    }

    @Transactional
    public int batchSaveOrUpdate(List<InventoryItem> inventoryItems) {
        int affected = 0;
        for (InventoryItem inventoryItem : inventoryItems) {
            affected += itemRepository.upsertInventoryItem(
                    inventoryItem.getUuid(),
                    inventoryItem.getGroupUuid(),
                    inventoryItem.getWagonUuid(),
                    inventoryItem.getName(),
                    inventoryItem.getDescription(),
                    inventoryItem.getQuantity(),
                    inventoryItem.getPhotos(),
                    inventoryItem.getUpdatedAt(),
                    inventoryItem.getCreatedAt(),
                    inventoryItem.getSyncStatus()
            );
        }
        return affected;
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
