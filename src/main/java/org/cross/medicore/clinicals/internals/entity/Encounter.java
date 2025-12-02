package org.cross.medicore.clinicals.internals.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.cross.medicore.clinicals.api.constants.EncounterStatus;

@Entity
@Table(name = "encounters")
@Getter
public class Encounter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long encounterId;

    @Column(name = "appointment_id", nullable = false, updatable = false)
    private long appointmentId;

    @OneToOne(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true)
    private Vitals vitals = new Vitals();

    @OneToOne(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true)
    private Prescription prescription = new Prescription();

    @Enumerated(EnumType.STRING)
    @Column(name = "encounter_status", nullable = false, length = 50)
    private EncounterStatus encounterStatus = EncounterStatus.ONGOING;

    @Version
    @Column(name = "version")
    private Integer version;

    public void endEncounter(){
        this.encounterStatus = EncounterStatus.ENDED;
    }

    public boolean isOngoing(){
        return this.encounterStatus.equals(EncounterStatus.ONGOING);
    }

    public Encounter() {
    }

    public Encounter(long appointmentId) {
        this.appointmentId = appointmentId;
    }
}
