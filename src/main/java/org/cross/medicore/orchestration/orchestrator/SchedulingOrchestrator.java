package org.cross.medicore.orchestration.orchestrator;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.auditing.annotation.Auditable;
import org.cross.medicore.patients.api.PatientApi;
import org.cross.medicore.scheduling.api.ProviderApi;
import org.cross.medicore.scheduling.api.SchedulingApi;
import org.cross.medicore.scheduling.api.SlotApi;
import org.cross.medicore.scheduling.api.dto.AppointmentDetailsDto;
import org.cross.medicore.scheduling.api.dto.ScheduleAppointmentDto;
import org.cross.medicore.scheduling.api.dto.SlotInfo;
import org.cross.medicore.shared.Action;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulingOrchestrator {
    private final SchedulingApi schedulingApi;
    private final PatientApi patientApi;
    private final ProviderApi providerApi;
    private final SlotApi slotApi;

    @Auditable(action = Action.READ_SLOT_DETAILS, message = "Orchestrated fetch available slots for providerId: ?1 near time: ?2")
    public List<SlotInfo> getAvailableSlots(long providerId, LocalDateTime time){
        return slotApi.getAvailableSlotsByProviderAndTime(providerId, time);
    }

    @Auditable(action = Action.SCHEDULE_APPOINTMENT, message = "Orchestrated schedule appointment for patientId: ?1.patientId with providerId: ?1.providerId")
    public AppointmentDetailsDto scheduleAppointment(ScheduleAppointmentDto dto){
        return schedulingApi.scheduleAppointment(dto);
    }

    @Auditable(action = Action.READ_APPOINTMENT_DETAILS, message = "Orchestrated fetch appointment details for appointmentId: ?1")
    public AppointmentDetailsDto getAppointmentDetails(long id){
        return schedulingApi.getAppointmentDetails(id);
    }

    @Auditable(action = Action.READ_APPOINTMENT_DETAILS, message = "Orchestrated fetch appointment details for patientId: ?1")
    public List<AppointmentDetailsDto> getAppointmentDetailsForPatient(long patientId){
        return schedulingApi.getAllAppointmentDetailsForPatient(patientId);
    }
}
