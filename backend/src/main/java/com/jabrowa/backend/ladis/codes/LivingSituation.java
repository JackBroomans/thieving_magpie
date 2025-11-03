package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.ladis.entities.LadisCode;
import lombok.Getter;

@Getter

public enum LivingSituation {
    ALLEENSTAAND(10, "alleenstaand", true, false),
    ALLEEN_MET_KIND(20, "met kind(eren) zonder partner", true, false),
    PARTNER_ZONDER_KIND(30, "met partner zonder kinderen", true, false),
    PARTNER_MET_KIND(40, "met partner en kind(eren)", true, false),
    KIND_EENOUDER(50, " kind in eenouder gezin", true, true),
    KIND_MEEROUDER(60, "kind in meeroudergezin", true, true),
    KIND_PLEEG(65, "kind in pleeggezin / gastgezin", true, true),
    ANDERS(97, "anders", true, true),
    ONBEKEND(99, "onbekend", true, true);

    private final int number;
    private final String display;
    private final boolean isActive;
    private final boolean isDefault;

    LivingSituation(int number, String display, boolean isActive, boolean isDefault) {
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
