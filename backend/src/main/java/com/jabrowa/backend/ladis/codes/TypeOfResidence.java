package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.ladis.entities.LadisCode;
import lombok.Getter;

@Getter
public enum TypeOfResidence {
    EIGEN_HUIS((short) 10, "TR-0010", "eigen huis (huur/koop)", true, false),
    PENSION_KOSTHUIS((short) 11, "TR-0011", "pension/kosthuis", true, false),
    OUDERLIJK_HUIS((short) 12, "TR-0012", "ouderlijk huis", true, false),
    KAMERS((short) 13, "TR-0013", "op kamers", true, false),
    FAMILIE_RELATIE((short) 14, "TR-0014", "familie/kennissen/relatie", true, false),
    PENITENTIAIR((short) 15, "TR-0015", "penitentiaire inrichting", true, false),
    KLINISCH_GGZ((short) 16, "TR-0016", "klinische GGZ voorziening", true, true),
    INSTITUUT_AGZ((short) 17, "TR-0017", "ander instituut (AGZ)", true, true),
    BESCHERMD((short) 18, "TR-0018", "beschermd wonen", true, true),
    TEHUIS_SOCIAAL((short) 19, "TR-0019", "tehuis/sociaal pension", true, true),
    AZC((short) 96, "TR-0096", "asielzoekerscentrum", true, true),
    STRAAT_ZWERVEND((short) 97, "TR-0097", "op straat/zwervend", true, true),
    ANDERSZINS((short) 98, "TR-0098", "anderszins", true, true),
    ONBEKEND((short) 99, "TR-0099", "onbekend", true, true);

    private final short number;
    private final String code;
    private final String display;
    private final boolean isActive;
    private final boolean isDefault;

    TypeOfResidence(short number, String code, String display, boolean isActive, boolean isDefault) {
        this.number = number;
        this.code = code;
        this.display = display;
        this.isActive = isActive;
        this.isDefault = isDefault;
    }

    /**
     * <strong>tranformToLadisCode<i>()</i></strong><br><br>
     * Creates a new instances of a Ladis code record, containing te attribute values of the current constant.
     * @return A Ladis code record for the curren constant.
     */
    public LadisCode tranformToLadisCode() {
        return new LadisCode(this.getClass().getSimpleName(), this.getNumber(), this.getCode(), this.getDisplay(),
                this.isActive(), this.isDefault());
    }

    /**
     * <strong>toNiceString(<i>Class, String</i>)</strong><br><br>
     * Constructs a nicely formatted string representation from the attributes of the current enum constant.
     * @return Returns a nicely formatted string representation of the current enum constant.
     */
    public String toNiceString() {
        return "Enumerator: " + this.getClass().getSimpleName() + "\n" +
                "\tLapis-nummer:    " + this.getNumber() + "\n" +
                "\tCode:            " + this.getCode() + "\n" +
                "\tOmschrijving:    " + this.getDisplay() + "\n" +
                "\tActief:          " + (this.isActive() ? "ja" : "Nee") + "\n" +
                "\tStandaard keuze: " + (this.isDefault() ? "ja" : "Nee") + "\n";
    }
}
