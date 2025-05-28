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

import static nl.schuldhulp.functies.clientFuncties.isClientnummerGeldig;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SchuldhulpApplication.class)
@Transactional
@ComponentScan(basePackages = "nl.schuldhulp.model.repository")
public class ClientTests {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void isClientnummerGeldigTest() {

        // Wanneer: Een clientnummer is gegenereerd en het formaat is ongeldig,
        // Dan:     Geeft de validatie 'false' terug.
        // Bij:     Onvolledig datumdeel
        String clientnummer = "25040-0000";
        assertFalse(isClientnummerGeldig(clientnummer));
        // Bij:     Ontbrekend koppelteken
        clientnummer = "2505230116";
        assertFalse(isClientnummerGeldig(clientnummer));
        // Bij:     Ongeldig volgnummer (te lang, te kort of geen getal)
        clientnummer = "250523-01234";
        assertFalse(isClientnummerGeldig(clientnummer));
        clientnummer = "250523-012";
        assertFalse(isClientnummerGeldig(clientnummer));
        clientnummer = "250523-A012";
        assertFalse(isClientnummerGeldig(clientnummer));
        // BIJ:     Datumdeel representeert geen bestaande datum
        clientnummer = "251831-0011";
        assertFalse(isClientnummerGeldig(clientnummer));

        // Wanneer: Een clientnummer is gegenereeerd en het formaat is geldig,
        // Dan:     Geeft de validatie 'true' terug.
        clientnummer = "200523-0123";
        assertTrue(isClientnummerGeldig(clientnummer));
    }

    @Test
    public void getNieuwClientnummerTest() {
        // Wanneer: Een nieuw klantnummer wordt aangevraagd,
        // En:      Er is geen client op dezelfde dag aangemeld
        // Dan:     Voldoet het klantnummer aan het juiste formaat en is nummer 0001 toegevoegd.

        // Wanneer: Een nieuw klantnummer wordt aangevraagd,
        // En:      Er is een client op dezelfde dag aangemeld
        // Dan:     Wordt de huidige datum omgevormd tot de juiste reeks van zes cijfers, wordt het koppelteken
        //          toegevoegd en het hoogste uitgegeven client nummer van die dag met één verhoogd en aan het
        //          clientnummer toegevoegd. (0002).
    }


    @Test
    public void clientNieuwID() {

        // Wanneer: Een (nieuwe) cliënt wordt geinitieerd,
        // Dan:     Wordt er een nieuwe identiteit (UUID) toegekend als tekenreeks.
        Client client = new Client();
        assertEquals(36, client.getId().length());
    }


    @Test
    public void testCRUD() {

        // 1. Nieuwe client aan en specifieer de attributen

        // Wanneer: Een nieuwe client wordt aangemaakt en één van de verplichte attributen is niet gespecificeerd.
        // Dan:     Wordt een 'DataIntegrityViolationException' gegenereerd.
        //
        Client clientPuk = Client.builder()
                .id(UUID.randomUUID().toString())
                .familienaam("Petteflat")
                .voorvoegsels("van de")
                .voorletters("Puk")
                .build();
        clientPuk.setClientnummer("250523-0001");

        clientPuk = clientRepository.save(clientPuk);

        String id = clientPuk.getId();
        assertNotNull(id);
        assertEquals(36, clientPuk.getId().length());

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
