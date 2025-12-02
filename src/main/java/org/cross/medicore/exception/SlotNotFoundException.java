package org.cross.medicore.exception;

public class SlotNotFoundException extends RuntimeException {
    public SlotNotFoundException(long id) {
        super("slot not found with id: " + id);
    }
}
