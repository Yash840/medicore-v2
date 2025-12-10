package org.cross.medicore.orchestration.orchestrator;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.patients.api.PatientApi;
import org.cross.medicore.patients.api.dto.CreatePatientDto;
import org.cross.medicore.patients.api.dto.PatientBriefProfile;
import org.cross.medicore.patients.api.dto.PatientPublicDto;
import org.cross.medicore.patients.api.dto.UpdatePatientInfoDto;
import org.cross.medicore.security.api.UserApi;
import org.cross.medicore.security.api.dto.UserDetailsDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PatientsOrchestrator {
    private final PatientApi patientApi;
    private final UserApi userApi;

    @Transactional
    public PatientBriefProfile createPatient(CreatePatientDto dto, String username, String password){
        UserDetailsDto user = userApi.createPatientUser(username, password);
        long userId = user.userId();

        return patientApi.registerPatient(dto, userId);
    }

    public PatientPublicDto updatePatientDetails(long patientId, UpdatePatientInfoDto dto){
        return patientApi.updatePatientInfo(patientId, dto);
    }

    public PatientPublicDto getPatientDetails(long patientId){
        return patientApi.getPatientById(patientId);
    }

    public PatientPublicDto deletePatient(long patientId){
        PatientPublicDto patientDetails = getPatientDetails(patientId);
        long associatedUserId = patientDetails.userId();

        userApi.deleteUser(associatedUserId);
        patientApi.deletePatient(patientId);

        return patientDetails;
    }
}
