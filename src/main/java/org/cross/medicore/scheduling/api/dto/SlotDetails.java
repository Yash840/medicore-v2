package org.cross.medicore.scheduling.api.dto;

import org.cross.medicore.scheduling.api.constants.SlotStatus;

import java.time.LocalDateTime;

public record SlotDetails(
      long slotId,
      long providerScheduleId,
      LocalDateTime start,
      LocalDateTime end,
      SlotStatus slotStatus,
      long providerId,
      AppointmentDetailsDto appointment
) {
}
