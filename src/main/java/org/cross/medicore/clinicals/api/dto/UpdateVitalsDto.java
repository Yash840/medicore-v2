package org.cross.medicore.clinicals.api.dto;

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
