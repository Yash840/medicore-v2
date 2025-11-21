package org.cross.medicore.patients.internals.entity;

public enum PatientStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String label;

    PatientStatus(String label){
        this.label = label;
    }

    @Override
    public String toString(){
        return this.label;
    }

    public static PatientStatus fromString(String str){
        if(str == null) return ACTIVE;
        String normalized = str.trim().toUpperCase().replaceAll("\\s+", "");
        return switch (normalized){
            case "A", "ACTIVE" -> ACTIVE;
            case "I", "INACTIVE" -> INACTIVE;
            default -> ACTIVE;
        };
    }
}
