package org.cross.medicore.scheduling.internals.persistence;

import org.cross.medicore.scheduling.internals.entity.ProviderSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ProviderScheduleRepository extends JpaRepository<ProviderSchedule, Long> {
     Optional<ProviderSchedule> findByProviderIdAndScheduleDate(long providerId, LocalDate date);
}