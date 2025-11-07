package com.jabrowa.backend.model.enums;

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
public enum Gender implements SelectableCode {
    MALE (1, "M", "Man", true, false),
    FEMALE (2, "F", "Vrouw", true,  false),
    BIPOLAIRE(3, "B", "Bi-polair", true,  false),
    INDIFFERENT (4, "I", "Onverschillig", true,  false),
    NOT_DETERMINED(5, "N", "Niet vastgesteld", true,  false),
    NOT_SPECIFIED (6, "X", "Niet gespecificeerd", true,  true);

    private final int keyValue;
    private final String code;
    private final String display;
    private final boolean isActive;
    private final boolean isDefault;

    Gender(int keyValue, String code, String display, boolean isActive, boolean isDefault) {
        this.keyValue = keyValue;
        this.code = code;
        this.display = display;
        this.isActive = isActive;
        this.isDefault = isDefault;
    }

    /**
     * <strong>SelectDefault</strong>()<br><br>
     * Selects the default marked Gender constant.
     * @return The default marked Gender constant. When there's no constant is marked as default, <i>null</i> is
     * returned, and when several constants are marked as default, the ordinal first is returned.
     * @throws RuntimeException When the generic selection of the default marked constant fails due to the missing
     *                          isDefault() argument.
     */
    public Gender selectDefault() throws RuntimeException {
        return EnumUtilities.selectDefault(Gender.class);
    }

    /**
     * <strong>toPrettyString(<i></i>)</strong><br><br>
     *  Assembles the gender's object attributes from the current instance, and transfers them into
     *  an easy readable format.
     */
    public String toNiceString() {
        return "\nEnumerator: " + this.getClass().getSimpleName() + "\n" +
                "\tIdentificatie:   " + this.getKeyValue() + "\n" +
                "\tNaam:            " + this.name() + "\n" +
                "\tCode:            " + this.getCode() + "\n" +
                "\tOmschrijving:    " + (this.getDisplay() == null ? "" : this.getDisplay()) + "\n" +
                "\tActief:          " + (this.isActive() ?  "Ja" : "Nee") + "\n" +
                "\tStandaard keuze: " + (this.isDefault() ? "Ja" : "Nee") + "\n";
    }
}
