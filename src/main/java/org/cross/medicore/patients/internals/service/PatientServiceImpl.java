package org.cross.medicore.patients.internals.service;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.patients.api.dto.PatientBriefProfile;
import org.cross.medicore.patients.api.dto.PatientPublicDto;
import org.cross.medicore.patients.internals.dto.CreatePatientDto;
import org.cross.medicore.patients.internals.dto.UpdatePatientInfoDto;
import org.cross.medicore.patients.internals.entity.Patient;
import org.cross.medicore.patients.internals.persistence.PatientManager;
import org.cross.medicore.patients.internals.persistence.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class PatientServiceImpl implements PatientService{
    private final PatientRepository patientRepository;
    private final PatientManager patientManager;

    @Override
    public PatientBriefProfile registerPatient(CreatePatientDto dto) {
        return null;
    }

    @Override
    public PatientPublicDto updatePatientInfo(long patientId, UpdatePatientInfoDto dto) {
        return null;
    }

    @Override
    public PatientPublicDto deletePatient(long patientId) {
        return null;
    }

    @Override
    public int countPatients(String status, String sex) {
        return 0;
    }

    @Override
    public List<PatientPublicDto> getAllPatients() {
        return List.of();
    }

    @Override
    public List<PatientPublicDto> getAllActivePatients() {
        return List.of();
    }

    @Override
    public PatientPublicDto getPatientById(long patientId) {
        return null;
    }

    @Override
    public Patient getPatientReference(long patientId) {
        return null;
    }
}
