package com.jabrowa.backend.model.codes.ladis;

import lombok.Getter;

public record UsageFrequency(int number, String display, boolean isActive, boolean isDefault) {

    @Getter
    public enum _UsageFrequency {
        DAG_MEERMALIG(1, "meermalen daags", true, false),
        DAGELIJKS(2, "dagelijks", true, false),
        WEEK_MEERMALIG(3, "meermalen per week", true, false),
        WEKELIJKS(4, "wekelijks", true, false),
        ONREGELMATIG(9, "onregelmatig", true, false),
        NVT(9, "niet (meer) van toepassing", true, false),
        ONBEKEND(9, "onbekend", true, true);

        private final int number;
        private final String display;
        private final boolean isActive;
        private final boolean isDefault;

        _UsageFrequency(int number, String display, boolean isActive, boolean isDefault) {
            this.number = number;
            this.display = display;
            this.isActive = isActive;
            this.isDefault = isDefault;
        }

        public String toNiceString() {
        return "Frequentie van gebruik\n" +
                "\tLadis code:      " + this.getNumber() + "\n" +
                "\tOmschrijving:    " + this.getDisplay() + "\n" +
                "\tActief:          " + (this.isActive() ? "ja" : "Nee") + "\n" +
                "\tStandaard keuze: " + (this.isDefault() ? "ja" : "Nee") + "\n";
        }
    }
}