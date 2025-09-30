package com.example.wagonmanager.dto;

import java.util.Date;

public class InventoryGroupDto {
    private String uuid;
    private String wagonUuid;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private String syncStatus;

    public InventoryGroupDto() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.syncStatus = "synced";
    }

    public InventoryGroupDto(String uuid, String name, String wagonUuid, String description) {
        this();
        this.uuid = uuid;
        this.name = name;
        this.description = description;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getWagonUuid() {
        return wagonUuid;
    }

    public void setWagonUuid(String wagonUuid) {
        this.wagonUuid = wagonUuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }
}
