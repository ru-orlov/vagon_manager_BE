package com.example.wagonmanager.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class InventoryItemAuditDto {
    private UUID inventoryItemUuid;
    private UUID groupUuid;
    private UUID wagonUuid;
    private String userName;
    private String operation;
    private Date updatedAt;
    public JsonNode oldValue;
    public JsonNode newValue;
}
