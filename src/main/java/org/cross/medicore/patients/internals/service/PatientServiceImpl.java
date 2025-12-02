package org.cross.medicore.patients.internals.service;

import lombok.RequiredArgsConstructor;
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
import org.cross.medicore.shared.Sex;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class PatientServiceImpl implements PatientService{
    private final PatientRepository patientRepository;
    private final PatientManager patientManager;

    @Override
    @Transactional
    public PatientBriefProfile registerPatient(CreatePatientDto dto) {
           Patient patient = patientRepository.save(PatientMapper.toPatient(dto));
           return PatientMapper.toPatientBriefProfile(patient);
    }

    @Override
    @Transactional
    public PatientPublicDto updatePatientInfo(long patientId, UpdatePatientInfoDto dto) {
            Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException("Invalid Patient Id"));
            PatientMapper.updatePatientFromDto(dto, patient);
            return PatientMapper.toPatientPublicDto(patient);
    }

    @Override
    @Transactional
    public PatientPublicDto deletePatient(long patientId) {
            Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException("Invalid Patient Id"));
            PatientPublicDto dto = PatientMapper.toPatientPublicDto(patient);
            patientRepository.delete(patient);
            return dto;
    }

    @Override
    @Transactional(readOnly = true)
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
    public List<PatientPublicDto> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(PatientMapper::toPatientPublicDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientPublicDto> getAllActivePatients() {
        return patientRepository.findAllByStatus(PatientStatus.ACTIVE)
                .stream()
                .map(PatientMapper::toPatientPublicDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
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
