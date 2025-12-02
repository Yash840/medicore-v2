package org.cross.medicore.clinicals.internals.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "encounter_id")
    @Setter
    private Encounter encounter;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medication> medications = new ArrayList<>();

    public void addMedication(Medication medication){
        if(!Objects.isNull(medication))
            this.medications.add(medication);
    }

    public void addMedication(
            String name,
            String dosage,
            String pattern,
            int quantity,
            String guidelines
    ){
        Medication medication = Medication.builder()
                .prescription(this)
                .name(name)
                .dosage(dosage)
                .pattern(pattern)
                .quantity(quantity)
                .guidelines(guidelines)
                .build();

        addMedication(medication);
    }
}
