package com.example.wagonmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class InventoryGroupDto {
    private String uuid;
    private String wagonUuid;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private String syncStatus;
}
