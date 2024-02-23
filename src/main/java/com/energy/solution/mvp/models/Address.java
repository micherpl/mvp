package com.energy.solution.mvp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @Column(length =  128)
    private String name;

    @Column(length =  128, nullable = false)
    private String street;

    @Column(name = "building_number", length =  128, nullable = false)
    private String buildingNumber;

    @Column(name = "apartment_number",length =  128)
    private String apartmentNumber;

    @Column(length =  128, nullable = false)
    private String city;

    @Column(name = "postal_code",length =  128, nullable = false)
    private String postalCode;

    @Column(length =  128, nullable = false)
    private String country;
}
