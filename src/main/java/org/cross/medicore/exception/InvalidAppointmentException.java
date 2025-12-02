package org.cross.medicore.exception;

public class InvalidAppointmentException extends RuntimeException {
    public InvalidAppointmentException(long id) {
        super("provided appointment may be cancelled or completed (AppointmentID: " + id + " )");
    }
}
