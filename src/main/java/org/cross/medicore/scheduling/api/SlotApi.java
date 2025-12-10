package org.cross.medicore.scheduling.api;

import org.cross.medicore.scheduling.api.dto.SlotInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface SlotApi {
    List<SlotInfo> getAvailableSlotsByProviderAndTime(long providerId, LocalDateTime requiredTime);
}
