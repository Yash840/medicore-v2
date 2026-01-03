package org.cross.medicore.clinicals.api.dto;

import lombok.Builder;

@Builder
public record UpdateVitalsDto(
        Double bodyTemperatureCelsius,
        Integer pulseRateBpm,
        Integer respiratoryRateBrPm,
        Double bloodPressureMmhg,
        Double oxygenSaturationPercent,
        Double heightMtr,
        Double weightKg
) {
}
