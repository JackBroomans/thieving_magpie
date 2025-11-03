package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.ladis.entities.LadisCode;
import lombok.Getter;

@Getter

public enum ClientType {
    GEBRUIKER(1, "gebruiker (ex-)", true, false),
    RELATIE(2, "naaste van (ex-)gebruiker", true, false),
    NIET_VERSLAAFD(8, "niet verslaafd", true, false),
    ONBEKEND(9, "onbekend", true, false);

    private final int number;
    private final String display;
    private final boolean isActive;
    private final boolean isDefault;

    ClientType(int number, String display, boolean isActive, boolean isDefault) {
        this.number = number;
        this.display = display;
        this.isActive = isActive;
        this.isDefault = isDefault;
    }

    /**
     * <strong>createLadisCodeFromEnum<i>()</i></strong><br><br>
     * Creates a new instances of a Ladis-code-record, containing the attribute values of the current enum constant.
     *
     * @return A Ladis-code-record created from the current enum constant.
     */
    public LadisCode createLadisCodeFromEnum() {
        return new LadisCode(this.getClass().getSimpleName(), this.getNumber(), this.getDisplay(),
                this.isActive(), this.isDefault());
    }

    /**
     * <strong>toNiceString(<i>Class, String</i>)</strong><br><br>
     * Constructs an easy readable string representation from the attributes of the current enum constant.
     *
     * @return Returns a pretty formatted string representation of the current enum constant.
     */
    public String toNiceString() {
        return "\nEnumerator: " + this.getClass().getSimpleName() + "\n" +
                "\tLadis code:      " + this.getNumber() + "\n" +
                "\tOmschrijving:    " + this.getDisplay() + "\n" +
                "\tActief:          " + (this.isActive() ? "ja" : "Nee") + "\n" +
                "\tStandaard keuze: " + (this.isDefault() ? "ja" : "Nee") + "\n";
    }
}
