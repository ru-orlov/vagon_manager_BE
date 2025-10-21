package com.example.wagonmanager.dto;

import com.google.gson.annotations.SerializedName;

public class PhotoUploadResponse {
    @SerializedName("success")
    private boolean success;

    // Уникальный идентификатор фото на сервере (может быть UUID или id)
    @SerializedName("photoId")
    private String photoId;

    // Имя файла, под которым фото сохранено на сервере
    @SerializedName("filename")
    private String filename;

    // Публичный URL или относительный путь к файлу для скачивания
    @SerializedName("url")
    private String url;

    // Сообщение об ошибке/информация
    @SerializedName("message")
    private String message;

    // Привязка к inventoryItem (если сервер возвращает)
    @SerializedName("inventoryItemUuid")
    private String inventoryItemUuid;

    public PhotoUploadResponse() {
    }

    // --- getters / setters ---
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInventoryItemUuid() {
        return inventoryItemUuid;
    }

    public void setInventoryItemUuid(String inventoryItemUuid) {
        this.inventoryItemUuid = inventoryItemUuid;
    }
}
