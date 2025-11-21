package org.cross.medicore.patients.internals.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.cross.medicore.patients.internals.entity.Patient;
import org.springframework.stereotype.Repository;

@Repository
public class PatientManager {
    @PersistenceContext
    EntityManager em;

    public Patient getPatientRef(long id){
        return em.getReference(Patient.class, id);
    }
}
