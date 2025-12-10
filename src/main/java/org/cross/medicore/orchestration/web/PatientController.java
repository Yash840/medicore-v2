package org.cross.medicore.orchestration.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.cross.medicore.orchestration.orchestrator.PatientsOrchestrator;
import org.cross.medicore.orchestration.web.dto.CreatePatientRequest;
import org.cross.medicore.patients.api.dto.CreatePatientDto;
import org.cross.medicore.patients.api.dto.PatientBriefProfile;
import org.cross.medicore.patients.api.dto.PatientPublicDto;
import org.cross.medicore.patients.api.dto.UpdatePatientInfoDto;
import org.cross.medicore.security.api.dto.UserCredsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/services/patients")
public class PatientController {
    private final PatientsOrchestrator patientsOrchestrator;

    @GetMapping("/{patientId}")
    public ResponseEntity<ApiResponseV1<PatientPublicDto>>
    getPatientDetails(
                  @PathVariable long patientId,
                  @NonNull HttpServletRequest request){
        PatientPublicDto patientDetails = patientsOrchestrator.getPatientDetails(patientId);

        ApiResponseV1<PatientPublicDto> apiResponse = ApiResponseV1.ok(patientDetails, request.getRequestURI());

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping()
    public ResponseEntity<ApiResponseV1<PatientBriefProfile>>
        createPatient(@RequestBody CreatePatientRequest requestBody,
                      @NonNull HttpServletRequest request){
        CreatePatientDto dto = requestBody.createPatientDto();
        UserCredsDto creds = requestBody.creds();

        PatientBriefProfile createdPatient = patientsOrchestrator.createPatient(dto, creds.username(), creds.password());

        ApiResponseV1<PatientBriefProfile> apiResponse = ApiResponseV1.created(createdPatient, request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(apiResponse);
    }

    @PatchMapping("/{patientId}")
    public ResponseEntity<ApiResponseV1<PatientPublicDto>>
    updatePatientDetails(
            @PathVariable long patientId,
            @RequestBody UpdatePatientInfoDto dto,
            @NonNull HttpServletRequest request){
        PatientPublicDto patientDetails = patientsOrchestrator.updatePatientDetails(patientId, dto);

        ApiResponseV1<PatientPublicDto> apiResponse = ApiResponseV1.ok(patientDetails, request.getRequestURI());

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<ApiResponseV1<PatientPublicDto>>
    deletePatient(
            @PathVariable long patientId,
            @NonNull HttpServletRequest request){
        PatientPublicDto deletedPatientDetails = patientsOrchestrator.deletePatient(patientId);

        ApiResponseV1<PatientPublicDto> apiResponse = ApiResponseV1.ok(deletedPatientDetails, request.getRequestURI());

        return ResponseEntity.ok(apiResponse);
    }

    public static class ProviderScheduleController {
    }
}
