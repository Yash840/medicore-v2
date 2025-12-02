package org.cross.medicore.scheduling.api.dto;

import org.cross.medicore.shared.BloodGroup;
import org.cross.medicore.shared.Sex;

import java.time.LocalDate;

public record ProviderDetails(
        long providerId,
        String firstName,
        String middleName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDate birthDate,
        BloodGroup bloodGroup,
        Sex sex,
        ProviderAddressDetails address,
        String specialization,
        String mmcRegNumber
) {
     public record ProviderAddressDetails(
             String street,
             String building,
             String landmark,
             String flat,
             String town,
             String city,
             String state,
             String country,
             String zipCode
     ){ }
}
