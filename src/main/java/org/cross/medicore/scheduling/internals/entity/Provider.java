package org.cross.medicore.scheduling.internals.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.cross.medicore.shared.BloodGroup;
import org.cross.medicore.shared.Sex;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "providers")
public class Provider {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long providerId;

    @Column(name = "first_name", nullable = false, length = 50)
    @Setter
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @Setter
    private String lastName;

    @Column(name = "middle_name", length = 50)
    @Setter
    private String middleName;

    @Column(name = "email", length = 150)
    @Setter
    private String email;

    @Column(name = "phone_number", length = 20)
    @Setter
    private String phoneNumber;

    @Column(name = "birth_date")
    @Setter
    private LocalDate birthDate;

    @Column(name = "blood_group")
    @Setter
    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @Column(name = "sex")
    @Setter
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", unique = true)
    @Setter
    private ProviderAddress address;

    @Column(name = "specialization", nullable = false)
    @Setter
    private String specialization;

    @Setter
    @Column(name = "mmc_reg_number", unique = true, length = 50)
    private String mmcRegNumber;

    @Column(name = "user_id", unique = true, updatable = false, nullable = false)
    @Setter
    private long userId;
}
