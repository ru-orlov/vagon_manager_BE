package com.example.wagonmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class InventoryItemDto {
    private String uuid;
    private String groupUuid;
    private String wagonUuid;
    private String name;
    private String description;
    private int quantity;
    private String photos;
    private Date createdAt;
    private Date updatedAt;
    private String syncStatus;
    private String photoSyncStatus;
}
