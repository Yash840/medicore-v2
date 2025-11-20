package org.cross.medicore.shared;

public enum BloodGroup {
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-"),
    UNKNOWN("UNKNOWN");

    private final String label;

    BloodGroup(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static BloodGroup fromString(String s) {
        if (s == null) return UNKNOWN;
        String normalized = s.trim().toUpperCase().replaceAll("\\s+", "");
        return switch (normalized) {
            case "A+", "APOSITIVE" -> A_POSITIVE;
            case "A-", "ANEGATIVE" -> A_NEGATIVE;
            case "B+", "BPOSITIVE" -> B_POSITIVE;
            case "B-", "BNEGATIVE" -> B_NEGATIVE;
            case "AB+", "ABPOSITIVE" -> AB_POSITIVE;
            case "AB-", "ABNEGATIVE" -> AB_NEGATIVE;
            case "O+", "OPOSITIVE" -> O_POSITIVE;
            case "O-", "ONEGATIVE" -> O_NEGATIVE;
            default -> UNKNOWN;
        };
    }
}
