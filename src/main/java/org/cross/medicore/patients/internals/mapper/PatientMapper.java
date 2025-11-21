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
    public static String normalizeText(String str){
        return str.trim().replaceAll("\\s+", "");
    }

    public static Patient toPatient(CreatePatientDto createPatientDto) {
        if (createPatientDto == null) {
            return null;
        }
        Patient patient = new Patient();
        patient.setFirstName(normalizeText(createPatientDto.firstName()));
        patient.setLastName(normalizeText(createPatientDto.lastName()));
        patient.setMiddleName(normalizeText(createPatientDto.middleName()));
        patient.setPhoneNumber(createPatientDto.phoneNumber());
        patient.setEmail(createPatientDto.email().toLowerCase());
        patient.setBirthDate(createPatientDto.birthDate());
        return patient;
    }

    public static PatientBriefProfile toPatientBriefProfile(Patient patient) {
        if (patient == null) {
            return null;
        }
        return new PatientBriefProfile(
                patient.getPatientId(),
                patient.getUserId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getMiddleName(),
                patient.getPhoneNumber(),
                patient.getEmail(),
                patient.getBirthDate()
        );
    }

    public static PatientPublicDto toPatientPublicDto(Patient patient) {
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
                toPatientAddressDto(patient.getAddress()),
                patient.getUserId()
        );
    }

    public static void updatePatientFromDto(UpdatePatientInfoDto dto, Patient patient) {
        if (dto == null || patient == null) {
            return;
        }

        if (dto.firstName() != null) {
            patient.setFirstName(dto.firstName());
        }
        if (dto.lastName() != null) {
            patient.setLastName(dto.lastName());
        }
        if (dto.middleName() != null) {
            patient.setMiddleName(dto.middleName());
        }
        if (dto.email() != null) {
            patient.setEmail(dto.email());
        }
        if (dto.phoneNumber() != null) {
            patient.setPhoneNumber(dto.phoneNumber());
        }
        if (dto.birthDate() != null) {
            patient.setBirthDate(dto.birthDate());
        }
        if (dto.height() > 0) {
            patient.setHeight(dto.height());
        }
        if (dto.weight() > 0) {
            patient.setWeight(dto.weight());
        }
        if (dto.aadhaar() != null) {
            patient.setAadhaar(dto.aadhaar());
        }
        if (dto.bloodGroup() != null) {
            patient.setBloodGroup(dto.bloodGroup());
        }
        if (dto.sex() != null) {
            patient.setSex(dto.sex());
        }

        if (dto.address() != null) {
            if (patient.getAddress() == null) {
                patient.setAddress(new PatientAddress());
            }
            updatePatientAddressFromDto(dto.address(), patient.getAddress());
        }
    }

    private static PatientPublicDto.PatientAddressDto toPatientAddressDto(PatientAddress address) {
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

    private static void updatePatientAddressFromDto(UpdatePatientInfoDto.PatientAddressDto dto, PatientAddress address) {
        if (dto == null || address == null) {
            return;
        }
        if (dto.street() != null) {
            address.setStreet(dto.street());
        }
        if (dto.building() != null) {
            address.setBuilding(dto.building());
        }
        if (dto.landmark() != null) {
            address.setLandmark(dto.landmark());
        }
        if (dto.flat() != null) {
            address.setFlat(dto.flat());
        }
        if (dto.town() != null) {
            address.setTown(dto.town());
        }
        if (dto.city() != null) {
            address.setCity(dto.city());
        }
        if (dto.state() != null) {
            address.setState(dto.state());
        }
        if (dto.country() != null) {
            address.setCountry(dto.country());
        }
        if (dto.zipCode() != null) {
            address.setZipCode(dto.zipCode());
        }
    }
}
