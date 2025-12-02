package org.cross.medicore.scheduling.internals.persistence;

import org.cross.medicore.patients.internals.entity.PatientAddress;
import org.springframework.data.repository.Repository;

public interface PatientAddressRepository extends Repository<PatientAddress, Long> {
}