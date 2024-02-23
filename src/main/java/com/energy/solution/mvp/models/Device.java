package com.energy.solution.mvp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "device_id", unique = true, length =  128, nullable = false)
    private String deviceId;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name = "modified_at")
    private String modifiedAt;

    @Column(name = "started_at")
    private String startedAt;

    @Column(name = "shutdown_at")
    private String shutdownAt;

    @OneToOne(optional = true)
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "device")
    private List<Configuration> devices;
}
