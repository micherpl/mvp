package com.energy.solution.mvp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "device_configuration")
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private Device device;

    @Column(name = "device_identifier", unique = true, length =  128, nullable = false)
    private String deviceIdentifier;

    @Lob
    @Column(nullable = false)
    private String configuration;

    @Column(name="created_at", nullable = false)
    private String createdAt;

    @Column(name="modified_at", nullable = false)
    private String modifiedAt;

}
