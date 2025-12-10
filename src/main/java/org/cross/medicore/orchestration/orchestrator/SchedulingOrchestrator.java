package org.cross.medicore.orchestration.orchestrator;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.patients.api.PatientApi;
import org.cross.medicore.scheduling.api.ProviderApi;
import org.cross.medicore.scheduling.api.SchedulingApi;
import org.cross.medicore.scheduling.api.SlotApi;
import org.cross.medicore.scheduling.api.dto.AppointmentDetailsDto;
import org.cross.medicore.scheduling.api.dto.ScheduleAppointmentDto;
import org.cross.medicore.scheduling.api.dto.SlotInfo;
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

    public List<SlotInfo> getAvailableSlots(long providerId, LocalDateTime time){
        return slotApi.getAvailableSlotsByProviderAndTime(providerId, time);
    }

    public AppointmentDetailsDto scheduleAppointment(ScheduleAppointmentDto dto){
        return schedulingApi.scheduleAppointment(dto);
    }

    public AppointmentDetailsDto getAppointmentDetails(long id){
        return schedulingApi.getAppointmentDetails(id);
    }

    public List<AppointmentDetailsDto> getAppointmentDetailsForPatient(long patientId){
        return schedulingApi.getAllAppointmentDetailsForPatient(patientId);
    }
}
