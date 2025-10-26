package com.example.wagonmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "inventory_item")
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String uuid;

    @Column(name="group_uuid", nullable=false)
    private String groupUuid;

    @Column(name="wagon_uuid", nullable=false)
    private String wagonUuid;

    @Column(nullable=false)
    private String name;

    @Column
    private String description;

    @Column
    private String photos;

    @Column(nullable=false)
    private int quantity;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;

    @Column(name="sync_status")
    private String syncStatus;

    @Column(name="photo_sync_status")
    private String photoSyncStatus;

    @Column(name="username")
    private String userName;
}
