package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.utilities.EnumUtilities;

public record HomeStay(int number, String display, boolean isActive, boolean isDefault) {

    public enum _HomeStay {
        EIGEN_HUIS(10, "eigen huis (huur/koop)", true, false),
        PENSION_KOSTHUIS(11, "pension/kosthuis", true, false),
        OUDERLIJK_HUIS(12, "ouderlijk huis", true, false),
        KAMERS(13, "op kamers", true, false),
        FAMILIE_RELATIE(14, "familie/kennissen/relatie", true, false),
        PENITENTIAIR(15, "penitentiaire inrichting", true, false),
        KLINISCH_GGZ(16, "klinische GGZ voorziening", true, true),
        INSTITUUT_AGZ(17, "ander instituut (AGZ)", true, true),
        BESCHERMD(18, "beschermd wonen", true, true),
        TEHUIS_SOCIAAL(19, "tehuis/sociaal pension", true, true),
        AZC(96, "asielzoekerscentrum", true, true),
        STRAAT_ZWERVEND(97, "op straat/zwervend", true, true),
        ANDERSZINS(9, "anderszins", true, true),
        ONBEKEND(98, "onbekend", true, true);
 
        private final int number;
        private final String display;
        private final boolean isActive;
        private final boolean isDefault;
 
        _HomeStay(int number, String display, boolean isActive, boolean isDefault) {
            this.number = number;
            this.display = display;
            this.isActive = isActive;
            this.isDefault = isDefault;
        }

        public String toNiceString() {
            return EnumUtilities.ladisCodeToPrettyString(_HomeStay.class, this.name());
       }
    }
}
