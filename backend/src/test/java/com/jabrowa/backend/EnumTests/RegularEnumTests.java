package com.jabrowa.backend.EnumTests;

import com.jabrowa.backend.model.enums.DateTimeFormats;
import com.jabrowa.backend.model.enums.Gender;
import com.jabrowa.backend.model.enums.PreferredNameUses;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegularEnumTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    // PreferredNameUses
    @Test
    public void PreferredNameUsesTests() {
        /*
        WHEN    a constant from the 'PreferredNameUses' enumerator is instantiated
        AND     the 'selectDefault()' method is called
        THEN    the instantiated constant is remains the actual instance.
        */
        PreferredNameUses preferredNameUses = PreferredNameUses.GIVEN_NAME_AND_FAMILY_NAME;
        assertEquals("GIVEN_NAME_ONLY", preferredNameUses.selectDefault().name());

        /*
        WHEN    a constant from the 'PreferredNameUses' enumerator is instantiated
        AND     the default set constant is gained ad explicit assigned to that instantiated constant,
        Then    the default set constant becomes the value of the instantiated constant. (mutable class)
         */
        preferredNameUses = preferredNameUses.selectDefault();
        assertTrue(preferredNameUses.isDefault());

        /*
        Log the toNiceString() method to check the format
         */
        logger.info(preferredNameUses.toNiceString());
    }

    // Gender
    @Test
    public void GenderTests() {
        /*
        WHEN    a constant from the 'Gender' enumerator is selected
        AND     the default assigned constant in the Gender enumerator is requested by calling selectDefault(),
        AND     the fetched default assigned constant is not explicit assigned to the current selected one,
        THEN    the original selected constant remains selected.
        */
        Gender gender = Gender.MALE;
        assertEquals("NOT_SPECIFIED", gender.selectDefault().name());
        assertEquals("M", gender.getCode());

        /*
        WHEN    a constant from the 'Gender' enumerator is selected
        AND     the default assigned constant in the Gender enumerator is requested by calling selectDefault()
        AND     this default constant is assigned to the current selected one,
        Then    the default assigned constant is selected.
         */
        gender = Gender.FEMALE;
        gender = gender.selectDefault().selectDefault();
        assertTrue(gender.isDefault());
        assertEquals("X", gender.getCode());

        /*
        Log the toNiceString() method to check the format
         */
        logger.info(gender.toNiceString());
    }

    // DateTimeFormatters
    @Test
    public void DateTimeFormattersTests() {
        /*
        WHEN    a LocalDateTime is created
        AND     the requested format of this LocalDateTime is only the day representation,
        THEN    only the date is presented.
         */
        LocalDateTime nowDateTime = LocalDateTime.now();
        String formatted = nowDateTime.format(DateTimeFormats.DATE_SIMPLE.getFormatter());
        assertEquals(10, formatted.length());

        /*
        WHEN    a LocalDate is created, thus the time part isn't applied
        AND     the requested format of this LocalDate is date and time,
        THEN    a time section with the value '00:00:00' (midnight) is added to the date.
         */
        LocalDate nowDate = LocalDate.now();
        formatted = DateTimeFormats.DATE_TIME_WITH_SECONDS.format(nowDate);
        assertEquals(19, formatted.length());
        assertEquals("00:00:00", DateTimeFormats.TIME_WITH_SECONDS.format(nowDate));

        /*
        WHEN    only a time is specified, either a local or general one
        AND     this time is valid
        THEN    the time formatted by the DateTimeFormats-enumerator equals the specified time
         */
        LocalTime time = LocalTime.now();
        assertEquals(8, DateTimeFormats.TIME_WITH_SECONDS.format(time).length());
        assertEquals(5, DateTimeFormats.TIME.format(time).length());
        int hour = time.getHour();
        assertEquals(String.valueOf(hour), DateTimeFormats.TIME_WITH_SECONDS.format(time).substring(0,2));
        hour = 4;
        time = LocalTime.of(4,36);
        assertEquals("0" + String.valueOf(hour), DateTimeFormats.TIME.format(time).substring(0,2));

        /*
        WHEN    a (local) DateTime is created and applied
        AND     only the time is requested by the format,
        THEN    only the time is presented
         */
        formatted = DateTimeFormats.TIME.format(LocalDateTime.now());
        assertEquals(5, formatted.length());
    }


}
