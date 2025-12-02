package org.cross.medicore.scheduling.api;

import org.cross.medicore.scheduling.api.dto.AppointmentDetailsDto;
import org.cross.medicore.scheduling.api.dto.ScheduleAppointmentDto;

import java.time.LocalDate;
import java.util.List;

public interface SchedulingApi {
    void consumeAppointment(long appointmentId);

    AppointmentDetailsDto scheduleAppointment(ScheduleAppointmentDto dto);

    AppointmentDetailsDto getAppointmentDetails(long appointmentId);

    List<AppointmentDetailsDto> getAllAppointmentDetailsForDay(LocalDate date);

    List<AppointmentDetailsDto> getAllAppointmentDetailsForPatient(long patientId);

    List<AppointmentDetailsDto> getAllAppointmentDetailsForProvider(long providerId);

    List<AppointmentDetailsDto> getAllAppointmentDetailsForPatient(long patientId, LocalDate date);

    List<AppointmentDetailsDto> getAllAppointmentDetailsForProvider(long providerId, LocalDate date);
}