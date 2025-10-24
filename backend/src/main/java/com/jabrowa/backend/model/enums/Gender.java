package com.jabrowa.backend.model.enums;

import com.jabrowa.backend.model.interfaces.Selectable;
import com.jabrowa.backend.utilities.SelectDefaultUtility;
import lombok.Getter;

import java.util.Optional;

// done: 23-10-2025 Add attributes 'displayName', and 'isDefault' to the enumerator 'backend.model.enums.Gender'
// todo: 23-10-2025 Implement the getters for the enumerator attributes of enumerator 'backend.model.enums,Gender'.
// todo: 23-10-2025 Implement a method 'selectDefault()' in enumerator 'backend.model.enums.Gender' where the default set value is selected.
// todo: 23-10-2025 Implement a method 'toPrettyString()' in enumerator 'backend.model.enums.Gender' to show the properties of the current enumerated value in an easy readable manner.
// todo: 23-10-2025 Add the Javadoc for the enumerator 'backend.model.enums.Gender'.
// todo: 23-10-2025 Unit test the Lombok generated getters for the attributes of enumerator 'backend.model.enums.Gender'.
// todo: 23-10-2025 Unit test the methods 'selectDefault()' and 'toPrettyString()' in enumerator 'backend.model.enums.Gender'.
@Getter
public enum Gender {
    MALE ("Man", false),
    FEMALE ("Vrouw", false),
    BIPOLAIRE ("Bi-polair", false),
    UNDEFINED ("Niet vastgesteld", false),
    NOT_SPECIFIED ("Niet gespecificeerd", true);

    private String displayName;
    private boolean isDefault;

    Gender(String displayName, boolean isDefault) {
        this.displayName = displayName;
        this.isDefault = isDefault;
    }


    /**
     * <strong>toPrettyString(<i></i>)</strong><br><br>
     *  Assembles the gender's object attributes from the current instance, and transfers them into
     *  an easy readable format.
     */
    public String toPrettyString() {
        return "Class: " + this.getClass().getName() + "\n" +
                "\tOption:          " + this.name() + "\n" +
                "\tDisplay name:    " + this.displayName + "\n" +
                "\tDefault setting: " + (this.isDefault() ? "Yes" : "No");
    }
}
