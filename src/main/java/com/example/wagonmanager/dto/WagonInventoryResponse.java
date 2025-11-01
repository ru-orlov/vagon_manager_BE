package com.example.wagonmanager.dto;

import com.example.wagonmanager.model.InventoryGroup;
import com.example.wagonmanager.model.InventoryItem;
import com.example.wagonmanager.model.Wagon;

import java.util.List;

public class WagonInventoryResponse {
    private Wagon wagon;
    private List<GroupWithItems> groups;

    public WagonInventoryResponse() {}

    public WagonInventoryResponse(Wagon wagon, List<GroupWithItems> groups) {
        this.wagon = wagon;
        this.groups = groups;
    }

    public Wagon getWagon() {
        return wagon;
    }

    public void setWagon(Wagon wagon) {
        this.wagon = wagon;
    }

    public List<GroupWithItems> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupWithItems> groups) {
        this.groups = groups;
    }

    public static class GroupWithItems {
        private InventoryGroup group;
        private List<InventoryItem> items;

        public GroupWithItems() {}

        public GroupWithItems(InventoryGroup group, List<InventoryItem> items) {
            this.group = group;
            this.items = items;
        }

        public InventoryGroup getGroup() {
            return group;
        }

        public void setGroup(InventoryGroup group) {
            this.group = group;
        }

        public List<InventoryItem> getItems() {
            return items;
        }

        public void setItems(List<InventoryItem> items) {
            this.items = items;
        }
    }
}
