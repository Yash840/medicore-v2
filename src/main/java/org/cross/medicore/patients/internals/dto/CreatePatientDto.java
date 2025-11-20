package org.cross.medicore.patients.internals.dto;

import java.time.LocalDate;

public record CreatePatientDto(
        String firstName,
        String lastName,
        String middleName,
        String phoneNumber,
        String email,
        LocalDate birthDate
) {
}
