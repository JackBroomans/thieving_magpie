package com.jabrowa.backend.EnumTests;

import com.jabrowa.backend.model.enums.Gender;
import com.jabrowa.backend.model.enums.PreferredNameUses;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        WHEN    a constant from the 'Gender' enumerator is instantiated
        AND     the 'selectDefault()' method is called,
        THEN    the instantiated constant is remains the current instance.
        */
        Gender gender = Gender.MALE;
        assertEquals("NOT_SPECIFIED", gender.selectDefault().name());
        assertEquals("M", gender.getCode());

        /*
        WHEN    a constant from the 'Gender' enumerator is instantiated
        AND     the default set constant is gained ad explicit assigned to that instantiated constant,
        Then    the default set constant becomes the value of the instantiated constant. (mutable class)
         */
        gender = gender.selectDefault().selectDefault();
        assertTrue(gender.isDefault());
        assertEquals("X", gender.getCode());

        /*
        Log the toNiceString() method to check the format
         */
        logger.info(gender.toNiceString());
    }


}
