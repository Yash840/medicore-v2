package org.cross.medicore.exception;

public class BeginEncounterFailedException extends RuntimeException {
    public BeginEncounterFailedException(String message) {
        super("beginning new encounter failed due to: " + message);
    }
}
