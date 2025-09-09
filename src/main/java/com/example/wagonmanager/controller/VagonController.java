package com.example.wagonmanager.controller;

import com.example.wagonmanager.model.Vagon;
import com.example.wagonmanager.service.VagonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vagons")
public class VagonController {

    private final VagonService vagonService;

    @Autowired
    public VagonController(VagonService vagonService) {
        this.vagonService = vagonService;
    }

    @GetMapping
    public List<Vagon> getAllVagons() {
        return vagonService.getAllVagons();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Vagon> getVagonByUuid(@PathVariable String uuid) {
        return vagonService.getVagonByUuid(uuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Vagon addVagon(@RequestBody Vagon vagon) {
        return vagonService.saveVagon(vagon);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vagon> updateVagon(@PathVariable Long id, @RequestBody Vagon vagon) {
        if (!vagonService.getVagonByUuid(vagon.getUuid()).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        vagon.setId(id);
        return ResponseEntity.ok(vagonService.saveVagon(vagon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVagon(@PathVariable Long id) {
        vagonService.deleteVagon(id);
        return ResponseEntity.noContent().build();
    }
}
