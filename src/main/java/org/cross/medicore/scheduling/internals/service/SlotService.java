package org.cross.medicore.scheduling.internals.service;

import org.cross.medicore.scheduling.api.SlotApi;
import org.cross.medicore.scheduling.internals.entity.Slot;


import java.util.List;

public interface SlotService extends SlotApi {
    Slot getSlotById(long slotId);

    void addSlots(List<Slot> slots);
}