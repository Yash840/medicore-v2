package org.cross.medicore.patients.internals.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cross.medicore.auditing.annotation.Auditable;
import org.cross.medicore.exception.PatientNotFoundException;
import org.cross.medicore.patients.api.dto.PatientBriefProfile;
import org.cross.medicore.patients.api.dto.PatientPublicDto;
import org.cross.medicore.patients.api.dto.CreatePatientDto;
import org.cross.medicore.patients.api.dto.UpdatePatientInfoDto;
import org.cross.medicore.patients.internals.entity.Patient;
import org.cross.medicore.patients.internals.entity.PatientStatus;
import org.cross.medicore.patients.internals.mapper.PatientMapper;
import org.cross.medicore.patients.internals.persistence.PatientManager;
import org.cross.medicore.patients.internals.persistence.PatientRepository;
import org.cross.medicore.shared.Action;
import org.cross.medicore.shared.Sex;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
class PatientServiceImpl implements PatientService{
    private final PatientRepository patientRepository;
    private final PatientManager patientManager;

    @Override
    @Transactional
    @Auditable(action = Action.CREATE_PATIENT, message = "Registered new patient with userId: ?2")
    @PreAuthorize("hasAuthority('ADD_PATIENT')")
    public PatientBriefProfile registerPatient(CreatePatientDto dto, long userId) {
           log.info("registerPatient: attempt to register patient with userId {} and details {}", userId, dto);

            Patient patient = PatientMapper.toPatient(dto);
            patient.setUserId(userId);

            Patient saved = patientRepository.save(patient);

            // TODO: Remove this log after testing
            log.info("registerPatient: patient saved in db Patient: {}", saved);

            return PatientMapper.toPatientBriefProfile(saved);
    }

    @Override
    @Transactional
    @Auditable(action = Action.MODIFY_PATIENT_DETAILS, message = "Updated patient details for patientId: ?1")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('READ_PATIENT_INFO')")
    public PatientPublicDto updatePatientInfo(long patientId, UpdatePatientInfoDto dto) {
            Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException("Invalid Patient Id"));
            PatientMapper.updatePatientFromDto(dto, patient);
            return PatientMapper.toPatientPublicDto(patient);
    }

    @Override
    @Transactional
    @Auditable(action = Action.DELETE_PATIENT, message = "Deleted patient with patientId: ?1")
    @PreAuthorize("hasRole('ADMIN')")
    public PatientPublicDto deletePatient(long patientId) {
            Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException("Invalid Patient Id"));
            PatientPublicDto dto = PatientMapper.toPatientPublicDto(patient);
            patientRepository.delete(patient);
            return dto;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('READ_PATIENT_INFO')")
    public int countPatients(String status, String sex) {
            if(Objects.isNull(status)){
                return patientRepository.countBySex(Sex.fromString(sex));
            }
            if(Objects.isNull(sex)){
                return patientRepository.countByStatus(PatientStatus.fromString(status));
            }

            return patientRepository.countByStatusAndSex(
                    PatientStatus.fromString(status),
                    Sex.fromString(sex)
            );
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('READ_PATIENT_INFO')")
    public List<PatientPublicDto> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(PatientMapper::toPatientPublicDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('READ_PATIENT_INFO')")
    public List<PatientPublicDto> getAllActivePatients() {
        return patientRepository.findAllByStatus(PatientStatus.ACTIVE)
                .stream()
                .map(PatientMapper::toPatientPublicDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    @Auditable(action = Action.READ_PATIENT_DETAILS, message = "Fetched patient details for patientId: ?1")
    @PreAuthorize("hasAuthority('READ_PATIENT_INFO')")
    public PatientPublicDto getPatientById(long patientId) {
            Patient patient = patientRepository.findById(patientId)
                    .orElseThrow(() -> new PatientNotFoundException("Invalid Patient Id"));

            return PatientMapper.toPatientPublicDto(patient);
    }

    @Override
    public Patient getPatientReference(long patientId) {
        return patientManager.getPatientRef(patientId);
    }
}
