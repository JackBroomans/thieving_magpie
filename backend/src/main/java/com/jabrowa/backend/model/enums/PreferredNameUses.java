package com.jabrowa.backend.model.enums;

import com.jabrowa.backend.model.interfaces.Selectable;
import lombok.Getter;

/**
 * <strong>PreferredNameUses</strong> - enumerator<br><br>
 * Enumerator which contains the options to use the given- and the family names in a particular way, according to the
 * meet the legal preferences offered. The following options are available:
 * <ul>
 *     <li>Use of only the given name. The family name (partner's name) is ignored. However when the given name is not
 *     specified, then the family name is used.</li>
 *     <li>Using family name followed by the given name. When one of the two name components aren't specified, then it
 *     will be ignored.</li>
 *     <li>Given name followed by the family name. When one of the two name components aren't specified, then it
 *     will be ignored. </li>
 *     <li>Use of the family name only.The given name (namegiven at birth) is ignored. However when the family name is
 *     not specified, then the family name is used.</li>
 * </ul>
 * When both parts exist and are applied, they will be separated by a hyphen.
 */
@Getter
public enum PreferredNameUses implements Selectable {
    GIVEN_NAME_ONLY ("", true),
    FAMILY_NAME_AND_GIVEN_NAME ("", false),
    GIVEN_NAME_AND_FAMILY_NAME("", false),
    FAMILY_NAME_ONLY("", false);

    private String display;
    private boolean isDefaultValue;
    PreferredNameUses(String display, boolean isDefault) {
        this.display = display;
        this.isDefaultValue = isDefault;
    }

    /**
     * <strong>selectDefault</strong>()<br><br>
     * Retrieves the default set PreferredNameUses value based on its default setting.
     *
     * @return The default set PreferredNameUses. When there's no element set as default, <i>null</i> is returned.
     */
    public Boolean isDefaultValue() {
        return isDefaultValue;
    }


    /**
     * <strong>toPrettyString(<i></i>)</strong><br><br>
     * Assembles the person's object attributes from current instance and converts them into an easy readable format.
     */
    public String toPrettyString() {
        return "Enumerator: " + this.getClass().getName() + "\n" +
                "\tOption:          " + this.name() + "\n" +
                "\tDisplay name:    " + this.display + "\n" +
                "\tDefault setting: " + (this.isDefaultValue ? "Yes" : "No");
    }
}
