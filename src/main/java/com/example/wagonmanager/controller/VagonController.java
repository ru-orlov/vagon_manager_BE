package com.example.wagonmanager.controller;

import com.example.wagonmanager.model.Wagon;
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
    public List<Wagon> getAllVagons() {
        return vagonService.getAllVagons();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Wagon> getVagonByUuid(@PathVariable String uuid) {
        return vagonService.getVagonByUuid(uuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Wagon addVagon(@RequestBody Wagon wagon) {
        return vagonService.saveVagon(wagon);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wagon> updateVagon(@PathVariable Long id, @RequestBody Wagon wagon) {
        if (!vagonService.getVagonByUuid(wagon.getUuid()).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        wagon.setId(id);
        return ResponseEntity.ok(vagonService.saveVagon(wagon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVagon(@PathVariable Long id) {
        vagonService.deleteVagon(id);
        return ResponseEntity.noContent().build();
    }
}
