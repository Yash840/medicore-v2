package org.cross.medicore.scheduling.internals.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class ProviderSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "provider_schedule_id", nullable = false)
    private long id;

    @Column(name = "provider_id", nullable = false)
    @Setter
    private long providerId;

    @Setter
    @Column(name = "schedule_date", nullable = false)
    private LocalDate scheduleDate;

    @Column(name = "schedule_start_time", nullable = false)
    @Setter
    private LocalDateTime scheduleStartTime;

    @Column(name = "schedule_end_time", nullable = false)
    @Setter
    private LocalDateTime scheduleEndTime;

    @OneToMany(mappedBy = "providerSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<Slot> slots;

    @OneToMany(mappedBy = "providerSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("Break.start ASC")
    @Setter
    private List<Break> breaks;

}