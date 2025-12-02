package org.cross.medicore.scheduling.internals.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.cross.medicore.scheduling.api.constants.AppointmentStatus;
import org.cross.medicore.scheduling.api.constants.AppointmentType;

@Entity
@Table(name = "appointments",
        indexes =
                {@Index(name = "idx_patient", columnList = "patient_id"),
                @Index(name = "idx_provider", columnList = "provider_id")}
)
@Getter
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long appointmentId;

    @Column(name = "patient_id", nullable = false, updatable = false)
    @Setter
    private long patientId;

    @Column(name = "provider_id", nullable = false, updatable = false)
    @Setter
    private long providerId;

    @OneToOne(optional = false)
    @JoinColumn(name = "slot_id", nullable = false)
    @Setter
    private Slot slot;

    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_type", nullable = false, length = 50)
    @Setter
    private AppointmentType appointmentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_status", nullable = false, length = 50)
    @Setter
    private AppointmentStatus appointmentStatus;

    public Appointment() {
    }
}