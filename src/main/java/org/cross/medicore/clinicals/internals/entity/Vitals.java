package org.cross.medicore.clinicals.internals.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Vitals {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "vitals_id", nullable = false)
    private Long vitalsId;

    @OneToOne
    @JoinColumn(name = "encounter_id", nullable = false)
    @Setter
    private Encounter encounter;

    @Column(name = "body_temperature_celsius")
    @Setter
    private Double bodyTemperatureCelsius = -100000D;

    @Column(name = "pulse_rate_bpm")
    @Setter
    private Integer pulseRateBpm = -1;

    @Column(name = "respiratory_rate_brpm")
    @Setter
    private Integer respiratoryRateBrPm = -1;

    @Column(name = "blood_pressure_mmhg")
    @Setter
    private Double bloodPressureMmhg = -1D;

    @Column(name = "oxygen_saturation_percent")
    @Setter
    private Double oxygenSaturationPercent = -1D;

    @Column(name = "height_mtr")
    @Setter
    private Double heightMtr = -1D;

    @Column(name = "weight_kg")
    @Setter
    private Double weightKg = -1D;
}
