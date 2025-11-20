package org.cross.medicore.patients.internals.persistence;

import org.cross.medicore.patients.internals.entity.Patient;
import org.cross.medicore.patients.internals.entity.PatientStatus;
import org.cross.medicore.shared.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    long countByStatus(PatientStatus status);

    long countBySex(Sex sex);

    long countByStatusAndSex(PatientStatus status, Sex sex);
}
