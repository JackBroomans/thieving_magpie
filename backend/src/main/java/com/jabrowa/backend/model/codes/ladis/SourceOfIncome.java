package com.jabrowa.backend.model.codes.ladis;

import com.jabrowa.backend.utilities.EnumUtilities;

public record SourceOfIncome(int number, String display, boolean isActive, boolean isDefault) {

    public enum _SourceOfIncome {
        LOON (1, "loon zelfstandig eigen bedrijf", true, false),
        UITKERING (2, "uitkering", true, false),
        PENSIOEN (3, "AOW/pensioen", true, false),
        GEEN (4,  "geen eigen inkomen", true, false),
        STUDIEFINACIERING (5,  "studiefinanciering", true, false),
        ANDERS (8,  "anders", true, false),
        ONBEKEND (9,  "onbekend", true, true);

        private final int number;
        private final String display;
        private final boolean isActive;
        private final boolean isDefault;

        _SourceOfIncome(int number, String display, boolean isActive, boolean isDefault) {
            this.number = number;
            this.display = display;
            this.isActive = isActive;
            this.isDefault = isDefault;
        }

        public String toNiceString() {
            return EnumUtilities.ladisCodeToPrettyString(_SourceOfIncome.class, this.name());
        }
    }
}
