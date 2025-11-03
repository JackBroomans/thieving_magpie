package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.ladis.entities.LadisCode;
import lombok.Getter;

@Getter

public enum Education {
    KLEUTER(10, "Onderwijs aan kleuters", true, false),
    GEEN(11, "Geen", true, false),
    BASIS(20, "Basis onderwijs", true, false),
    SPECIAAL_BASIS(21, "Speciaal Basis onderwijs", true, false),
    SPECIAAL_VOORTGEZET(31, "Voortgezet Speciaal onderwijs", true, false),
    VMBO_PRAKTIJK(32, "VMBO praktijk", true, false),
    VMBO_T_MAVO(33, "LBO:VMBO-t: MAVO", true, true),
    MBO12(42, "MBO 1 en 2", true, true),
    MBO34_HAVO_VWO(43, "HAVO: VWO: MBO 3 en 4", true, true),
    HBO_BACHELOR(52, "HBO Bachelor", true, true),
    WO_BACHCHELOR(53, " WO bachelor", true, true),
    HBO_WO_MASTER(60, "HBO master: WO Master", true, true),
    POST_DOC(70, "Post doctoraal", true, true),
    ONBEKEND(99, "Onbekend", true, true);

    private final int number;
    private final String display;
    private final boolean isActive;
    private final boolean isDefault;

    Education(int number, String display, boolean isActive, boolean isDefault) {
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


