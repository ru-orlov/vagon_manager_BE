package com.example.wagonmanager.controller;

import com.example.wagonmanager.dto.SyncPayload;
import com.example.wagonmanager.model.Wagon;
import com.example.wagonmanager.service.InventoryGroupService;
import com.example.wagonmanager.service.InventoryItemService;
import com.example.wagonmanager.service.VagonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/sync")
public class SyncController {

    @Autowired
    private VagonService vagonService;
    @Autowired
    private InventoryGroupService groupService;
    @Autowired
    private InventoryItemService itemService;

    // Загрузка изменений от клиента
    @PostMapping("/upload")
    public Map<String, Object> uploadSyncData(@RequestBody SyncPayload payload) {
        List<Wagon> savedWagons = new ArrayList<>();
        System.out.printf(">>> uploadSyncData");
//        for (Wagon v : payload.getWagons()) {
//            // реализуй сравнение updated_at
//            Optional<Wagon> existing = vagonService.getVagonByUuid(v.getUuid());
//            if (existing.isEmpty() || v.getUpdatedAt().after(existing.get().getUpdatedAt())) {
//                savedWagons.add(vagonService.saveVagon(v));
//            }
//        }
        // То же для групп, items, photos
        // ...
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
//        result.put("savedVagons", savedVagons.size());
        // ...
        return result;
    }

    // Выгрузка изменений для клиента
    @GetMapping("/download")
    public SyncPayload downloadSyncData(@RequestParam(name = "since") Date since) {
        List<Wagon> wagons = vagonService.getAllVagons().stream().filter(
                v -> v.getUpdatedAt() != null && v.getUpdatedAt().after(since)
        ).toList();
        // То же для групп, items, photos
        SyncPayload payload = new SyncPayload();
        payload.setWagons(wagons);
        // ...
        return payload;
    }
}
