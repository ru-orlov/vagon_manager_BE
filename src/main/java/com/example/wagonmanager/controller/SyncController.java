package com.example.wagonmanager.controller;

import com.example.wagonmanager.dto.PhotoUploadResponse;
import com.example.wagonmanager.dto.SyncPayload;
import com.example.wagonmanager.model.InventoryItem;
import com.example.wagonmanager.model.Wagon;
import com.example.wagonmanager.service.InventoryGroupService;
import com.example.wagonmanager.service.InventoryItemService;
import com.example.wagonmanager.service.WagonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        System.out.println(">>> uploadSyncData");
        System.out.println(">>> payload.getWagons().size(): " + payload.getWagons().size());
        Map<String, Object> result = new HashMap<>();
        if (!payload.getWagons().isEmpty()) {
            int wagonsUpdated = wagonService.batchSaveOrUpdate(payload.getWagons(), payload.userName);
            result.put("wagonsUpdated", wagonsUpdated);
        }
        if (!payload.getInventoryGroups().isEmpty()) {
            int groupsUpdated = groupService.batchSaveOrUpdate(payload.getInventoryGroups(), payload.userName);
            result.put("groupsUpdated", groupsUpdated);
        }
        if (!payload.getInventoryItems().isEmpty()) {
            int itemsUpdated = itemService.batchSaveOrUpdate(payload.getInventoryItems(), payload.userName);
            result.put("itemsUpdated", itemsUpdated);
        }
        result.put("success", true);
        return result;
    }

    @PostMapping("/photosupload")
    public PhotoUploadResponse uploadPhoto(@RequestParam("file") MultipartFile file,
                                           @RequestParam("wagonUuid") String wagonUuid,
                                           @RequestParam("inventoryItemUuid") String uuid) throws IOException {

        System.out.printf(">>> uploadPhoto for inventoryItemUuid: %s\n", uuid);
        System.out.printf(">>> file original filename: %s, size: %d\n", file.getOriginalFilename(), file.getSize());
        // сохранить файл (disk, S3 и т.д.)
        String storedFilename = itemService.saveInventoryPhoto(file, wagonUuid, uuid);



        // сохранить запись в БД (photo id, url, связь с inventoryItem)
        return new PhotoUploadResponse(true, uuid);
    }

    @GetMapping("/downloadwagon")
    public SyncPayload wagonWithItems(@RequestParam(name = "uuid") String wagonuuid) {
        System.out.println("wagonuuid " + wagonuuid);
        Optional<Wagon> wagons = wagonService.getWagonByUuid(wagonuuid);
        System.out.println("wagons " + wagons.isEmpty());

        SyncPayload payload = new SyncPayload();
        if (wagons.isPresent()) {
            Wagon dto = new Wagon();
            dto.setUuid(wagonuuid);
            dto.setNumber(wagons.get().getNumber());
            dto.setType(wagons.get().getType());
            dto.setCreatedAt(wagons.get().getCreatedAt());
            dto.setUpdatedAt(wagons.get().getUpdatedAt());
            payload.setWagons(List.of(dto));
            payload.setInventoryGroups(wagons.get().getInventoryGroups());
            List<InventoryItem> inventoryItems = new ArrayList<>();
            wagons.get().getInventoryGroups().forEach(group -> inventoryItems.addAll(group.getInventoryItems()));
            payload.setInventoryItems(inventoryItems);
        }
        return payload;
    }

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
