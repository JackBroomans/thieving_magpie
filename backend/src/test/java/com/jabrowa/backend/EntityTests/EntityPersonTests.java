package com.jabrowa.backend.EntityTests;

import com.jabrowa.backend.model.entities.Client;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class EntityPersonTests {
    private static final Logger LOGGER = LoggerFactory.getLogger("EntityPersonTests");
    @Test
    void PersonInitializationTest() {

        Client client = new Client();
        client.setGivenName(null);
        client.setNickName("Dikkertje");

        assertEquals("Dikkertje", client.getNickName());

        LOGGER.info(client.toPrettyString());
    }
}
