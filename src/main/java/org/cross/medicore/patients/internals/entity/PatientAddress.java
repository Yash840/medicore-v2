package org.cross.medicore.patients.internals.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class PatientAddress {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long patientAddressId;

    @OneToOne(mappedBy = "address")
    @Getter
    @Setter
    private Patient patient;

    @Column(name = "street", length = 100)
    @Getter @Setter
    private String street;

    @Column(name = "building", length = 100)
    @Getter @Setter
    private String building;

    @Column(name = "landmark", length = 100)
    @Getter @Setter
    private String landmark;

    @Column(name = "flat", length = 100)
    @Getter @Setter
    private String flat;

    @Column(name = "town", length = 100)
    @Getter @Setter
    private String town;

    @Column(name = "city", length = 100)
    @Getter @Setter
    private String city;

    @Column(name = "state", length = 100)
    @Getter @Setter
    private String state;

    @Column(name = "country", length = 100)
    @Getter @Setter
    private String country;

    @Column(name = "zipCode", length = 10)
    @Getter @Setter
    private String zipCode;
}
