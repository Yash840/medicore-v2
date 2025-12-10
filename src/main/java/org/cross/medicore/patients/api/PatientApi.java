package org.cross.medicore.patients.api;


import org.cross.medicore.patients.api.dto.CreatePatientDto;
import org.cross.medicore.patients.api.dto.PatientBriefProfile;
import org.cross.medicore.patients.api.dto.UpdatePatientInfoDto;
import org.cross.medicore.patients.internals.entity.Patient;
import org.cross.medicore.patients.api.dto.PatientPublicDto;

import java.util.List;

public interface PatientApi {
    int countPatients(String status, String sex);

    List<PatientPublicDto> getAllPatients();

    List<PatientPublicDto> getAllActivePatients();

    PatientPublicDto getPatientById(long patientId);

    Patient getPatientReference(long patientId);

    PatientBriefProfile registerPatient(CreatePatientDto dto, long userId);

    PatientPublicDto updatePatientInfo(long patientId, UpdatePatientInfoDto dto);

    PatientPublicDto deletePatient(long patientId);
}
