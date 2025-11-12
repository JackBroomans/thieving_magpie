package com.jabrowa.backend.ladis.entities;

/**
 * <strong>LadisCode</strong> - Record<br><br>
 * The Ladis codes are immutable, so they are implemented as records because they are implicit final. Because they
 * can't be an (JPA compliant) entity, they can't be mapped to database tables and/or columns. However, it's a perfect
 * fit for a DTO-projection.
 *
 * @param typeName  The name of the specific type of the Ladis code (SourceOfIncome, ClientType, AddictionDuration etc.)
 * @param number The numeric code identifier.
 * @param code The unique code. This code is only use within the system and doesn't belong to the 'Ladis code system'.
 * @param display The short description of the Ladis-code.
 * @param isActive Indicates if the Ladis-code is active. (Codes aren't removed to ensure database integrity)
 * @param isDefault Indicates if the particular Ladis-code is the default constant of the enumerated constants.
 */
public record
    LadisCode(String typeName, Short number, String code, String display, boolean isActive, boolean isDefault) {

    public LadisCode {
        if ((typeName == null || typeName.isBlank()) ||
                (code == null || code.isBlank()) ||
                (display == null || display.isBlank()) ||
                (number <= 0)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * <strong>toNiceString(<i>Class, String</i>)</strong><br><br>
     * Constructs a nicely formatted string representation from the attributes of Ladis code instance.
     * @return Returns a nicely formatted string representation of the current enum constant.
     */
    public String toNiceString() {
        return "Ladis code -> " + this.typeName() + "\n" +
                "\tLapis-nummer:    " + this.number() + "\n" +
                "\tCode:            " + this.code() + "\n" +
                "\tOmschrijving:    " + this.display() + "\n" +
                "\tActief:          " + (this.isActive() ? "ja" : "Nee") + "\n" +
                "\tStandaard keuze: " + (this.isDefault() ? "ja" : "Nee") + "\n";
    }
}
