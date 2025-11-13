package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.ladis.entities.LadisCode;
import com.jabrowa.backend.utilities.EnumUtilities;
import lombok.Getter;

@Getter
public enum LivingSituation {
    ALLEENSTAAND((short) 10, "LV-0010", "alleenstaand", true, false),
    ALLEEN_MET_KIND((short) 20, "LV-0020", "met kind(eren) zonder partner", true, false),
    PARTNER_ZONDER_KIND((short) 30, "LV-0030", "met partner zonder kinderen", true, false),
    PARTNER_MET_KIND((short) 40, "LV-0040", "met partner en kind(eren)", true, false),
    KIND_EENOUDER((short) 50, "LV-0050", "kind in eenouder gezin", true, true),
    KIND_MEEROUDER((short) 60, "LV-0060", "kind in meeroudergezin", true, true),
    KIND_PLEEG((short) 65, "LV-0065", "kind in pleeggezin / gastgezin", true, true),
    ANDERS((short) 97, "LV-0097", "anders", true, true),
    ONBEKEND((short) 99,"LV-9999", "onbekend", true, true);

    private final short number;
    private final String code;
    private final String display;
    private final boolean isActive;
    private final boolean isDefault;

    LivingSituation(short number, String code, String display, boolean isActive, boolean isDefault) {
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
     * <strong>SelectDefault</strong>()<br><br>
     * Selects the default marked LivingSituation constant.
     * @return The default marked LivingSituation constant.
     * If there's no constant is marked as default, <i>null</i> is returned, and when several constants are marked
     * as default, the first marked constant is returned.
     * @throws RuntimeException When the generic selection of the default marked constant fails due to the missing
     *                          isDefault() argument.
     */
    public LivingSituation selectDefault() throws RuntimeException {
        return EnumUtilities.selectDefault(LivingSituation.class);
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
