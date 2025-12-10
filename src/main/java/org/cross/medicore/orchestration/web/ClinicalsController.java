package org.cross.medicore.orchestration.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.cross.medicore.clinicals.api.dto.AddMedicationDto;
import org.cross.medicore.clinicals.api.dto.EncounterDetails;
import org.cross.medicore.clinicals.api.dto.UpdateVitalsDto;
import org.cross.medicore.orchestration.orchestrator.ClinicalsOrchestrator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/services/clinicals")
public class ClinicalsController {
    private final ClinicalsOrchestrator clinicalsOrchestrator;

    @PostMapping("/encounters/appointment/{appointmentId}")
    public ResponseEntity<ApiResponseV1<Long>>
    beginEncounter(
            @PathVariable long appointmentId,
            @NonNull HttpServletRequest request) {
        long encounterId = clinicalsOrchestrator.beginEncounter(appointmentId);

        ApiResponseV1<Long> apiResponse = ApiResponseV1.created(encounterId, request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(apiResponse);
    }

    @GetMapping("/encounters/{encounterId}")
    public ResponseEntity<ApiResponseV1<EncounterDetails>>
    getEncounterDetailsByEncounterId(
            @PathVariable long encounterId,
            @NonNull HttpServletRequest request) {
        EncounterDetails encounterDetails = clinicalsOrchestrator.getEncounterDetailsByEncounterId(encounterId);

        ApiResponseV1<EncounterDetails> apiResponse = ApiResponseV1.ok(encounterDetails, request.getRequestURI());

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/encounters/appointment/{appointmentId}")
    public ResponseEntity<ApiResponseV1<EncounterDetails>>
    getEncounterDetailsByAppointmentId(
            @PathVariable long appointmentId,
            @NonNull HttpServletRequest request) {
        EncounterDetails encounterDetails = clinicalsOrchestrator.getEncounterDetailsByAppointmentId(appointmentId);

        ApiResponseV1<EncounterDetails> apiResponse = ApiResponseV1.ok(encounterDetails, request.getRequestURI());

        return ResponseEntity.ok(apiResponse);
    }

    @PatchMapping("/encounters/{encounterId}/end")
    public ResponseEntity<ApiResponseV1<EncounterDetails>>
    endEncounter(
            @PathVariable long encounterId,
            @NonNull HttpServletRequest request) {
        EncounterDetails encounterDetails = clinicalsOrchestrator.endEncounter(encounterId);

        ApiResponseV1<EncounterDetails> apiResponse = ApiResponseV1.ok(encounterDetails, request.getRequestURI());

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/encounters/{encounterId}/medications")
    public ResponseEntity<ApiResponseV1<EncounterDetails>>
    addMedications(
            @PathVariable long encounterId,
            @RequestBody List<AddMedicationDto> medications,
            @NonNull HttpServletRequest request) {
        EncounterDetails encounterDetails = clinicalsOrchestrator.addMedications(encounterId, medications);

        ApiResponseV1<EncounterDetails> apiResponse = ApiResponseV1.ok(encounterDetails, request.getRequestURI());

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/encounters/{encounterId}/vitals")
    public ResponseEntity<ApiResponseV1<EncounterDetails>>
    addVitals(
            @PathVariable long encounterId,
            @RequestBody UpdateVitalsDto vitals,
            @NonNull HttpServletRequest request) {
        EncounterDetails encounterDetails = clinicalsOrchestrator.addVitals(encounterId, vitals);

        ApiResponseV1<EncounterDetails> apiResponse = ApiResponseV1.ok(encounterDetails, request.getRequestURI());

        return ResponseEntity.ok(apiResponse);
    }
}
