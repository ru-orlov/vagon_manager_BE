package com.example.wagonmanager.controller;

import com.example.wagonmanager.model.InventoryItem;
import com.example.wagonmanager.service.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class InventoryItemController {

    private final InventoryItemService itemService;

    @Autowired
    public InventoryItemController(InventoryItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<InventoryItem> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<InventoryItem> getItemByUuid(@PathVariable String uuid) {
        return itemService.getItemByUuid(uuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/group/{groupUuid}")
    public List<InventoryItem> getItemsByGroupUuid(@PathVariable String groupUuid) {
        return itemService.getItemsByGroupUuid(groupUuid);
    }

    @PostMapping
    public InventoryItem addItem(@RequestBody InventoryItem item) {
        return itemService.saveItem(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryItem> updateItem(@PathVariable Long id, @RequestBody InventoryItem item) {
        if (!itemService.getItemByUuid(item.getUuid()).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        item.setId(id);
        return ResponseEntity.ok(itemService.saveItem(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
