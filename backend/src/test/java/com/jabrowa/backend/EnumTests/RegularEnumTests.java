package com.jabrowa.backend.EnumTests;

import com.jabrowa.backend.model.entities.Client;
import com.jabrowa.backend.model.enums.DateTimeFormats;
import com.jabrowa.backend.model.enums.Gender;
import com.jabrowa.backend.model.enums.NameFormats;
import com.jabrowa.backend.model.enums.PreferredNameUses;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegularEnumTests {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    /* Gender */
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
        LOGGER.info(gender.toNiceString());
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

    /* PreferredNameUses */
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
        LOGGER.info(preferredNameUses.toNiceString());
    }

    /* NameFormats */
    @Test
    public void NameFormatsInformalTests() {

    }

    @Test
    public void  NameFormatsFormalTests() {
        /*
         FORMAL_FAMILY format with just the family name
         */
        Client clientPluk = new Client();

        /*
        WHEN    the family name isn't specified
        AND     the given name isn't specified,
        AND     the 'FORMAL_FAMILY' format is applied,
        THEN    An empty string is returned on all formal presentations
         */
        assertTrue(clientPluk.format(NameFormats.FORMAL_FAMILY, false).isBlank());

        /*
        WHEN    the family name is specified, including the prefixes
        AND     initials aren't specified
        AND     the 'FORMAL_FAMILY' format is applied,
        THEN    the presentation will match <prefixes> <family name>
         */
        clientPluk.setPrefixesFamilyName("van de");
        clientPluk.setFamilyName("Petteflet");
        assertEquals("van de Petteflet", clientPluk.format(NameFormats.FORMAL_FAMILY));

        /*
        WHEN    the family name isn't specified
        AND     prefixes belonging to the family name are specified
        AND     initials are specified
        AND     the 'FORMAL_FAMILY' format is applied,
        THEN    the presentation is an empty sting, initials and prefixes are ignored
         */
        clientPluk.setFamilyName(null);
        assertTrue(clientPluk.format(NameFormats.FORMAL_FAMILY).isBlank());
        clientPluk.setInitials("P.");
        assertEquals("P.van de", clientPluk.getInitials() + clientPluk.getPrefixesFamilyName());
        assertTrue(clientPluk.format(NameFormats.FORMAL_FAMILY).isBlank());

        /*
        WHEN    the family name is specified
        AND     prefixes belonging to the family name are specified
        AND     initials are specified,
        AND     the 'FORMAL_FAMILY' format is applied,
        THEN    the name is presented as '<initials> <prefixes> <family name>'
         */
        clientPluk.setFamilyName("Petteflet");
        assertEquals("P. van de Petteflet", clientPluk.format(NameFormats.FORMAL_FAMILY));

        /*
        WHEN    the family name is specified
        AND     prefixes belonging to the family name are specified
        AND     initials are specified,
        AND     the prefix titles are specified
        AND     the 'FORMAL_FAMILY' format is applied,
        AND     the 'include titles parameter' is set to false,
        THEN    the name is presented as '<initials> <prefixes> <family name>'
         */
        clientPluk.setPrefixTitles("dhr.");
        assertEquals("P. van de Petteflet", clientPluk.format(NameFormats.FORMAL_FAMILY, false));

        /*
        WHEN    the family name is specified
        AND     prefixes belonging to the family name are specified
        AND     initials are specified,
        AND     the prefix titles are specified
        AND     the 'FORMAL_FAMILY' format is applied,
        AND     the 'include titles parameter' is not present (true by default),
        THEN    the name is presented as '<titles> <initials> <prefixes> <family name>'
         */
        clientPluk.setPrefixTitles("dhr.");
        assertEquals("dhr. P. van de Petteflet", clientPluk.format(NameFormats.FORMAL_FAMILY));

        LOGGER.info("Format FORMAL_FAMILY -> '{}' <- expected.", clientPluk.format(NameFormats.FORMAL_FAMILY));

        /*
        FORMAL_MAIDEN format with just the maiden name
        */
        Client clientJanneke = new Client();
        /*
        WHEN    both names, family and maiden, are specified
        AND     the 'include titles parameter' is set to true
        AND     no titles are specified
        AND     the 'FORMAL_MAIDEN' format is applied,
        THEN    the family name is ignored
        AND     <initials> <prefixes> <maiden name> is presented
         */
        clientJanneke.setInitials("J.");
        clientJanneke.setFamilyName("Buurmeisje");
        clientJanneke.setMaidenName("Jip");
        clientJanneke.setPrefixesMaidenName("van");
        assertEquals("J. van Jip", clientJanneke.format(NameFormats.FORMAL_MAIDEN, true));

        /*
        WHEN    both names, family and maiden, are specified
        AND     the 'include titles parameter' is set to true
        AND     titles are specified
        AND     the 'FORMAL_MAIDEN' format is applied,
        THEN    the family name is ignored
        AND     <titles> <initials> <prefixes> <maiden name> is presented
         */
        clientJanneke.setPrefixTitles("mej.");
        assertEquals("mej. J. van Jip", clientJanneke.format(NameFormats.FORMAL_MAIDEN, true));

        LOGGER.info("Format FORMAL_MAIDEN -> '{}' <- expected.", clientJanneke.format(NameFormats.FORMAL_MAIDEN));

        /*
        FORMAL_FAMILY_MAIDEN format with first family name followed by the maiden name separated with a hyphen
        */
        Client clientAbel = new Client();
        /*
        WHEN    only the family name is specified
        AND     the initials are specified
        AND     titles are specified
        AND     the 'include titles parameter' parameter not applied
        AND     the 'FORMAL_FAMILY_MAIDEN' format is applied,
        THEN    the family name is presented
        AND     the titles and initials are placed before the name
         */
        clientAbel.setInitials("A.");
        clientAbel.setFamilyName("Roef");
        clientAbel.setPrefixTitles("dhr.");
        assertEquals("dhr. A. Roef", clientAbel.format(NameFormats.FORMAL_FAMILY_MAIDEN));

        /*
        WHEN    only the maiden name is specified
        AND     the initials are specified
        AND     titles are specified
        AND     the 'include titles parameter' parameter is set to false,
        THEN    only the maiden name is applied
        AND     the titles are ignored
         */
        clientAbel.setInitials("A.");
        clientAbel.setFamilyName(null);
        clientAbel.setMaidenName("Schmidt");
        assertEquals("dhr. A. Schmidt", clientAbel.format(NameFormats.FORMAL_FAMILY_MAIDEN, true));

        /*
        WHEN    both names are specified
        AND     initials are specified
        AND     the titles are specified
        AND     the 'include titles parameter' parameter is set to false,
        THEN    both names are applied in the right order of; family name - maiden name
        AND     a hyphen between the two names is added
        AND     the initials are placed before the name while the titles are ignored
         */
        clientAbel.setFamilyName("Roef");
        assertEquals("A. Roef - Schmidt",
                clientAbel.format(NameFormats.FORMAL_FAMILY_MAIDEN, false));

        LOGGER.info("Format FORMAL_FAMILY_MAIDEN -> '{}' <- expected.",
                clientAbel.format(NameFormats.FORMAL_FAMILY_MAIDEN));

        /*
        Formal format with first family name followed by the maiden name separated with a hyphen
        */
        Client clientBerend = new Client();
         /*
        WHEN    an empty client instance is tried to be formatted according the FORMAL_MAIDEN_FAMILY constant
        THEN    the result is an empty string
         */
        assertTrue(clientBerend.format(NameFormats.FORMAL_MAIDEN_FAMILY).isBlank());
        assertEquals("",  clientBerend.format(NameFormats.FORMAL_MAIDEN_FAMILY));

        /*
        WHEN    neither maiden- nor family name are specified
        AND     the initial is specified
        AND     the prefix of the maiden name is specified
        AND     the format according the FORMAL_MAIDEN_FAMILY constant is requested,
        THEN    an empty string is the result of the formatting process
         */
        clientBerend.setInitials("B.");
        clientBerend.setPrefixesMaidenName("van");
        assertTrue(clientBerend.format(NameFormats.FORMAL_MAIDEN_FAMILY).isBlank());
        assertEquals("",  clientBerend.format(NameFormats.FORMAL_MAIDEN_FAMILY));

        /*
        WHEN    the family name is specified, but the maiden name isn't
        AND     the initial is specified
        AND     the prefix of the maiden name is specified
        AND     the format according the FORMAL_MAIDEN_FAMILY constant is requested,
        THEN    the family name is presented including the initial
        AND     the prefix of the maiden name is ignored
         */
        clientBerend.setFamilyName("Botje");
        assertEquals("B. Botje",  clientBerend.format(NameFormats.FORMAL_MAIDEN_FAMILY));

        /*
        WHEN    both maiden- and family names are specified
        AND     the initial is specified
        AND     the prefix of the maiden name is specified
        AND     the title is specified,
        THEN    the result is the presentation of both names seperated by a hyphen in the correct order
                including prefix
        AND      both title and initial are placed previous to the names.
         */
        clientBerend.setMaidenName("Zuidlaren");
        clientBerend.setPrefixTitles("dhr.");
        assertEquals("dhr. B. van Zuidlaren - Botje", clientBerend.format(NameFormats.FORMAL_MAIDEN_FAMILY));

        LOGGER.info("Format FORMAL_MAIDEN_FAMILY -> '{}' <- expected.",
                clientPluk.format(NameFormats.FORMAL_MAIDEN_FAMILY));

    }

}
