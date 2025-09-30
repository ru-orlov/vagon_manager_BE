package com.example.wagonmanager.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InventoryItemDto {
    private String uuid;
    private String groupId;
    private String wagonUuid;
    private String name;
    private String description;
    private int quantity;
    private List<String> photos = new ArrayList<>();
    private Date createdAt;
    private Date updatedAt;
    private String syncStatus;

    public InventoryItemDto() {}

    public InventoryItemDto(String uuid, String groupId, String wagonUuid, String name, String description,
                            int quantity, Date createdAt, Date updatedAt, String syncStatus) {
        this.uuid = uuid;
        this.groupId = groupId;
        this.wagonUuid = wagonUuid;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.syncStatus = syncStatus;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
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
