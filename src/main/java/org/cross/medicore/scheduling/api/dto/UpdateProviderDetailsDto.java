package org.cross.medicore.scheduling.api.dto;

import org.cross.medicore.shared.BloodGroup;
import org.cross.medicore.shared.Sex;

import java.time.LocalDate;

public record UpdateProviderDetailsDto(
        String firstName,
        String middleName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDate birthDate,
        BloodGroup bloodGroup,
        Sex sex,
        ProviderDetails.ProviderAddressDetails address,
        String specialization,
        String mmcRegNumber
) {
}
