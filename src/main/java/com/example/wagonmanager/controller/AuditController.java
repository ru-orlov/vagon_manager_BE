package com.example.wagonmanager.controller;

import com.example.wagonmanager.dto.AuditEntry;
import com.example.wagonmanager.dto.PagedResponse;
import com.example.wagonmanager.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/v1/audit")
public class AuditController {
    private final AuditService auditService;

    @Autowired
    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    /**
     * GET /api/v1/audit/wagon/{wagonUuid}?page=0&size=50&since=2025-10-01T00:00:00Z
     * Reads from wagon_audit table.
     */
    @GetMapping("/audit/wagon/{wagonUuid}")
    public PagedResponse<AuditEntry> getWagonAudit(
            @PathVariable String wagonUuid,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "50") int size,
            @RequestParam(name = "since", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime since
    ) {
        String table = "wagon_audit"; // adjust if your audit table name differs
        Optional<OffsetDateTime> sinceOpt = Optional.ofNullable(since);
        List<AuditEntry> entries = auditService.queryAuditTable(table, wagonUuid, sinceOpt, page, size);
        long total = auditService.countAuditTable(table, wagonUuid, sinceOpt);
        PagedResponse.PageableInfo pinfo = new PagedResponse.PageableInfo(page, size);
        return new PagedResponse<>(entries, pinfo, total);
    }

    /**
     * GET /api/v1/audit/item/{itemUuid}?page=0&size=50&since=...
     * Reads from inventory_item_audit table.
     */
    @GetMapping("/audit/item/{itemUuid}")
    public PagedResponse<AuditEntry> getItemAudit(
            @PathVariable String itemUuid,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "50") int size,
            @RequestParam(name = "since", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime since
    ) {
        String table = "inventory_item_audit"; // adjust if different
        Optional<OffsetDateTime> sinceOpt = Optional.ofNullable(since);
        List<AuditEntry> entries = auditService.queryAuditTable(table, itemUuid, sinceOpt, page, size);
        long total = auditService.countAuditTable(table, itemUuid, sinceOpt);
        PagedResponse.PageableInfo pinfo = new PagedResponse.PageableInfo(page, size);
        return new PagedResponse<>(entries, pinfo, total);
    }
}
