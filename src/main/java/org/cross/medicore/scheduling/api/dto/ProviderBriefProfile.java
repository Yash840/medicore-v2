package org.cross.medicore.scheduling.api.dto;

public record ProviderBriefProfile(
        long providerId,
        long userId,
        String firstName,
        String middleName,
        String lastName,
        String email,
        String phoneNumber,
        String specialization,
        String mmcRegNumber
) {
}
