package org.cross.medicore.clinicals.internals.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "medication_id", nullable = false)
    private long medicationId;

    @ManyToOne()
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dosage", nullable = false)
    private String dosage;

    @Column(name = "pattern", nullable = false)
    private String pattern;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    private String guidelines;
}
