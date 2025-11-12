package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.ladis.entities.LadisCode;
import com.jabrowa.backend.utilities.EnumUtilities;
import lombok.Getter;

@Getter

public enum Education {
    KLEUTER((short) 10, "KLT", "Onderwijs aan kleuters", true, false),
    GEEN((short) 11, "GN", "Geen", true, false),
    BASIS((short) 20, "BAS", "Basis onderwijs", true, false),
    SPECIAAL_BASIS((short) 21, "SPB", "Speciaal Basis onderwijs", true, false),
    SPECIAAL_VOORTGEZET((short) 31, "SPV", "Voortgezet Speciaal onderwijs", true, false),
    VMBO_PRAKTIJK((short) 32, "VMBOP", "VMBO praktijk", true, false),
    VMBO_T_MAVO((short) 33, "VMBOT", "LBO:VMBO-t: MAVO", true, false),
    MBO12((short) 42, "MBO2", "MBO 1 en 2", true, false),
    MBO34_HAVO_VWO((short) 43, "MBO4", "HAVO: VWO: MBO 3 en 4", true, false),
    HBO_BACHELOR((short) 52, "HBOB", "HBO Bachelor", true, false),
    WO_BACHCHELOR((short) 53, "WOB", "WO bachelor", true, false),
    HBO_WO_MASTER((short) 60, "MST", "HBO master: WO Master", true, false),
    POST_DOC((short) 70, "POST", "Post doctoraal", true, false),
    ONBEKEND((short) 99, "ONB", "Onbekend", true, true);

    private final Short number;
    private final String code;
    private final String display;
    private final boolean isActive;
    private final boolean isDefault;

    Education(Short number, String code, String display, boolean isActive, boolean isDefault) {
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

    /**
     * <strong>SelectDefault</strong>()<br><br>
     * Selects the default marked Education constant.
     * @return The default marked Education constant.
     * If there's no constant is marked as default, <i>null</i> is returned, and when several constants are marked
     * as default, the first marked constant is returned.
     * @throws RuntimeException When the generic selection of the default marked constant fails due to the missing
     *                          isDefault() argument.
     */
    public Education selectDefault() throws RuntimeException {
        return EnumUtilities.selectDefault(Education.class);
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


