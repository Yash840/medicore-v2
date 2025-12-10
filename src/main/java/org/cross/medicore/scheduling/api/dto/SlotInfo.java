package org.cross.medicore.scheduling.api.dto;

import org.cross.medicore.scheduling.api.constants.SlotStatus;
import org.cross.medicore.scheduling.internals.AppointmentInfo;

import java.time.LocalDateTime;

/**
 * Projection for {@link org.cross.medicore.scheduling.internals.entity.Slot}
 */
public interface SlotInfo {
    long getSlotId();

    long getProviderScheduleId();

    long getProviderId();

    LocalDateTime getStart();

    LocalDateTime getEnd();

    SlotStatus getSlotStatus();

    String getProviderFirstName();

    String getProviderLastName();

    String getProviderSpecialization();

    AppointmentInfo getAppointment();
}