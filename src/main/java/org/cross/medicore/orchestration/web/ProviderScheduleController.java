package org.cross.medicore.orchestration.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.cross.medicore.orchestration.orchestrator.ProviderScheduleOrchestrator;
import org.cross.medicore.scheduling.api.dto.CreateProviderScheduleDto;
import org.cross.medicore.scheduling.api.dto.ProviderScheduleDetailsDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/services/scheduling/provider-schedules")
public class ProviderScheduleController {
    private final ProviderScheduleOrchestrator providerScheduleOrchestrator;

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ApiResponseV1<ProviderScheduleDetailsDto>>
    getProviderScheduleById(
            @PathVariable long scheduleId,
            @NonNull HttpServletRequest request){
        ProviderScheduleDetailsDto scheduleDetails = providerScheduleOrchestrator.getProviderScheduleById(scheduleId);

        ApiResponseV1<ProviderScheduleDetailsDto> apiResponse = ApiResponseV1.ok(scheduleDetails, request.getRequestURI());

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<ApiResponseV1<ProviderScheduleDetailsDto>>
    getProviderScheduleByProviderAndDate(
            @PathVariable long providerId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @NonNull HttpServletRequest request){
        ProviderScheduleDetailsDto scheduleDetails = providerScheduleOrchestrator.getProviderScheduleForProviderAtDay(providerId, date);

        ApiResponseV1<ProviderScheduleDetailsDto> apiResponse = ApiResponseV1.ok(scheduleDetails, request.getRequestURI());

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping()
    public ResponseEntity<ApiResponseV1<ProviderScheduleDetailsDto>>
    createProviderSchedule(
            @RequestBody CreateProviderScheduleDto dto,
            @NonNull HttpServletRequest request){
        ProviderScheduleDetailsDto createdSchedule = providerScheduleOrchestrator.createProviderSchedule(dto);

        ApiResponseV1<ProviderScheduleDetailsDto> apiResponse = ApiResponseV1.created(createdSchedule, request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(apiResponse);
    }
}
