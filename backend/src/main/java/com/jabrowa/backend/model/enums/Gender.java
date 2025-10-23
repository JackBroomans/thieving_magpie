package com.jabrowa.backend.model.enums;

import lombok.Getter;

// done: 23-10-2025 Add attributes 'displayName', and 'isDefault' to the enumerator 'backend.model.enums.Gender'
// todo: 23-10-2025 Implement the getters for the enumerator attributes of enumerator 'backend.model.enums,Gender'.
// todo: 23-10-2025 Implement a method 'selectDefault()' in enumerator 'backend.model.enums.Gender' where the default set value is selected.
// todo: 23-10-2025 Implement a method 'toPrettyString()' in enumerator 'backend.model.enums.Gender' to show the properties of the current enumerated value in an easy readable manner.
// todo: 23-10-2025 Add the Javadoc for the enumerator 'backend.model.enums.Gender'.
// todo: 23-10-2025 Unit test the Lombok generated getters for the attributes of enumerator 'backend.model.enums.Gender'.
// todo: 23-10-2025 Unit test the methods 'selectDefault()' and 'toPrettyString()' in enumerator 'backend.model.enums.Gender'.
@Getter
public enum Gender {
    MALE ("man", false),
    FEMALE ("vrouw", false),
    BIPOLAIRE ("bi-polair", false),
    UNDEFINED ("niet vastgesteld", false),
    NOT_SPECIFIED ("niet gespecificeerd", true);

    private String displayName;
    private boolean isDefault;

    Gender(String displayName, boolean isDefault) {
        this.displayName = displayName;
        this.isDefault = isDefault;
    }
}
