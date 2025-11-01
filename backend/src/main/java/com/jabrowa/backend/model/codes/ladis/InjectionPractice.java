package com.jabrowa.backend.model.codes.ladis;

import com.jabrowa.backend.utilities.EnumUtilities;

 public record InjectionPractice(int number, String display, boolean isActive, boolean isDefault) {


    public enum _InjectionPractice {
        OOIT(1, "ooit", true, false),
        RECENT(2, "recent (laatste jaar)", true, false),
        ACTUEEL(3, "actueel (laatste maand)", true, false),
        NOOIT(4, "nooit", true, false),
        ONBEKEND(9, "onbekend", true, true);

        private final int number;
        private final String display;
        private final boolean isActive;
        private final boolean isDefault;

        _InjectionPractice(int number, String display, boolean isActive, boolean isDefault) {
            this.number = number;
            this.display = display;
            this.isActive = isActive;
            this.isDefault = isDefault;

        }

         public String toNiceString() {
            return EnumUtilities.ladisCodeToPrettyString(_InjectionPractice.class, this.name());

        }
    }
}

