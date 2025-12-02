package org.cross.medicore.clinicals.api.dto;

import org.cross.medicore.clinicals.api.constants.EncounterStatus;

import java.util.List;

public record EncounterDetails(
        long encounterId,
        long associatedAppointmentId,
        EncounterStatus encounterStatus,
        VitalsDto vitals,
        PrescriptionDto prescription
) {
    public record VitalsDto(
            Long vitalsId,
            Double bodyTemperatureCelsius,
            Integer pulseRateBpm,
            Integer respiratoryRateBrPm,
            Double bloodPressureMmhg,
            Double oxygenSaturationPercent,
            Double heightMtr,
            Double weightKg
    ) {}

    public record PrescriptionDto(
            Long prescriptionId,
            List<MedicationDto> medications
    ) {}

    public record MedicationDto(
            Long medicationId,
            String name,
            String dosage,
            String pattern,
            Long quantity,
            String guidelines
    ){}
}
