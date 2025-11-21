package org.cross.medicore.patients.internals.persistence;

import org.cross.medicore.patients.internals.entity.Patient;
import org.cross.medicore.patients.internals.entity.PatientStatus;
import org.cross.medicore.shared.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    int countByStatus(PatientStatus status);

    int countBySex(Sex sex);

    int countByStatusAndSex(PatientStatus status, Sex sex);

    List<Patient> findAllByStatus(PatientStatus status);
}
