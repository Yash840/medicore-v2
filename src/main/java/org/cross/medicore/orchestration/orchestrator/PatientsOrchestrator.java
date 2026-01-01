package org.cross.medicore.orchestration.orchestrator;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.auditing.annotation.Auditable;
import org.cross.medicore.patients.api.PatientApi;
import org.cross.medicore.patients.api.dto.CreatePatientDto;
import org.cross.medicore.patients.api.dto.PatientBriefProfile;
import org.cross.medicore.patients.api.dto.PatientPublicDto;
import org.cross.medicore.patients.api.dto.UpdatePatientInfoDto;
import org.cross.medicore.security.api.UserApi;
import org.cross.medicore.security.api.dto.UserDetailsDto;
import org.cross.medicore.shared.Action;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PatientsOrchestrator {
    private final PatientApi patientApi;
    private final UserApi userApi;

    @Transactional
    @Auditable(action = Action.CREATE_PATIENT, message = "Orchestrated patient creation with username: ?2")
    public PatientBriefProfile createPatient(CreatePatientDto dto, String username, String password){
        UserDetailsDto user = userApi.createPatientUser(username, password);
        long userId = user.userId();

        return patientApi.registerPatient(dto, userId);
    }

    @Auditable(action = Action.MODIFY_PATIENT_DETAILS, message = "Orchestrated patient update for patientId: ?1")
    public PatientPublicDto updatePatientDetails(long patientId, UpdatePatientInfoDto dto){
        return patientApi.updatePatientInfo(patientId, dto);
    }

    @Auditable(action = Action.READ_PATIENT_DETAILS, message = "Orchestrated fetch patient details for patientId: ?1")
    public PatientPublicDto getPatientDetails(long patientId){
        return patientApi.getPatientById(patientId);
    }

    @Transactional
    @Auditable(action = Action.DELETE_PATIENT, message = "Orchestrated patient deletion for patientId: ?1")
    public PatientPublicDto deletePatient(long patientId){
        PatientPublicDto patientDetails = getPatientDetails(patientId);
        long associatedUserId = patientDetails.userId();

        userApi.deleteUser(associatedUserId);
        patientApi.deletePatient(patientId);

        return patientDetails;
    }
}
