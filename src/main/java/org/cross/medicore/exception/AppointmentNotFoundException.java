package org.cross.medicore.exception;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(long id) {
        super("appointment not found with id: " + id);
    }
}
