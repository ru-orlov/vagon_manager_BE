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
public class InventoryGroupAuditDto {
    public UUID inventoryGroupUuid;
    public UUID wagonUuid;
    public String operation;
    public String userName;
    public Date updatedAt;
    public JsonNode oldValue;
    public JsonNode newValue;
}
