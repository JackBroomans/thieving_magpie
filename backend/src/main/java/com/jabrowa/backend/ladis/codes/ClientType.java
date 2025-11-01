package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.utilities.EnumUtilities;

public record ClientType(int number, String disply, boolean isActive, boolean isDefault) {

    public enum _ClientType {
        GEBRUIKER(1, "gebruiker (ex-)", true, false),
        RELATIE(2, "naaste van (ex-)gebruiker", true, false),
        NIET_VERSLAAFD(8, "niet verslaafd", true, false),
        ONBEKEND(9, "onbekend", true, false);

        private final int number;
        private final String display;
        private final boolean isActive;
        private final boolean isDefault;

        _ClientType(int number, String display, boolean isActive, boolean isDefault) {
            this.number = number;
            this.display = display;
            this.isActive = isActive;
            this.isDefault = isDefault;
        }

//        public String toNiceString() {
//            return EnumUtilities.ladisCodeToPrettyString(_ClientType.class, this.name());
//        }

    }
}
