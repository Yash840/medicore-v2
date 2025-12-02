package org.cross.medicore.scheduling.internals.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Break {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long breakId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "provider_schedule_id", nullable = false)
    private ProviderSchedule providerSchedule;

    @Column(name = "start", nullable = false)
    @Setter
    private LocalDateTime start;

    @Column(name = "end", nullable = false)
    @Setter
    private LocalDateTime end;

    @Column(name = "break_name", nullable = false)
    @Setter
    private String breakName;

    public Break() {
    }

    public Break(ProviderSchedule providerSchedule) {
        this.providerSchedule = providerSchedule;
    }

    public Break(ProviderSchedule providerSchedule, LocalDateTime start, LocalDateTime end, String breakName) {
        this.providerSchedule = providerSchedule;
        this.start = start;
        this.end = end;
        this.breakName = breakName;
    }
}
