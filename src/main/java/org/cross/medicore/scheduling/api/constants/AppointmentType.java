package org.cross.medicore.scheduling.api.constants;

import java.util.Objects;

public enum AppointmentType {
    CONSULTATION("CONSULTATION"),
    FOLLOW_UP("FOLLOW_UP"),
    ROUTINE("ROUTINE"),
    UNKNOWN("UNKNOWN");

    private final String label;

    AppointmentType(String label){
        this.label = label;
    }

    @Override
    public String toString(){
        return this.label;
    }

    public static AppointmentType fromString(String str){
        if(Objects.isNull(str)) return UNKNOWN;
        String normalized = str.trim().toUpperCase().replaceAll("\\s+", "");
        return switch (normalized){
            case "CONSULTATION" -> CONSULTATION;
            case "FOLLOWUP", "FOLLOW_UP" -> FOLLOW_UP;
            case "ROUTINE" -> ROUTINE;
            default -> UNKNOWN;
        };
    }
}
