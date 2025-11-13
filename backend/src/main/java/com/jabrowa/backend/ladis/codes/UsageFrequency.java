package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.ladis.entities.LadisCode;
import lombok.Getter;

@Getter
public enum UsageFrequency {
    DAG_MEERMALIG((short) 1, "UF-0001", "meermalen daags", true, false),
    DAGELIJKS((short) 2, "UF-0002", "dagelijks", true, false),
    WEEK_MEERMALIG((short) 3, "UF-0003", "meermalen per week", true, false),
    WEKELIJKS((short) 4, "UF-004", "wekelijks", true, false),
    ONREGELMATIG((short) 5, "UF-005", "onregelmatig", true, false),
    NVT((short) 8, "UF-0008", "niet (meer) van toepassing", true, false),
    ONBEKEND((short) 9, "UF-9999", "onbekend", true, true);

    private final short number;
    private final String code;
    private final String display;
    private final boolean isActive;
    private final boolean isDefault;

    UsageFrequency(short number, String code,  String display, boolean isActive, boolean isDefault) {
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
        return new LadisCode(this.getClass().getSimpleName(), this.getNumber(), this.getCode(),  this.getDisplay(),
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
