package org.cross.medicore.scheduling.internals.persistence;

import org.cross.medicore.scheduling.internals.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}