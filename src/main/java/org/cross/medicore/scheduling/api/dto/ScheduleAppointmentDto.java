package org.cross.medicore.scheduling.api.dto;

import org.cross.medicore.scheduling.api.constants.AppointmentType;

/**
 * DTO responsible for transferring data to schedule new appointment
 */
public record ScheduleAppointmentDto(
        long patientId,
        long providerId,
        long slotId,
        AppointmentType appointmentType
) {
}
