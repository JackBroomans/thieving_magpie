package com.jabrowa.backend.model.enums;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

import com.jabrowa.backend.utilities.EnumUtilities;
import jakarta.persistence.TemporalType;
import lombok.Getter;

@Getter
public enum DateTimeFormats {

    DATE_SIMPLE("dd-MM-yyyy", TemporalType.DATE, true),
    DATE_SIMPLE_FULL_MONTH("dd-MMM-yyyy", TemporalType.DATE , false),
    TIME("HH:mm", TemporalType.TIME, false),
    TIME_WITH_SECONDS("HH:mm:ss", TemporalType.TIME, false),
    DATE_TIME("dd-MM-yyyy HH:mm", TemporalType.TIMESTAMP, false),
    DATE_TIME_WITH_SECONDS("dd-MM-yyyy HH:mm:ss", TemporalType.TIMESTAMP, false);

    private final String pattern;
    private final TemporalType temporalType;
    private final boolean isDefault;

    DateTimeFormats(String pattern, TemporalType temporalType, boolean isDefault) {
        this.pattern = pattern;
        this.temporalType = temporalType;
        this.isDefault = isDefault;
    }

    /**
     * Returns the DateTimeFormatter for this format. The formatter uses the European locale for day and month names.
     * @return The DateTimeFormatter for the current selected format.
     */
    public DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(pattern, Locale.UK);
    }

    /**
     * <strong>SelectDefault</strong>()<br><br>
     * Selects the default marked Gender constant.
     * @return The default marked Gender constant. When there's no constant is marked as default, <i>null</i> is
     * returned, and when several constants are marked as default, the first (ordinal) constant is returned.
     */
    public DateTimeFormats selectDefault() {
        return EnumUtilities.selectDefault(DateTimeFormats.class);
    }

    /**
     * <strong>toNiceString(<i></i>)</strong><br><br>
     * Assembles the date and time formatter object attributes, and transfers them into an easy readable presentation.
     */
    public String toNiceString() {
        return "\nEnumerator: " + this.getClass().getSimpleName() + "\n" +
                "\tNaam:   " + this.name() + "\n" +
                "\tFormaat:         " + this.getPattern() + "\n" +
                "\tStandaard keuze: " + (this.isDefault() ? "Ja" : "Nee") + "\n";
    }


    public String format(TemporalAccessor temporal) {

        return switch (this.getTemporalType()) {
            case DATE -> {
                LocalDate date = (temporal instanceof LocalDateTime t ? t.toLocalDate() : LocalDate.from(temporal));
                yield date.format(getFormatter());
            }
            case TIME -> {
                if (!(temporal instanceof LocalTime || temporal instanceof LocalDateTime)) {
                    temporal = LocalTime.of(0,0,0);
                }
                LocalTime time = (temporal instanceof LocalDateTime t ? t.toLocalTime() : LocalTime.from(temporal));
                yield time.format(getFormatter());
            }
            case TIMESTAMP -> {
                LocalDateTime dt = (temporal instanceof LocalDateTime t ? t : LocalDate.from(temporal).atStartOfDay());
                yield dt.format(getFormatter());
            }
        };
    }

}
