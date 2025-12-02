package org.cross.medicore.exception;

public class EncounterNotFoundException extends RuntimeException {
    public EncounterNotFoundException(long id) {
        super("Encounter not found with ID: " + id);
    }
    public EncounterNotFoundException(String message) {
        super(message);
    }
}
