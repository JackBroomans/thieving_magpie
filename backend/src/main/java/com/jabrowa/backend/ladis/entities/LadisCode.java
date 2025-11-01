package com.jabrowa.backend.ladis.entities;

/**
 * <strong>LadisCode</strong> - Record<br><br>
 * The Ladis codes are immutable, so they are implemented as records because they are implicit final. Because they
 * can't be an (JPA compliant) entity, they can't be mapped to database tables and/or columns. However, it's a perfect
 * fit for a DTO-projection.
 *
 * @param typeName  The name of the specific type of the Ladis code (SourceOfIncome, ClientType, AddictionDuration etc.)
 * @param number
 * @param display
 * @param isActive
 * @param isDefault
 */
public record LadisCode(
        String typeName,
        int number,
        String display,
        boolean isActive,
        boolean isDefault) {


    /**
     * <strong>toNiceString(<i>Class, String</i>)</strong><br><br>
     * Constructs a nicely formatted string representation from the attributes of Ladis code instance.
     * @return Returns a nicely formatted string representation of the current enum constant.
     */
    public String toNiceString() {
        return "Ladis code:\n" +
                "\tLadis code:      " + this.number() + "\n" +
                "\tOmschrijving:    " + this.display() + "\n" +
                "\tActief:          " + (this.isActive() ? "ja" : "Nee") + "\n" +
                "\tStandaard keuze: " + (this.isDefault() ? "ja" : "Nee") + "\n";
    }
}
