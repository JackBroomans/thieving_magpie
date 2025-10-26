package com.jabrowa.backend.model.enums;

import com.jabrowa.backend.model.interfaces.Selectable;
import com.jabrowa.backend.utilities.EnumUtilities;

// done: 23-10-2025 Add attributes 'displayName', and 'isDefault' to the enumerator 'backend.model.enums.Gender'
// todo: 23-10-2025 Implement the getters for the enumerator attributes of enumerator 'backend.model.enums,Gender'.
// done: 23-10-2025 Implement a method 'selectDefault()' in enumerator 'backend.model.enums.Gender' where the default set value is selected.
// done: 23-10-2025 Implement a method 'toPrettyString()' in enumerator 'backend.model.enums.Gender' to show the properties of the current enumerated value in an easy readable manner.
// todo: 23-10-2025 Add the Javadoc for the enumerator 'backend.model.enums.Gender'.
// todo: 23-10-2025 Unit test the Lombok generated getters for the attributes of enumerator 'backend.model.enums.Gender'.
// todo: 23-10-2025 Unit test the methods 'selectDefault()' and 'toPrettyString()' in enumerator 'backend.model.enums.Gender'.
public enum Gender implements Selectable {
    MALE ("Man", false),
    FEMALE ("Vrouw", false),
    BIPOLAIRE ("Bi-polair", false),
    UNDEFINED ("Niet vastgesteld", false),
    NOT_SPECIFIED ("Niet gespecificeerd", true);

    private final String display;
    private final boolean isDefaultValue;

    Gender(String display, boolean isDefault) {
        this.display = display;
        this.isDefaultValue = isDefault;
    }
    public Boolean isDefaultValue() { return isDefaultValue; }
    public String getDisplay() { return display; }

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
    public String toPrettyString() {
        return "\nClass: " + this.getClass().getSimpleName() + "\n" +
                "\tOption:          " + this.name() + "\n" +
                "\tDisplay name:    " + this.display + "\n" +
                "\tDefault setting: " + (this.isDefaultValue() ? "Yes" : "No");
    }
}
