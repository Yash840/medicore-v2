package org.cross.medicore.scheduling.internals.persistence;

import org.cross.medicore.scheduling.api.constants.SlotStatus;

import java.time.LocalDateTime;

/**
 * Projection for {@link org.cross.medicore.scheduling.internals.entity.Slot}
 */
public interface SlotInfo {
    long getSlotId();

    long getProviderId();

    LocalDateTime getStart();

    LocalDateTime getEnd();

    SlotStatus getSlotStatus();
}