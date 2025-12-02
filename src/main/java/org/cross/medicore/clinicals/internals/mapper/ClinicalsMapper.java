package org.cross.medicore.clinicals.internals.mapper;

import org.cross.medicore.clinicals.api.dto.AddMedicationDto;
import org.cross.medicore.clinicals.api.dto.EncounterDetails;
import org.cross.medicore.clinicals.api.dto.UpdateVitalsDto;
import org.cross.medicore.clinicals.internals.entity.Encounter;
import org.cross.medicore.clinicals.internals.entity.Medication;
import org.cross.medicore.clinicals.internals.entity.Prescription;
import org.cross.medicore.clinicals.internals.entity.Vitals;

import java.util.List;

public class ClinicalsMapper {

    /**
     * Maps Encounter entity to EncounterDetails DTO
     */
    public static EncounterDetails toEncounterDetails(Encounter encounter) {
        if (encounter == null) {
            return null;
        }

        return new EncounterDetails(
                encounter.getEncounterId(),
                encounter.getAppointmentId(),
                encounter.getEncounterStatus(),
                toVitalsDto(encounter.getVitals()),
                toPrescriptionDto(encounter.getPrescription())
        );
    }

    /**
     * Maps Vitals entity to VitalsDto
     */
    public static EncounterDetails.VitalsDto toVitalsDto(Vitals vitals) {
        if (vitals == null) {
            return null;
        }

        return new EncounterDetails.VitalsDto(
                vitals.getVitalsId(),
                vitals.getBodyTemperatureCelsius() != null && vitals.getBodyTemperatureCelsius() != -100000D ? vitals.getBodyTemperatureCelsius() : null,
                vitals.getPulseRateBpm() != null && vitals.getPulseRateBpm() != -1 ? vitals.getPulseRateBpm() : null,
                vitals.getRespiratoryRateBrPm() != null && vitals.getRespiratoryRateBrPm() != -1 ? vitals.getRespiratoryRateBrPm() : null,
                vitals.getBloodPressureMmhg() != null && vitals.getBloodPressureMmhg() != -1D ? vitals.getBloodPressureMmhg() : null,
                vitals.getOxygenSaturationPercent() != null && vitals.getOxygenSaturationPercent() != -1D ? vitals.getOxygenSaturationPercent() : null,
                vitals.getHeightMtr() != null && vitals.getHeightMtr() != -1D ? vitals.getHeightMtr() : null,
                vitals.getWeightKg() != null && vitals.getWeightKg() != -1D ? vitals.getWeightKg() : null
        );
    }

    /**
     * Maps Prescription entity to PrescriptionDto
     */
    public static EncounterDetails.PrescriptionDto toPrescriptionDto(Prescription prescription) {
        if (prescription == null) {
            return null;
        }

        List<EncounterDetails.MedicationDto> medicationDtos = prescription.getMedications().stream()
                .map(ClinicalsMapper::toMedicationDto)
                .toList();

        return new EncounterDetails.PrescriptionDto(
                prescription.getId(),
                medicationDtos
        );
    }

    /**
     * Maps Medication entity to MedicationDto
     */
    public static EncounterDetails.MedicationDto toMedicationDto(Medication medication) {
        if (medication == null) {
            return null;
        }

        return new EncounterDetails.MedicationDto(
                medication.getMedicationId(),
                medication.getName(),
                medication.getDosage(),
                medication.getPattern(),
                (long) medication.getQuantity(),
                medication.getGuidelines()
        );
    }

    /**
     * Updates vitals entity with values from UpdateVitalsDto
     * Only non-null fields are updated
     */
    public static void updateVitals(Vitals vitals, UpdateVitalsDto updateVitalsDto) {
        if (vitals == null || updateVitalsDto == null) {
            return;
        }

        if (updateVitalsDto.bodyTemperatureCelsius() != null) {
            vitals.setBodyTemperatureCelsius(updateVitalsDto.bodyTemperatureCelsius());
        }

        if (updateVitalsDto.pulseRateBpm() != null) {
            vitals.setPulseRateBpm(updateVitalsDto.pulseRateBpm());
        }

        if (updateVitalsDto.respiratoryRateBrPm() != null) {
            vitals.setRespiratoryRateBrPm(updateVitalsDto.respiratoryRateBrPm());
        }

        if (updateVitalsDto.bloodPressureMmhg() != null) {
            vitals.setBloodPressureMmhg(updateVitalsDto.bloodPressureMmhg());
        }

        if (updateVitalsDto.oxygenSaturationPercent() != null) {
            vitals.setOxygenSaturationPercent(updateVitalsDto.oxygenSaturationPercent());
        }

        if (updateVitalsDto.heightMtr() != null) {
            vitals.setHeightMtr(updateVitalsDto.heightMtr());
        }

        if (updateVitalsDto.weightKg() != null) {
            vitals.setWeightKg(updateVitalsDto.weightKg());
        }
    }

    /**
     * Creates a Medication entity from AddMedicationDto
     */
    public static Medication toMedication(AddMedicationDto addMedicationDto, Prescription prescription) {
        if (addMedicationDto == null) {
            return null;
        }

        return Medication.builder()
                .prescription(prescription)
                .name(addMedicationDto.name())
                .dosage(addMedicationDto.dosage())
                .pattern(addMedicationDto.pattern())
                .quantity(addMedicationDto.quantity())
                .guidelines(addMedicationDto.guidelines())
                .build();
    }
}
