package com.example.wagonmanager.controller;

import com.example.wagonmanager.model.Wagon;
import com.example.wagonmanager.service.WagonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vagons")
public class WagonController {

    private final WagonService wagonService;

    @Autowired
    public WagonController(WagonService wagonService) {
        this.wagonService = wagonService;
    }

    @GetMapping
    public List<Wagon> getAllVagons() {
        return wagonService.getAllVagons();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Wagon> getVagonByUuid(@PathVariable String uuid) {
        return wagonService.getVagonByUuid(uuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Wagon addVagon(@RequestBody Wagon wagon) {
        return wagonService.saveVagon(wagon);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wagon> updateVagon(@PathVariable Long id, @RequestBody Wagon wagon) {
        if (!wagonService.getVagonByUuid(wagon.getUuid()).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        wagon.setId(id);
        return ResponseEntity.ok(wagonService.saveVagon(wagon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVagon(@PathVariable Long id) {
        wagonService.deleteVagon(id);
        return ResponseEntity.noContent().build();
    }
}
