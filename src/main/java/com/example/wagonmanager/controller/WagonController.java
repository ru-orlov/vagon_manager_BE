package com.example.wagonmanager.controller;

import com.example.wagonmanager.dto.PagedResponse;
import com.example.wagonmanager.model.Wagon;
import com.example.wagonmanager.repository.WagonRepository;
import com.example.wagonmanager.service.WagonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wagon")
public class WagonController {

    private final WagonService wagonService;
    private final WagonRepository wagonRepository;

    @Autowired
    public WagonController(WagonService wagonService, WagonRepository wagonRepository) {
        this.wagonService = wagonService;
        this.wagonRepository = wagonRepository;
    }

    @GetMapping
    public List<Wagon> getAllWagons() {
        return wagonService.getAllWagons();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Wagon> getWagonByUuid(@PathVariable String uuid) {
        return wagonService.getWagonByUuid(UUID.fromString(uuid))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Wagon addWagon(@RequestBody Wagon wagon) {
        return wagonService.saveWagon(wagon);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wagon> updateWagon(@PathVariable Long id, @RequestBody Wagon wagon) {
        if (!wagonService.getWagonByUuid(wagon.getUuid()).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        wagon.setId(id);
        return ResponseEntity.ok(wagonService.saveWagon(wagon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWagon(@PathVariable Long id) {
        wagonService.deleteWagonByUuid(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/v1/wagon?page=0&size=20&sort=updatedAt,desc&search=123
     * Returns Page-like response with content and pageable info.
     */
    @GetMapping("/wagon")
    public PagedResponse<Wagon> getWagons(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size,
            @RequestParam(name = "sort", defaultValue = "updatedAt,desc") String sort
    ) {
        Sort sortObj = Sort.by(Sort.Direction.DESC, "updatedAt");
        if (sort != null && !sort.isEmpty()) {
            String[] parts = sort.split(",");
            if (parts.length >= 1) {
                String prop = parts[0];
                Sort.Direction dir = (parts.length > 1 && parts[1].equalsIgnoreCase("asc")) ? Sort.Direction.ASC : Sort.Direction.DESC;
                sortObj = Sort.by(dir, prop);
            }
        }
        Pageable pageable = PageRequest.of(page, size, sortObj);
        Page<Wagon> pageResult;
        if (search == null || search.isBlank()) {
            pageResult = wagonRepository.findAll(pageable);
        } else {
            pageResult = wagonRepository.findByNumberContainingIgnoreCaseOrTypeContainingIgnoreCase(search, search, pageable);
        }

        PagedResponse.PageableInfo pinfo = new PagedResponse.PageableInfo(pageResult.getNumber(), pageResult.getSize());
        return new PagedResponse<>(pageResult.getContent(), pinfo, pageResult.getTotalElements());
    }
}
