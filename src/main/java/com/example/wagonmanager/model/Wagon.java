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
@Table(name = "wagon")
public class Wagon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String uuid;

    @Column(nullable=false)
    private String number;

    @Column
    private String type;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;

    @Column(name="sync_status")
    private String syncStatus;

    @Column(name="username")
    private String userName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "wagon_uuid", referencedColumnName = "uuid")
    private List<InventoryGroup> inventoryGroups = new ArrayList<>();
}