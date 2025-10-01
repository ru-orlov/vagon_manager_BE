package com.example.wagonmanager.controller;

import com.example.wagonmanager.dto.SyncPayload;
import com.example.wagonmanager.model.Wagon;
import com.example.wagonmanager.service.InventoryGroupService;
import com.example.wagonmanager.service.InventoryItemService;
import com.example.wagonmanager.service.WagonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

@RestController
@RequestMapping("/api/v1/sync")
public class SyncController {

    @Autowired
    private WagonService wagonService;
    @Autowired
    private InventoryGroupService groupService;
    @Autowired
    private InventoryItemService itemService;
    private static final Logger logger = LoggerFactory.getLogger(SyncController.class);

    // Загрузка изменений от клиента
    @PostMapping("/upload")
    public Map<String, Object> uploadSyncData(@RequestBody SyncPayload payload) {
        System.out.printf(">>> uploadSyncData");
        System.out.printf(">>> payload.getWagons().size(): %d", payload.getWagons().size());
        Map<String, Object> result = new HashMap<>();
        if (!payload.getWagons().isEmpty()) {
            int wagonsUpdated = wagonService.batchSaveOrUpdate(payload.getWagons());
            result.put("wagonsUpdated", wagonsUpdated);
        }
        if (!payload.getInventoryGroups().isEmpty()) {
            int groupsUpdated = groupService.batchSaveOrUpdate(payload.getInventoryGroups());
            result.put("groupsUpdated", groupsUpdated);
        }
        if (!payload.getInventoryItems().isEmpty()) {
            int itemsUpdated = itemService.batchSaveOrUpdate(payload.getInventoryItems());
            result.put("itemsUpdated", itemsUpdated);
        }

        result.put("success", true);
        return result;
    }

    // Выгрузка изменений для клиента
    @GetMapping("/download")
    public SyncPayload downloadSyncData(@RequestParam(name = "since") Date since) {
        System.out.println("since " + since);
        List<Wagon> wagons = wagonService.getAllWagons().stream().filter(
                v -> v.getUpdatedAt() != null && v.getUpdatedAt().after(since)
        ).toList();
        System.out.println("wagons " + wagons.size());
        // То же для групп, items, photos
        SyncPayload payload = new SyncPayload();
        payload.setWagons(wagons);
        // ...
        return payload;
    }
}
