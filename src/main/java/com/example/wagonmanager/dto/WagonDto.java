package com.example.wagonmanager.dto;


public class WagonDto {
    private String wagonUuid;
    public String number;
    public String type;
    public String createdAt;
    public String updatedAt;
    public String syncStatus;

    public WagonDto() {}
    public WagonDto(WagonDto wagon) {
        this.wagonUuid = wagon.getWagonUuid();
        this.number = wagon.getNumber();
        this.type = wagon.getType();
        this.updatedAt = wagon.getUpdatedAt();
        this.createdAt = wagon.getCreatedAt();
        this.syncStatus = wagon.getSyncStatus();
    }

    public String getWagonUuid() {
        return wagonUuid;
    }

    public void setWagonUuid(String wagonUuid) {
        this.wagonUuid = wagonUuid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }
}
