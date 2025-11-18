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


/**
 * <strong>DateTimeFormats</strong> - Enumerator<br><br>
 * Enumerator which defined the formats which can be used to present dates- and times in a easy way. All applicable
 * formats are defined and contain the following attributes:
 * <ol>
 *     <li>The pattern that is applied in the formatter of the date and/or time constant.</li>
 *     <li>The temporal type of the constant, use to avoid type mismatches./li>
 *     <li>An indicator if the  constant is the default presentation format.</li>
 * </ol>
 * <i>The use of this enumerator to format date and/or time presentations is demonstrated in RegularEnumTests.</i>
 */
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
     * Returns the DateTimeFormatter for this format.<>br>
     * The formatter uses the European locale for day and month names, with the pattern as defined in the
     * applied constant.
     * @return The DateTimeFormatter for the current selected format.
     */
    public DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(pattern, Locale.UK);
    }


    /**
     * <strong>format(<i>TemporalAccessor</i>)</strong><br><br>
     * Uses the temporal type of the passed date and/or time to adjust it with the requested formats. When for instance,
     * a temporal type without a time component (LocalDate), is requested with a time presentation, then the temporal
     * type is adjusted to one with a time component (LocalDateTime or Time) This avoids conversion errors.
     * @param temporal The date and/or time in its temporal type.
     * @return The date and/or time formatted to the requested (through the enumerator constant) as a string for
     * presentation.
     */
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

}
