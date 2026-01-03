package org.cross.medicore.scheduling.internals.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cross.medicore.scheduling.api.constants.SlotStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "slot_id", nullable = false)
    private long slotId;

    @ManyToOne()
    @JoinColumn(name = "provider_schedule_id")
    private ProviderSchedule providerSchedule;

    @Column(name = "provider_id", nullable = false)
    private long providerId;

    @Column(name = "start", nullable = false)
    private LocalDateTime start;

    @Column(name = "end", nullable = false)
    private LocalDateTime end;

    @OneToOne(mappedBy = "slot")
    @Setter
    private Appointment appointment;

    @Enumerated(EnumType.STRING)
    @Setter
    @Column(name = "slot_status", nullable = false, length = 50)
    private SlotStatus slotStatus;

    @Version
    @Column(name = "version", nullable = false)
    private int version;

    public Slot(ProviderSchedule providerSchedule, long providerId, LocalDateTime start, LocalDateTime end) {
        this.providerSchedule = providerSchedule;
        this.providerId = providerId;
        this.start = start;
        this.end = end;
    }

    public Slot(ProviderSchedule providerSchedule, long providerId, LocalDateTime start, LocalDateTime end, SlotStatus slotStatus) {
        this.providerSchedule = providerSchedule;
        this.providerId = providerId;
        this.start = start;
        this.end = end;
        this.slotStatus = slotStatus;
    }
}
