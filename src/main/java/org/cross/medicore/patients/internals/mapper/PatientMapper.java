package org.cross.medicore.patients.internals.mapper;

import org.cross.medicore.patients.api.dto.PatientBriefProfile;
import org.cross.medicore.patients.api.dto.PatientPublicDto;
import org.cross.medicore.patients.internals.dto.CreatePatientDto;
import org.cross.medicore.patients.internals.dto.UpdatePatientInfoDto;
import org.cross.medicore.patients.internals.entity.Patient;
import org.cross.medicore.patients.internals.entity.PatientAddress;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {
    public Patient toPatient(CreatePatientDto createPatientDto) {
        if (createPatientDto == null) {
            return null;
        }
        Patient patient = new Patient();
        patient.setFirstName(createPatientDto.firstName());
        patient.setLastName(createPatientDto.lastName());
        patient.setMiddleName(createPatientDto.middleName());
        patient.setPhoneNumber(createPatientDto.phoneNumber());
        patient.setEmail(createPatientDto.email());
        patient.setBirthDate(createPatientDto.birthDate());
        return patient;
    }

    public PatientBriefProfile toPatientBriefProfile(Patient patient) {
        if (patient == null) {
            return null;
        }
        return new PatientBriefProfile(
                patient.getPatientId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getMiddleName(),
                patient.getPhoneNumber(),
                patient.getEmail(),
                patient.getBirthDate()
        );
    }

    public PatientPublicDto toPatientPublicDto(Patient patient) {
        if (patient == null) {
            return null;
        }
        return new PatientPublicDto(
                patient.getPatientId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getMiddleName(),
                patient.getEmail(),
                patient.getPhoneNumber(),
                patient.getBirthDate(),
                patient.getAgeYears(),
                patient.getHeight(),
                patient.getWeight(),
                patient.getAadhaar(),
                patient.getBloodGroup(),
                patient.getSex(),
                patient.getStatus(),
                toPatientAddressDto(patient.getAddress())
        );
    }

    public void updatePatientFromDto(UpdatePatientInfoDto dto, Patient patient) {
        if (dto == null || patient == null) {
            return;
        }
        patient.setFirstName(dto.firstName());
        patient.setLastName(dto.lastName());
        patient.setMiddleName(dto.middleName());
        patient.setEmail(dto.email());
        patient.setPhoneNumber(dto.phoneNumber());
        patient.setBirthDate(dto.birthDate());
        patient.setHeight(dto.height());
        patient.setWeight(dto.weight());
        patient.setAadhaar(dto.aadhaar());
        patient.setBloodGroup(dto.bloodGroup());
        patient.setSex(dto.sex());

        if (dto.address() != null) {
            if (patient.getAddress() == null) {
                patient.setAddress(new PatientAddress());
            }
            updatePatientAddressFromDto(dto.address(), patient.getAddress());
        }
    }

    private PatientPublicDto.PatientAddressDto toPatientAddressDto(PatientAddress address) {
        if (address == null) {
            return null;
        }
        return new PatientPublicDto.PatientAddressDto(
                address.getStreet(),
                address.getBuilding(),
                address.getLandmark(),
                address.getFlat(),
                address.getTown(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getZipCode()
        );
    }

    private void updatePatientAddressFromDto(UpdatePatientInfoDto.PatientAddressDto dto, PatientAddress address) {
        if (dto == null || address == null) {
            return;
        }
        address.setStreet(dto.street());
        address.setBuilding(dto.building());
        address.setLandmark(dto.landmark());
        address.setFlat(dto.flat());
        address.setTown(dto.town());
        address.setCity(dto.city());
        address.setState(dto.state());
        address.setCountry(dto.country());
        address.setZipCode(dto.zipCode());
    }
}
