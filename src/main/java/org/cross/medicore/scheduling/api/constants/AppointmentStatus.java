package org.cross.medicore.scheduling.api.constants;

import java.util.Objects;

public enum AppointmentStatus {
    SCHEDULED("SCHEDULED"),
    CANCELLED("CANCELLED"),
    COMPLETED("COMPLETED"),
    UNKNOWN("UNKNOWN");

    private final String label;

    AppointmentStatus(String label){
        this.label = label;
    }

    @Override
    public String toString(){
        return this.label;
    }

    public static AppointmentStatus fromString(String str){
        if(Objects.isNull(str)) return UNKNOWN;
        String normalized = str.trim().toUpperCase().replaceAll("\\s+", "");
        return switch (normalized){
            case "SCHEDULED" -> SCHEDULED;
            case "CANCELLED" -> CANCELLED;
            case "COMPLETED" -> COMPLETED;
            default -> UNKNOWN;
        };
    }
}
