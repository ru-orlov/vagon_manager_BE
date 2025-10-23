package com.example.wagonmanager.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public InventoryItemDto() {}

    public InventoryItemDto(String uuid, String groupUuid, String wagonUuid, String name, String description,
                            int quantity, Date createdAt, Date updatedAt, String syncStatus, String photoSyncStatus) {
        this.uuid = uuid;
        this.groupUuid = groupUuid;
        this.wagonUuid = wagonUuid;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.syncStatus = syncStatus;
        this.photoSyncStatus = photoSyncStatus;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getGroupUuid() {
        return groupUuid;
    }

    public void setGroupUuid(String groupUuid) {
        this.groupUuid = groupUuid;
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

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
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

    public String getPhotoSyncStatus() {
        return photoSyncStatus;
    }

    public void setPhotoSyncStatus(String photoSyncStatus) {
        this.photoSyncStatus = photoSyncStatus;
    }
}
