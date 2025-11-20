package org.cross.medicore.patients.api.dto;

import java.time.LocalDate;

public record PatientBriefProfile(
        long patientId,
        String firstName,
        String lastName,
        String middleName,
        String phoneNumber,
        String email,
        LocalDate birthDate
) {
}
