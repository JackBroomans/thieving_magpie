package com.jabrowa.backend.EnumTests;

import com.jabrowa.backend.model.enums.PreferredNameUses;
import com.jabrowa.backend.utilities.EnumUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PreferredNameUsesTest {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    /*
    WHEN    PreferredNameUses is instantiated
    AND     PreferredNameUses has implemented the Selectable interface
    AND     Exact one constant is marked as default
    THEN    Requesting the default marked constant returns the expected constant.
     */
    @Test
    public void requestDefaultConstantOneDefaultMarkTest() {
        PreferredNameUses preferentName = EnumUtilities.selectDefault(PreferredNameUses.class);
        Assertions.assertEquals(PreferredNameUses.GIVEN_NAME_ONLY, preferentName);
        LOGGER.info(preferentName.toPrettyString());
    }
}
