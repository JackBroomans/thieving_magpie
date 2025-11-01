package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.utilities.EnumUtilities;


public record MethodsOfUse(int number, String diplay, boolean isActivective, boolean isDefault) {

    public enum _MethodsOfUse {
        SPUITEN(1, "spuiten", true, false),
        ROKEN(2, "roken/basen/chinezen", true, false),
        SNUIVEN(3, "snuiven", true, false),
        SLIKKEN(4, "slikken/eten", true, false),
        DRINKEN(5, "drinken", true, false),
        NVT(8, "niet van toepassing", true, false),
        ONBEKEND(9, "onbekend", true, true);

        private final int number;
        private final String display;
        private final boolean isActive;
        private final boolean isDefault;

        _MethodsOfUse(int number, String display, boolean isActive, boolean isDefault) {
            this.number = number;
            this.display = display;
            this.isActive = isActive;
            this.isDefault = isDefault;

        }

//        public String toNiceString() {
//            return EnumUtilities.ladisCodeToPrettyString(_MethodsOfUse.class, this.name());
//        }
    }
}
