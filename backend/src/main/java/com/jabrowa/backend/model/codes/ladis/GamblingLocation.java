package com.jabrowa.backend.model.codes.ladis;

import lombok.Getter;

public record GamblingLocation(int number, String display, boolean isActive, boolean isDefault) {

    @Getter
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

        public String toNiceString() {
        return "Favoriete plaatst om te gokken\n" +
            "\tLadis code:      " + this.getNumber() + "\n" +
            "\tOmschrijving:    " + this.getDisplay() + "\n" +
            "\tActief:          " + (this.isActive() ? "ja" : "Nee") + "\n" +
            "\tStandaard keuze: " + (this.isDefault() ? "ja" : "Nee") + "\n";
        }
    }

}
