package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.utilities.EnumUtilities;

import lombok.Getter;

public record AddictionSeverity(int number, String display, boolean isActive, boolean isDefault) {

     @Getter
    public enum _AddictionSeverity {
        VERSLECTERD(0, "Verslechterd", true, false),
        ENIGZINS_VERSLECHTERRD(0, "Enigszins verslechterd", true, false),
        ONVERANDERD(0, "Onveranderd", true, false),
        ENIGZINS_VERBETERD(0, "Enigszins verbeterd", true, false),
        VERBETERD(0, "Verbeterd", true, false),
        KLACHTENVRIJ(0, "Klachtenvrij", true, false),
        ONBEKEND(0, "Onbekend", true, true);
 
        private final int number;
        private final String display;
        private final boolean isActive;
        private final boolean isDefault;
 
        _AddictionSeverity (int number, String display, boolean isActive, boolean isDefault) {
            this.number = number;
            this.display = display;
            this.isActive = isActive;
            this.isDefault = isDefault;
        }

//        public String toNiceString() {
//            return EnumUtilities.ladisCodeToPrettyString(_AddictionSeverity.class, this.name());
//        }
    }

}
