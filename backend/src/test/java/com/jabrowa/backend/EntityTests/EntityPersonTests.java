package com.jabrowa.backend.EntityTests;

import com.jabrowa.backend.model.entities.Client;
import static org.junit.jupiter.api.Assertions.*;

import com.jabrowa.backend.model.enums.Gender;
import com.jabrowa.backend.model.enums.PreferredNameUses;
import com.jabrowa.backend.utilities.EnumUtilities;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class EntityPersonTests {
    private static final Logger LOGGER = LoggerFactory.getLogger("EntityPersonTests");
    @Test
    public void PersonInitializationTest() {

        /*
        WHEN    a Client entity class, which extends the Person entity class, is instantiated,
        THEN    the default constants for the preferred use of name and gender are preselected
         */
        Client client = new Client();
        assertNotNull(client.getPreferredNameUse());
        assertTrue(client.getPreferredNameUse().isDefault());

        assertNotNull(client.getGender());
        assertTrue(client.getGender().isDefault());

        /*
        WHEN    a Client entity class, which extends the Person entity class, is instantiated,
        AND     one or more mandatory fields aren't specified
        THEN    the method 'validate()' of the (extended) 'person' entity class, returns 'false'
         */
        assertFalse(client.validated());

        /*
        WHEN    a Client entity class, which extends the Person entity class, is instantiated
        AND     a few properties from the person class are set with their Lombok setters,
        THEN    the field values are accepted and approachable by the Client instance
         */
        final String GIVEN_NAME = "Dap";
        final String NICKNAME = "Dikkertje";
        final String INITIALS = "D.";

        client.setPreferredNameUse(EnumUtilities.selectDefault(PreferredNameUses.class));
        client.setGivenName(GIVEN_NAME);
        client.setInitials(INITIALS);
        client.setNickname(NICKNAME);
        client.setGender(Gender.MALE);

        assertEquals(GIVEN_NAME, client.getGivenName());
        assertEquals(NICKNAME, client.getNickname());

        /*
        WHEN    a Client entity class, which extends the Person entity class, is instantiated,
        AND     all mandatory fields are specified
        THEN    the method 'validate()' of the (extended) 'person' entity class, returns 'true'
         */
        assertTrue(client.validated());

        /*
        Log the toNiceString() method to check the format
         */
        LOGGER.info(client.toNiceString());
    }
}
