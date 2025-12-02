package org.cross.medicore.exception;

public class EncounterAlreadyEndedException extends RuntimeException {
    public EncounterAlreadyEndedException(String message) {
        super(message);
    }
    public EncounterAlreadyEndedException() {
        super("cannot modify ended encounter");
    }
}
