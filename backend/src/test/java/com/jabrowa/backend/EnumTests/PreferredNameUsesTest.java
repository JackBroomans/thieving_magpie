package com.jabrowa.backend.EnumTests;

import com.jabrowa.backend.model.enums.PreferredNameUses;
import com.jabrowa.backend.utilities.EnumUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.mock;

public class PreferredNameUsesTest {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    /*
    WHEN    The enumerator has implemented the Selectable interface.
    AND     no constant from the PreferredNameUses enumerator is marked as default.
    THEN    The result of the request to select the expected as default marked constant is null.
     */
    @Test
    public void requestDefaultConstantNoMarkTest() {
        PreferredNameUses prefNameUses = EnumUtilities.selectDefault(null);;
    }

    /*
    WHEN    The enumerator has implemented the Selectable interface
    AND     Exact one constant is marked as default
    THEN    Requesting the default marked constant returns the expected constant.
     */
    @Test
    public void requestDefaultConstantOneMarkTest() {
        PreferredNameUses preferentName = EnumUtilities.selectDefault(PreferredNameUses.class);
        Assertions.assertEquals(PreferredNameUses.GIVEN_NAME_ONLY, preferentName);
        LOGGER.info(preferentName.toPrettyString());
    }

}
