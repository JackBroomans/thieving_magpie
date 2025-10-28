package com.jabrowa.backend.EnumTests;

import com.jabrowa.backend.model.enums.PreferredNameUses;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.jabrowa.backend.utilities.EnumUtilities.selectDefault;

public class PreferredNameUsesTest {
    private static final Logger LOGGER = LoggerFactory.getLogger("PreferredNameUsesTest");

    /*
    WHEN    The enumerator has implemented the Selectable interface
    AND     Exact one constant is marked as default
    THEN    Requesting the default marked constant returns the expected constant.
     */
    @Test
    public void requestDefaultConstantOneMarkTest() {
        final String expectedName = PreferredNameUses.GIVEN_NAME_ONLY.name();
        final PreferredNameUses preferentNameUses    = selectDefault(PreferredNameUses.class);
        Assertions.assertEquals(expectedName, preferentNameUses.name());
        LOGGER.info(preferentNameUses.toPrettyString());
    }
}
