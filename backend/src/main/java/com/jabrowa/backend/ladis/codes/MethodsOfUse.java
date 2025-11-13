package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.ladis.entities.LadisCode;
import lombok.Getter;

@Getter
public enum MethodsOfUse {
    SPUITEN((short) 1, "MU-0001", "spuiten", true, false),
    ROKEN((short) 2, "MU-0002", "roken/basen/chinezen", true, false),
    SNUIVEN((short) 3, "MU-0003", "snuiven", true, false),
    SLIKKEN((short) 4, "MU-0004", "slikken/eten", true, false),
    DRINKEN((short) 5, "MU-0005", "drinken", true, false),
    NVT((short) 8, "MU-0008", "niet van toepassing", true, false),
    ONBEKEND((short) 9, "MU-9999", "onbekend", true, true);

    private final short number;
    private final String code;
    private final String display;
    private final boolean isActive;
    private final boolean isDefault;

    MethodsOfUse(short number, String code, String display, boolean isActive, boolean isDefault) {
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
