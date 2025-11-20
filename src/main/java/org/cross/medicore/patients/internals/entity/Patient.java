package org.cross.medicore.patients.internals.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.cross.medicore.shared.BloodGroup;
import org.cross.medicore.shared.Sex;

import java.time.LocalDate;

@Entity
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private long patientId;

    @Column(name = "first_name", nullable = false, length = 50)
    @Getter @Setter
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @Getter @Setter
    private String lastName;

    @Column(name = "middle_name", length = 50)
    @Getter @Setter
    private String middleName;

    @Column(name = "email", length = 150)
    @Getter @Setter
    private String email;

    @Column(name = "phone_number", nullable = false, length = 20)
    @Getter @Setter
    private String phoneNumber;

    @Column(name = "birth_date", nullable = false)
    @Getter @Setter
    private LocalDate birthDate;

    @Column(name = "height")
    @Getter @Setter
    private int height;

    @Column(name = "weight")
    @Getter @Setter
    private int weight;

    @Column(name = "aadhaar")
    @Getter @Setter
    private String aadhaar;

    @Column(name = "blood_group")
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @Column(name = "sex")
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", unique = true)
    @Getter @Setter
    private PatientAddress address;
}
