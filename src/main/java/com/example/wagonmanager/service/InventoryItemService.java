package com.example.wagonmanager.service;

import com.example.wagonmanager.controller.SyncController;
import com.example.wagonmanager.model.InventoryItem;
import com.example.wagonmanager.repository.InventoryItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryItemService {
    private static final Logger logger = LoggerFactory.getLogger(SyncController.class);
    @Autowired
    private final InventoryItemRepository itemRepository;

    @Autowired
    public InventoryItemService(InventoryItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<InventoryItem> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<InventoryItem> getItemByUuid(UUID uuid) {
        return itemRepository.findByUuid(uuid);
    }

    public List<InventoryItem> getItemsByGroupUuid(String groupUuid) {
        return itemRepository.findAllByGroupUuid(UUID.fromString(groupUuid));
    }

    public InventoryItem saveItem(InventoryItem item) {
        return itemRepository.save(item);
    }

    @Transactional
    public int batchSaveOrUpdate(List<InventoryItem> inventoryItems, String userName) {
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
                    userName,
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

    public String saveInventoryPhoto(MultipartFile file, String wagonUuid, String uuid) throws IOException {
        String baseDir = "D:\\Inventory\\Photos\\" + wagonUuid + "\\" + uuid;
        java.nio.file.Path dir = java.nio.file.Paths.get(baseDir);
        if (!java.nio.file.Files.exists(dir)) {
            java.nio.file.Files.createDirectories(dir);
        }

        String original = java.nio.file.Paths.get(file.getOriginalFilename()).getFileName().toString();
        java.nio.file.Path target = dir.resolve(original);

        try (java.io.InputStream in = file.getInputStream()) {
            java.nio.file.Files.copy(in, target, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }

        logger.info("Saved photo to {}", target);
        return target.toString();
    }
}
