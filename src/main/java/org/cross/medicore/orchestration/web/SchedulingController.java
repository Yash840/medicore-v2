package org.cross.medicore.orchestration.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.cross.medicore.orchestration.orchestrator.SchedulingOrchestrator;
import org.cross.medicore.scheduling.api.dto.AppointmentDetailsDto;
import org.cross.medicore.scheduling.api.dto.ScheduleAppointmentDto;
import org.cross.medicore.scheduling.api.dto.SlotInfo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/services/scheduling")
public class SchedulingController {
    private final SchedulingOrchestrator schedulingOrchestrator;

    @GetMapping("/slots/provider/{providerId}")
    public ResponseEntity<ApiResponseV1<List<SlotInfo>>>
    getAvailableSlots(
            @PathVariable long providerId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time,
            @NonNull HttpServletRequest request){
        List<SlotInfo> slots = schedulingOrchestrator.getAvailableSlots(providerId, time);

        ApiResponseV1<List<SlotInfo>> apiResponse = ApiResponseV1.ok(slots, request.getRequestURI());

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/appointments")
    public ResponseEntity<ApiResponseV1<AppointmentDetailsDto>>
    scheduleAppointment(
            @RequestBody ScheduleAppointmentDto dto,
            @NonNull HttpServletRequest request){
        AppointmentDetailsDto appointment = schedulingOrchestrator.scheduleAppointment(dto);

        ApiResponseV1<AppointmentDetailsDto> apiResponse = ApiResponseV1.created(appointment, request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(apiResponse);
    }

    @GetMapping("/appointments/{appointmentId}")
    public ResponseEntity<ApiResponseV1<AppointmentDetailsDto>>
    getAppointmentDetails(
            @PathVariable long appointmentId,
            @NonNull HttpServletRequest request){
        AppointmentDetailsDto appointmentDetails = schedulingOrchestrator.getAppointmentDetails(appointmentId);

        ApiResponseV1<AppointmentDetailsDto> apiResponse = ApiResponseV1.ok(appointmentDetails, request.getRequestURI());

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/appointments/patient/{patientId}")
    public ResponseEntity<ApiResponseV1<List<AppointmentDetailsDto>>>
    getAppointmentDetailsForPatient(
            @PathVariable long patientId,
            @NonNull HttpServletRequest request){
        List<AppointmentDetailsDto> appointments = schedulingOrchestrator.getAppointmentDetailsForPatient(patientId);

        ApiResponseV1<List<AppointmentDetailsDto>> apiResponse = ApiResponseV1.ok(appointments, request.getRequestURI());

        return ResponseEntity.ok(apiResponse);
    }
}
