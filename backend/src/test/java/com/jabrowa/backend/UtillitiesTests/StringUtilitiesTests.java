package com.jabrowa.backend.UtillitiesTests;

import com.jabrowa.backend.utilities.StringUtilities;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringUtilitiesTests {
    private final static Logger logger = Logger.getLogger(StringUtilitiesTests.class.getName());

    @Test
    public void testNormalize() {
        /*
        WHEN    the 'Normalize String' utility is called
        AND     the parameter is null
        THEN    an IllegalArgument is thrown.
         */
        assertThrows(IllegalArgumentException.class, ()-> {
                    StringUtilities.normalizeString(null);
                });

        /*
        WHEN    the 'Normalize String' utility is called
        AND     the parameter is an empty string
        THEN    an empty string is returned.
         */
        assertEquals("", StringUtilities.normalizeString(""));

        /*
        WHEN    the 'Normalized String' utility is called
        AND     the parameter is a 'blank' string (contains blank spaces only)
        THEN    an empty string is returned.
         */
        assertEquals("", StringUtilities.normalizeString("    "));

        /*
        WHEN    the 'Normalized String' utility method is called
        AND     the parameter doesn't contain any diacritics,
        THEN    the original string (parameter) is returned.
         */
        assertEquals("Donald Duck", StringUtilities.normalizeString("Donald Duck"));

        /*
        WHEN    the 'Normalize String' utility method is called
        AND     the parameter is a string containing one or more diacritics.
        THEN    the result would be the normalized string.
         */
        assertEquals("Gosibucuk Alaeror Eadne zici fosutortok", StringUtilities.normalizeString(
                "Ğöşibuçük Ålærør Ěádně žiči fősütörtök"));
    }
}
