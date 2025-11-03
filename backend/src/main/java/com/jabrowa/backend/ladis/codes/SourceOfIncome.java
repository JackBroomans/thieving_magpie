package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.ladis.entities.LadisCode;
import lombok.Getter;

@Getter

public enum SourceOfIncome {
    LOON(1, "loon zelfstandig eigen bedrijf", true, false),
    UITKERING(2, "uitkering", true, false),
    PENSIOEN(3, "AOW/pensioen", true, false),
    GEEN(4, "geen eigen inkomen", true, false),
    STUDIEFINACIERING(5, "studiefinanciering", true, false),
    ANDERS(8, "anders", true, false),
    ONBEKEND(9, "onbekend", true, true);

    private final int number;
    private final String display;
    private final boolean isActive;
    private final boolean isDefault;

    SourceOfIncome(int number, String display, boolean isActive, boolean isDefault) {
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
