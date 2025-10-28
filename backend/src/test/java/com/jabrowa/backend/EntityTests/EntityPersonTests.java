package com.jabrowa.backend.EntityTests;

import com.jabrowa.backend.model.entities.Client;
import com.jabrowa.backend.model.enums.Gender;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityPersonTests {
    private static final Logger LOGGER = LoggerFactory.getLogger("EntityPersonTests");
    @Test
    public void PersonInitializationTest() {

        Client client = new Client();
        client.setGivenName().setGivenName("Dap");
        client.setNickName("Dikkertje");
        client.setGender(Gender.MALE);

        LOGGER.info(client.toPrettyString());
    }
}
