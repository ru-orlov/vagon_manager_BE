package com.example.wagonmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class WagonDto {
    private UUID uuid;
    public String number;
    public String type;
    public String createdAt;
    public String updatedAt;
    public String syncStatus;
}
