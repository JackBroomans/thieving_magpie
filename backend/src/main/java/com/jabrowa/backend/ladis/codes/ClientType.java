package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.ladis.entities.LadisCode;
import com.jabrowa.backend.utilities.EnumUtilities;
import lombok.Getter;

@Getter
public enum ClientType {
    GEBRUIKER((short) 1, "CT-0001", "gebruiker (ex-)", true, false),
    RELATIE((short) 2, "CT-0002", "naaste van (ex-)gebruiker", true, false),
    NIET_VERSLAAFD((short) 8, "CT-0008", "niet verslaafd", true, false),
    ONBEKEND((short) 9, "CT-9999", "onbekend", true, false);

    private final short number;
    private final String code;
    private final String display;
    private final boolean isActive;
    private final boolean isDefault;

    ClientType(short number, String code, String display, boolean isActive, boolean isDefault) {
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
        return new LadisCode(this.getClass().getSimpleName(), this.getNumber(), this.code, this.getDisplay(),
                this.isActive(), this.isDefault());
    }

    /**
     * <strong>SelectDefault</strong>()<br><br>
     * Selects the default marked ClientType constant.
     * @return The default marked ClientType constant.
     * If there's no constant is marked as default, <i>null</i> is returned, and when several constants are marked
     * as default, the first marked constant is returned.
     * @throws RuntimeException When the generic selection of the default marked constant fails due to the missing
     *                          isDefault() argument.
     */
    public ClientType selectDefault() throws RuntimeException {
        return EnumUtilities.selectDefault(ClientType.class);
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
