package org.cross.medicore.scheduling.internals.service;

import org.cross.medicore.scheduling.api.dto.SlotDetails;
import org.cross.medicore.scheduling.internals.entity.Slot;

import java.time.LocalDateTime;
import java.util.List;

public interface SlotService {
    Slot getSlotById(long slotId);

    void addSlots(List<Slot> slots);

    List<SlotDetails> getAvailableSlotsByProviderAndTime(long providerId, LocalDateTime requiredTime);
}