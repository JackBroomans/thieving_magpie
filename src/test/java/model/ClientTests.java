package model;

import lombok.Builder;
import nl.schuldhulp.SchuldhulpApplication;
import nl.schuldhulp.model.classes.Client;
import nl.schuldhulp.model.repository.ClientRepository;
import nl.schuldhulp.model.repository.ClientnummersRepository;
import nl.schuldhulp.services.ClientnummerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan
@SpringBootTest(classes = SchuldhulpApplication.class)
@Transactional
@ComponentScan(basePackages = "nl.schuldhulp.model.repository")
public class ClientTests {

    @Mock
    private ClientnummersRepository repository;
    @InjectMocks
    private ClientnummerService clientnummerService;

    @Autowired
    private ClientRepository clientRepository;;

    Client clientPuk = Client.builder()
            .id(UUID.randomUUID().toString())
            .familienaam("Petteflat")
            .voorvoegsels("van de")
            .voorletters("Puk")
            .build();

    @Test
    public void testRepositoryCRUD() {
        /*
         Wanneer:   Een nieuwe client wordt aangemaakt
         En:        Eén van de verplichte attributen is niet gespecificeerd.
         Dan:       Treedt een 'DataIntegrityViolationException' op.
        */
        assertThrows(DataIntegrityViolationException.class, () -> {
            clientPuk = clientRepository.save(clientPuk);
        });

        /*
         Wanneer:   De gegevens van de client compleet zijn, dus alle verplichte velden zijn gespecificeerd,
         Dan:       Worden de gegevens van de client in de database opgeslagen en weer uit de database worden opgehaald.
         */
        clientPuk.setClientnummer(clientnummerService.getNieuwClientnummer());

        assertTrue(Client.isClientnummerGeldig(clientPuk.getClientnummer()));
        assertEquals(36, clientPuk.getId().length());
        assertNotNull(clientPuk.getFamilienaam());
        assertNotNull(clientPuk.getVoorletters());

        clientRepository.save(clientPuk);

        Client clientPukOpgehaald = clientRepository.findById(this.clientPuk.getId()).orElse(null);
        assertNotNull(clientPukOpgehaald);
        assertEquals("Petteflat", clientPukOpgehaald.getFamilienaam());

        /*
         Wanneer:   Gegevens worden gewijzigd,
         En:        Worden opgeslagen,
         Dan:       Worden de gewijzigde gegevens opgelagen
         */
        clientPuk.setFamilienaam("Kraanwagen");

        clientRepository.save(clientPuk);

        this.clientPuk = clientRepository.findById(this.clientPuk.getId()).orElse(null);
        assertEquals("Kraanwagen", this.clientPuk.getFamilienaam());
        assertEquals("Puk", this.clientPuk.getVoorletters());

        /*
         Wanneer:   Een client uit de database wordt opgehaald op basis van het id,
         En:        De betreffende client is gevonden en aan een client-object toegevoegd,
         En:        De client wordt uit de database verwijderd op basis van dit object,
         En:        Vervolgens wordt de client opnieuw gezocht,
         Dan:       Treedt een 'JpaObjectRetrievalFailureException' op, omdat de client niet meer in de gegevensbank
                    aanwezig is.
         */
        this.clientPuk = clientRepository.findById(this.clientPuk.getId()).orElse(null);
        clientRepository.delete(this.clientPuk);
        assertThrows(JpaObjectRetrievalFailureException.class, () -> {
            clientRepository.getReferenceById(this.clientPuk.getId());
        });
    }
}
