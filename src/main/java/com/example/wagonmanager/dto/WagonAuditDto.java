package com.example.wagonmanager.dto;

import java.util.Date;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WagonAuditDto {
    public String wagonUuid;
    public String number;
    public Date updatedAt;
    public String userName;
    public String operation;
    public JsonNode oldValue;
    public JsonNode newValue;
}
