package com.jabrowa.backend.model.enums;

// todo: plan the implementation of the UserRoles enumerators
// todo: plan Unit test if the @Getter annotation is working for the display property
// todo: plan Unit test if the @Getter annotation is working for the defaultValue property
// todo: plan - Unit test if the default set Role (in this case 'Employee') is fetched by 'Role.getDefault'

import lombok.Getter;

@Getter
public enum UserRoles {
    Administrator("Systeembeheerder", false),
    Employee("Medewerker", false),
    Client("Cliënt", true),
    ClientRelation("Relatie cliënt", false),
    Practitioner("Behandelaar", false),
    ExternalContact("Externe Relatie", false);

    private final String display;
    private final boolean defaultValue;
    UserRoles(String display, boolean defaultValue) {
        this.display = display;
        this.defaultValue = defaultValue;
    }

    public Boolean isDefaultValue() {
        return this.defaultValue;
    }
}
