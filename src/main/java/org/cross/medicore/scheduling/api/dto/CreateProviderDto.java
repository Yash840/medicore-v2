package org.cross.medicore.scheduling.api.dto;

public record CreateProviderDto(
        String firstName,
        String middleName,
        String lastName,
        String email,
        String specialization,
        String mmcRegNumber
) {
}
