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
@Table(name = "inventory_group")
public class InventoryGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private UUID uuid;

    @Column(nullable=false)
    private String name;

    @Column
    private String description;

    @Column(name="wagon_uuid")
    private UUID wagonUuid;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;

    @Column(name="sync_status")
    private String syncStatus;

    @Column(name="username")
    private String userName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_uuid", referencedColumnName = "uuid")
    private List<InventoryItem> inventoryItems = new ArrayList<>();
}