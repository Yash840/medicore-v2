package org.cross.medicore.patients.api.dto;

import org.cross.medicore.shared.BloodGroup;
import org.cross.medicore.shared.Sex;

import java.time.LocalDate;

public record UpdatePatientInfoDto(
        long patientId,
        String firstName,
        String lastName,
        String middleName,
        String email,
        String phoneNumber,
        LocalDate birthDate,
        int ageYears,
        int height,
        int weight,
        String aadhaar,
        BloodGroup bloodGroup,
        Sex sex,
        PatientAddressDto address
) {
    public record PatientAddressDto(
            String street,
            String building,
            String landmark,
            String flat,
            String town,
            String city,
            String state,
            String country,
            String zipCode
    ) {}
}