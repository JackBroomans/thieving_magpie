package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.ladis.entities.LadisCode;
import com.jabrowa.backend.model.enums.Gender;
import com.jabrowa.backend.model.interfaces.SelectableCode;
import com.jabrowa.backend.utilities.EnumUtilities;
import lombok.Getter;

@Getter
public enum AddictionDuration implements SelectableCode<Short> {
    M1TM3((short) 1, "M13", "1 tot 3 maanden", true, false),
    M3TM6((short) 2, "M36", "3 tot 6 maanden", true, false),
    M6TM12((short) 3, "M612", "6 tot 12 maanden", true, false),
    J1TM2((short) 4, "J12", "1 tot 2 jaar", true, false),
    J2TM5((short) 5, "J25", "2 tot 5 jaar", true, false),
    J5TM10((short) 6, "J510", "5 tot 10 jaar", true, false),
    J10PLUS((short) 7, "J10P", "meer dan 10 jaar", true, false),
    ONBEKEND((short) 9, "ONB", "onbekend", true, true);

    private final Short number;
    private final String code;
    private final String display;
    private final boolean isActive;
    private final boolean isDefault;

    AddictionDuration(Short number, String code, String display, boolean isActive, boolean isDefault) {
        this.number = number;
        this.code = code;
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

    @Override
    public Short getKeyValue() {
        return 0;
    }

    /**
     * <strong>SelectDefault</strong>()<br><br>
     * Selects the default marked AddictionDuration constant.
     * @return The default marked AddictionDuration constant.
     * If there's no constant is marked as default, <i>null</i> is returned, and when several constants are marked
     * as default, the first marked constant is returned.
     * @throws RuntimeException When the generic selection of the default marked constant fails due to the missing
     *                          isDefault() argument.
     */
    public AddictionDuration selectDefault() throws RuntimeException {
        return EnumUtilities.selectDefault(AddictionDuration.class);
    }

    /**
     * <strong>toNiceString(<i>Class, String</i>)</strong><br><br>
     * Constructs an easy readable string representation from the attributes of the current enum constant.
     *
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
