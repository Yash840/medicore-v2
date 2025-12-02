package org.cross.medicore.scheduling.internals.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "provider_addresses")
public class ProviderAddress {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long providerAddressId;

    @OneToOne(mappedBy = "address")
    @Setter
    private Provider provider;

    @Column(name = "street", length = 100)
    @Setter
    private String street;

    @Column(name = "building", length = 100)
    @Setter
    private String building;

    @Column(name = "landmark", length = 100)
    @Setter
    private String landmark;

    @Column(name = "flat", length = 100)
    @Setter
    private String flat;

    @Column(name = "town", length = 100)
    @Setter
    private String town;

    @Column(name = "city", length = 100)
    @Setter
    private String city;

    @Column(name = "state", length = 100)
    @Setter
    private String state;

    @Column(name = "country", length = 100)
    @Setter
    private String country;

    @Column(name = "zipCode", length = 10)
    @Setter
    private String zipCode;
}
