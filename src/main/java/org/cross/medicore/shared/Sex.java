package org.cross.medicore.shared;

public enum Sex {
    MALE("Male"),
    FEMALE("Female"),
    TRANSGENDER("Transgender"),
    PREFER_NOT_TO_SAY("Unknown");

    private final String label;

    Sex(String label){
        this.label = label;
    }

    @Override
    public String toString(){
        return this.label;
    }

    public static Sex fromString(String str){
        if(str == null) return PREFER_NOT_TO_SAY;
        String normalized = str.trim().toUpperCase().replaceAll("\\s+", "");
        return switch (normalized){
            case "MALE", "M" -> MALE;
            case "FEMALE", "F" -> FEMALE;
            case "TRANSGENDER", "T" -> TRANSGENDER;
            default -> PREFER_NOT_TO_SAY;
        };
    }
}
