package org.cross.medicore.clinicals.internals.persistence;

import org.cross.medicore.clinicals.internals.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
