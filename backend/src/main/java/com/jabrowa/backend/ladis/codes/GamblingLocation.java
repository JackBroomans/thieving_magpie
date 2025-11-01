package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.utilities.EnumUtilities;


public record GamblingLocation(int number, String display, boolean isActive, boolean isDefault) {

    public enum _GamblingLocation {
        ONBEKEND (9, "onbekend", true, false),
        HOLLAND_CASINO (10, "Holland Casino", true, false),
        AMUSEMENTSHAL (11, "Amusementshal", true, false),
        INTERNET (12,  "Internet", true, false),
        THUIS (13,  "Thuis en bij vrienden werk etc.", true, false),
        HORECA (14,  "Horeca gelegenheid", true, true);

        private final int number;
        private final String display;
        private final boolean isActive;
        private final boolean isDefault;

        _GamblingLocation(int number, String display, boolean isActive, boolean isDefault) {
            this.number = number;
            this.display = display;
            this.isActive = isActive;
            this.isDefault = isDefault;
        }

//        public String toNiceString() {
//            return EnumUtilities.ladisCodeToPrettyString(_GamblingLocation.class, this.name());
//        }
    }
}
