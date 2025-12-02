package org.cross.medicore.scheduling.internals.persistence;

import org.cross.medicore.scheduling.internals.entity.Appointment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findBySlot_ProviderSchedule_ScheduleDateOrderBySlot_StartAsc(LocalDate scheduleDate);

    List<Appointment> findByPatientIdOrderBySlot_ProviderSchedule_ScheduleDateAsc(long patientId);

    List<Appointment> findByProviderIdOrderBySlot_ProviderSchedule_ScheduleDateAsc(long patientId);

    List<Appointment> findByPatientIdAndSlot_ProviderSchedule_ScheduleDateOrderBySlot_StartAsc(long patientId, LocalDate date);

    List<Appointment> findByProviderIdAndSlot_ProviderSchedule_ScheduleDateOrderBySlot_StartAsc(long providerId, LocalDate date);
}