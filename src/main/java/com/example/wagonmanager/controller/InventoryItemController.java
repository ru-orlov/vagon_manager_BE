package com.example.wagonmanager.controller;

import com.example.wagonmanager.dto.WagonInventoryResponse;
import com.example.wagonmanager.model.InventoryGroup;
import com.example.wagonmanager.model.InventoryItem;
import com.example.wagonmanager.model.Wagon;
import com.example.wagonmanager.service.InventoryGroupService;
import com.example.wagonmanager.service.InventoryItemService;
import com.example.wagonmanager.service.WagonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/items")
public class InventoryItemController {

    private final InventoryItemService itemService;
    private final WagonService wagonService;
    private final InventoryGroupService groupService;

    @Autowired
    public InventoryItemController(InventoryItemService itemService, WagonService wagonService, InventoryGroupService groupService) {
        this.itemService = itemService;
        this.wagonService = wagonService;
        this.groupService = groupService;
    }

    @GetMapping
    public List<InventoryItem> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<InventoryItem> getItemByUuid(@PathVariable String uuid) {
        return itemService.getItemByUuid(UUID.fromString(uuid))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/wagon/{uuid}/inventory")
    public ResponseEntity<WagonInventoryResponse> getWagonInventory(@PathVariable String uuid) {
        Optional<Wagon> optionalWagon = wagonService.getWagonByUuid(UUID.fromString(uuid));
        if (optionalWagon.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Wagon wagon = optionalWagon.get();
        List<InventoryGroup> groups = groupService.getGroupsByWagonUuid(uuid);
        List<WagonInventoryResponse.GroupWithItems> groupsWithItems = groups.stream().map(g -> {
            List<InventoryItem> items = itemService.getItemsByGroupUuid(String.valueOf(g.getUuid()));
            return new WagonInventoryResponse.GroupWithItems(g, items);
        }).collect(Collectors.toList());

        WagonInventoryResponse resp = new WagonInventoryResponse(wagon, groupsWithItems);
        return ResponseEntity.ok(resp);
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
