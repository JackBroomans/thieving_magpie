package com.jabrowa.backend.ladis.codes;

import com.jabrowa.backend.utilities.EnumUtilities;


public record Education(int number, String display, boolean isActive, boolean isDefault) {

    public enum _Education {
        KLEUTER (10, "Onderwijs aan kleuters", true, false),
        GEEN (11, "Geen", true, false),
        BASIS (20, "Basis onderwijs", true, false),
        SPECIAAL_BASIS (21,  "Speciaal Basis onderwijs", true, false),
        SPECIAAL_VOORTGEZET (31,  "Voortgezet Speciaal onderwijs", true, false),
        VMBO_PRAKTIJK (32,  "VMBO praktijk", true, false),
        VMBO_T_MAVO(33,  "LBO:VMBO-t: MAVO", true, true),
        MBO12(42,  "MBO 1 en 2", true, true),
        MBO34_HAVO_VWO(43,  "HAVO: VWO: MBO 3 en 4", true, true),
        HBO_BACHELOR(52,  "HBO Bachelor", true, true),
        WO_BACHCHELOR(53,  " WO bachelor", true, true),
        HBO_WO_MASTER(60,  "HBO master: WO Master", true, true),
        POST_DOC(70,  "Post doctoraal", true, true),
        ONBEKEND(99,  "Onbekend", true, true);

        private final int number;
        private final String display;
        private final boolean isActive;
        private final boolean isDefault;

        _Education(int number, String display, boolean isActive, boolean isDefault) {
            this.number = number;
            this.display = display;
            this.isActive = isActive;
            this.isDefault = isDefault;
        }

        public String toNiceString() {
            return EnumUtilities.ladisCodeToPrettyString(_Education.class, this.name());
        }
    }
}


