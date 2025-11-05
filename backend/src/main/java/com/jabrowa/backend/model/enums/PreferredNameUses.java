package com.jabrowa.backend.model.enums;

import com.jabrowa.backend.model.interfaces.SelectableCode;
import com.jabrowa.backend.utilities.EnumUtilities;
import lombok.Getter;

/**
 * <strong>PreferredNameUses</strong> - enumerator<br><br>
 * Enumerator which contains the options to use the given- and the family names in a particular way, according to the
 * meet the legal preferences offered. The following options are available:
 * <ul>
 *     <li>Using the given name only. The partner's name is ignored. However when the given name is not specified,
 *     then the family name of the partner is used.</li>
 *     <li>Using the family name of the partner followed by the given name.</li>
 *     <li>Given name followed by the family name of the partner. When one of the two name components aren't specified, then it
 *     will be ignored. </li>
 *     <li>Use of the family name of the partner only.The given name is ignored. However when the family name is
 *     not specified, then the family name is used.</li>
 * </ul>
 */
@Getter
public enum PreferredNameUses implements SelectableCode {
    GIVEN_NAME_ONLY ("GIV", "Alleen geboortenaam.", true, true),
    FAMILY_NAME_AND_GIVEN_NAME ("FNG", "Naam partner gevolgd door geboortenaam.", true, false),
    GIVEN_NAME_AND_FAMILY_NAME("GNF", "Geboortenaam gevolgd door naam partner.", true, false),
    FAMILY_NAME_ONLY("FML", "Alleen naam partner.", true, false);

    private String code;
    private String display;
    private boolean isActive;
    private boolean isDefault;
    PreferredNameUses(String code, String display, boolean isActive,  boolean isDefault) {
        this.code = code;
        this.display = display;
        this.isActive = isActive;
        this.isDefault = isDefault;
    }

    /**
     * <strong>selectDefault</strong>()<br><br>
     * Selects the default marked constant as indicate by the 'isDefault' attribute.
     * @return The default marked constant. When there's no element set as default, <i>null</i> is returned.
     */
    public PreferredNameUses selectDefault() {
        return EnumUtilities.selectDefault(PreferredNameUses.class);
    }

    /**
     * <strong>toPrettyString(<i></i>)</strong><br><br>
     * Assembles the person's object attributes from current instance and converts them into an easy readable format.
     */
    public String toPrettyString() {
        return "\nEnumerator: " + this.getClass().getSimpleName() + "\n" +
                "\tNaam:            " + this.name() + "\n" +
                "\tCode:            " + this.getCode() + "\n" +
                "\tOmschrijving:    " + this.getDisplay() + "\n" +
                "\tActief:          " + (this.isDefault() ? "Ja" : "Nee") + "\n" +
                "\tStandaard optie: " + (this.isDefault() ? "Ja" : "Nee");
    }
}
