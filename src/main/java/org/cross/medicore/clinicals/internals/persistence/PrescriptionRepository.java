package org.cross.medicore.clinicals.internals.persistence;

import org.cross.medicore.clinicals.internals.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}
