package org.cross.medicore.clinicals.internals.persistence;

import org.cross.medicore.clinicals.internals.entity.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EncounterRepository extends JpaRepository<Encounter, Long> {
    Optional<Encounter> findByAppointmentId(long appointmentId);
}
