package org.cross.medicore.scheduling.api.dto;

import org.cross.medicore.scheduling.api.constants.AppointmentStatus;
import org.cross.medicore.scheduling.api.constants.AppointmentType;

import java.time.LocalDateTime;

public record AppointmentDetailsDto(
        long appointmentId,
        long patientId,
        long providerId,
        AppointmentType appointmentType,
        AppointmentStatus appointmentStatus,
        SlotDetailsDto slot
) {
    public record SlotDetailsDto(
            long slotId,
            long providerScheduleId,
            LocalDateTime start,
            LocalDateTime end
    ) {}
}
