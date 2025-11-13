package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.ladis.entities.LadisCode;
import lombok.Getter;

@Getter
public enum AddictionType {
    ALCOHOL((short) 10, "AT-0010", "alcoholverslaving", true, false),
    DRUGS((short) 20, "AT-0020", "drugsverslaving", true, false),
    GOKKEN((short) 30, "AT-0030", "gokverslaving", true, false),
    GAMEN((short) 40, "AT-0040", "gameverslaving", true, false),
    BEELDSCHERM((short) 41, "AT-0041", "computer- of beeldschermverslaving (overig)", true, false),
    SEX((short) 50, "AT-0050", "sexverslaving", true, false),
    ETEN((short) 60, "AT-0060", "eetverslaving/stoornis", true, false),
    ANDERS((short) 97, "AT-0097", " kind in eenouder gezin", true, true),
    ONBEKEND((short) 99, "AT-9999", "onbekend", true, true);

    private final short number;
    private final String code;
    private final String display;
    private final boolean isActive;
    private final boolean isDefault;

    AddictionType(short number, String code, String display, boolean isActive, boolean isDefault) {
        this.number = number;
        this.code = code;
        this.display = display;
        this.isActive = isActive;
        this.isDefault = isDefault;
    }

    /**
     * <strong>createLadisCodeFromEnum<i>()</i></strong><br><br>
     * Creates a new instances of a Ladis-code-record, containing the attribute values of the current enum constant.
     * @return A Ladis-code-record created from the current enum constant.
     */
    public LadisCode createLadisCodeFromEnum() {
        return new LadisCode(this.getClass().getSimpleName(), this.getNumber(), this.getCode(), this.getDisplay(),
                this.isActive(), this.isDefault());
    }

    /**
     * <strong>toNiceString(<i>Class, String</i>)</strong><br><br>
     * Constructs an easy readable string representation from the attributes of the current enum constant.
     * @return Returns a pretty formatted string representation of the current enum constant.
     */
    public String toNiceString() {
        return "\nEnumerator: " + this.getClass().getSimpleName() + "\n" +
                "\tLapis-nummer:    " + this.getNumber() + "\n" +
                "\tCode:            " + this.getCode() + "\n" +
                "\tOmschrijving:    " + this.getDisplay() + "\n" +
                "\tActief:          " + (this.isActive() ? "ja" : "Nee") + "\n" +
                "\tStandaard keuze: " + (this.isDefault() ? "ja" : "Nee") + "\n";
    }
}

