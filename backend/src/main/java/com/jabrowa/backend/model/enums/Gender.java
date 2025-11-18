package com.jabrowa.backend.model.enums;

import com.jabrowa.backend.model.interfaces.HasKeyValue;
import com.jabrowa.backend.model.interfaces.SelectableCode;
import com.jabrowa.backend.utilities.EnumUtilities;
import lombok.Getter;

/**
 * <strong>PreferredNameUses</strong> - enumerator<br><br>
 * Enumerator which contains the options to use the given- and the family names in a particular way, according to the
 * meet the legal preferences offered. The following options are available:
 * <ul>
 *     <li><strong>MALE</strong></li>
 *     <li><strong>FEMALE</strong></li>
 *     <li><strong>BI_POLAIRE</strong></li>
 *     <li><strong>INDIFFERENT</strong></li>
 *     <li><strong>NOT_DETERMINED</strong></li>
 *     <li><strong>NOT_SPECIFIED</strong></li>
 * </ul>
 * The enumerator contains the standard (mandatory) attributes to implement the required methods as prescribed in the
 * SelectableCode interface.
 */
@Getter
public enum Gender implements SelectableCode<Short>, HasKeyValue {
    MALE ((short) 1, "M", "Man", true, false),
    FEMALE ((short) 2, "F", "Vrouw", true,  false),
    BIPOLAIRE((short) 3, "B", "Bi-polair", true,  false),
    INDIFFERENT ((short) 4, "I", "niet aangegeven", true,  false),
    NOT_DETERMINED((short) 5, "N", "Niet vastgesteld", true,  false),
    NOT_SPECIFIED ((short) 6, "X", "Niet gespecificeerd", true,  true);

    private final Short number;
    private final String code;
    private final String display;
    private final boolean isActive;
    private final boolean isDefault;

    Gender(short number, String code, String display, boolean isActive, boolean isDefault) {
        this.number = number;
        this.code = code;
        this.display = display;
        this.isActive = isActive;
        this.isDefault = isDefault;
    }

    @Override
    public short getNumber() {
        return number;
    }

    /**
     * <strong>SelectDefault</strong>()<br><br>
     * Selects the default marked Gender constant.
     * @return The default marked Gender constant. When there's no constant is marked as default, <i>null</i> is
     * returned, and when several constants are marked as default, the first (ordinal) constant is returned.
     */
    public Gender selectDefault() {
        return EnumUtilities.selectDefault(Gender.class);
    }

    /**
     * <strong>toNiceString(<i></i>)</strong><br><br>
     *  Assembles the gender's object attributes, and transfers them into an easy readable presentation.
     */
    public String toNiceString() {
        return "\nEnumerator: " + this.getClass().getSimpleName() + "\n" +
                "\tIdentificatie:   " + this.getNumber() + "\n" +
                "\tNaam:            " + this.name() + "\n" +
                "\tCode:            " + this.getCode() + "\n" +
                "\tOmschrijving:    " + (this.getDisplay() == null ? "" : this.getDisplay()) + "\n" +
                "\tActief:          " + (this.isActive() ?  "Ja" : "Nee") + "\n" +
                "\tStandaard keuze: " + (this.isDefault() ? "Ja" : "Nee") + "\n";
    }
}
