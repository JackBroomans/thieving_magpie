package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.utilities.EnumUtilities;

public record LivingSituation(int number, String display, boolean isActive, boolean isDefault) {

    public enum _LivingSituation {
        ALLEENSTAAND(10, "alleenstaand", true, false),
        ALLEEN_MET_KIND(20, "met kind(eren) zonder partner", true, false),
        PARTNER_ZONDER_KIND(30, "met partner zonder kinderen", true, false),
        PARTNER_MET_KIND(40, "met partner en kind(eren)", true, false),
        KIND_EENOUDER(50, " kind in eenouder gezin", true, true),
        KIND_MEEROUDER(60, "kind in meeroudergezin", true, true),
        KIND_PLEEG(65, "kind in pleeggezin / gastgezin", true, true),
        ANDERS(97, "anders", true, true),
        ONBEKEND(99, "onbekend", true, true);

        private final int number;
        private final String display;
        private final boolean isActive;
        private final boolean isDefault;

        _LivingSituation(int number, String display, boolean isActive, boolean isDefault) {
            this.number = number;
            this.display = display;
            this.isActive = isActive;
            this.isDefault = isDefault;

        }

        public String toNiceString() {
            return EnumUtilities.ladisCodeToPrettyString(_LivingSituation.class, this.name());
        }
    }    
}
