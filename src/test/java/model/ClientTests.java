package model;

import nl.schuldhulp.SchuldhulpApplication;
import nl.schuldhulp.model.repository.ClientRepository;
import nl.schuldhulp.model.classes.Client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SchuldhulpApplication.class)
@Transactional
@ComponentScan(basePackages = "nl.schuldhulp.model.repository")
public class ClientTests {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void clientNieuwID() {

        // Wanneer: Een (nieuwe) cliënt wordt geinitieerd,
        // Dan: Wordt er een nieuwe identiteit (UUID) toegekend als tekenreeks.
        Client client = new Client();
        assertEquals(36, client.getId().length());
    }

    @Test
    public void testCRUD() {
        // 1. Maak een nieuwe client aan en specifieer de attributen
        Client client = Client.builder()
                .id(UUID.randomUUID().toString())
                .clientnummer("12345678901234")
                .familienaam("Petteflat")
                .voorvoegsels("van de")
                .voorletters("Puk")
                .build();

        client = clientRepository.save(client);
        String id = client.getId();

        assertNotNull(id);
        assertEquals(36, client.getId().length());

        // 2. Read
        Client fetched = clientRepository.findById(id).orElse(null);
        assertNotNull(fetched);
        assertEquals("Petteflat", fetched.getFamilienaam());

        // 3. Wijzig de familienaam van de uit de database opgehaalde client
        fetched.setFamilienaam("Kraanwagen");
        clientRepository.save(fetched);

        Client updated = clientRepository.findById(id).orElse(null);
        assertEquals("Kraanwagen", updated.getFamilienaam());
        assertEquals("Puk", updated.getVoorletters());

        // 4. Verwijder de cliënt
        clientRepository.deleteById(id);
        assertFalse(clientRepository.existsById(id));
    }
}
