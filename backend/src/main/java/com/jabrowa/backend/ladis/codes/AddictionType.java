package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.utilities.EnumUtilities;

public record AddictionType(int number, String type, String display, boolean isActive, boolean isDefault) {

    public enum _AddictionType {
        ALCOHOL(10, "alcoholverslaving", true, false),
        DRUGS(20, "drugsverslaving", true, false),
        GOKKEN(30, "gokverslaving", true, false),
        GAMEN(40, "gameverslaving", true, false),
        BEELDSCHERM(41, "computer- of beeldschermverslaving (overig)", true, false),
        SEX(50, "sexverslaving", true, false),
        ETEN(60, "eetverslaving/stoornis",true, false),
        ANDERS(97, " kind in eenouder gezin", true, true),
        ONBEKEND(99, "onbekend", true, true);

        private final int number;
        private final String display;
        private final boolean isActive;
        private final boolean isDefault;

        _AddictionType(int number, String display, boolean isActive, boolean isDefault) {
            this.number = number;
            this.display = display;
            this.isActive = isActive;
            this.isDefault = isDefault;
        }

        public String toNiceString() {
            return EnumUtilities.ladisCodeToPrettyString(_AddictionType.class, this.name());
        }
    }
}
