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
@Table(name = "inventory_groups")
public class InventoryGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String uuid;

    @Column(nullable=false)
    private String name;

    @Column
    private String description;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;

    @Column(name="sync_status")
    private String syncStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vagon_uuid", referencedColumnName = "uuid")
    private Vagon vagon;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryItem> items = new ArrayList<>();

    // getters/setters
}