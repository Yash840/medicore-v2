package org.cross.medicore.scheduling.internals.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class SchedulingManager {
    @PersistenceContext
    EntityManager em;


}
