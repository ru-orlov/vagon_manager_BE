package com.example.wagonmanager.controller;

import com.example.wagonmanager.model.InventoryGroup;
import com.example.wagonmanager.service.InventoryGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
public class InventoryGroupController {

    private final InventoryGroupService groupService;

    @Autowired
    public InventoryGroupController(InventoryGroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<InventoryGroup> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<InventoryGroup> getGroupByUuid(@PathVariable String uuid) {
        return groupService.getGroupByUuid(uuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/vagon/{vagonUuid}")
    public List<InventoryGroup> getGroupsByVagonUuid(@PathVariable String vagonUuid) {
        return groupService.getGroupsByVagonUuid(vagonUuid);
    }

    @PostMapping
    public InventoryGroup addGroup(@RequestBody InventoryGroup group) {
        return groupService.saveGroup(group);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryGroup> updateGroup(@PathVariable Long id, @RequestBody InventoryGroup group) {
        if (!groupService.getGroupByUuid(group.getUuid()).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        group.setId(id);
        return ResponseEntity.ok(groupService.saveGroup(group));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }
}
