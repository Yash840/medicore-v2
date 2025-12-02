package org.cross.medicore.clinicals.api.dto;

public record AddMedicationDto(
        String name,
        String dosage,
        String pattern,
        int quantity,
        String guidelines
) {
}
