package org.cross.medicore.clinicals.internals.persistence;

import org.cross.medicore.clinicals.internals.entity.Vitals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VitalsRepository extends JpaRepository<Vitals, Long> {
}
