package com.example.wagonmanager.dto;

import com.example.wagonmanager.model.InventoryGroup;
import com.example.wagonmanager.model.InventoryItem;
import com.example.wagonmanager.model.Wagon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SyncPayload {
    private List<Wagon> wagons;
    private List<InventoryGroup> inventoryGroups;
    private List<InventoryItem> inventoryItems;
}
