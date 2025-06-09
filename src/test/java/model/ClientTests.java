package model;

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

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SpellCheckingInspection")
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
    private ClientRepository clientRepository;

    Client clientPuk = Client.builder()
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
         Wanneer:   De gegevens van de client compleet zijn, dus alle verplichte velden zijn (geldig) gespecificeerd,
         En:        De gegevens worden gepersisteerd,
         Dan:       Kunnen deze gegevens worden opgehaald in een nieuw object.
         */
        clientPuk = Client.builder()
            .clientnummer(clientnummerService.getNieuwClientnummer())
            .familienaam("Petteflat")
            .voorvoegsels("van de")
            .voorletters("Puk")
            .build();
        clientRepository.save(clientPuk);

        assertTrue(Client.isClientnummerGeldig(clientPuk.getClientnummer()));
        assertEquals(36, clientPuk.getId().length());
        assertNotNull(clientPuk.getFamilienaam());
        assertNotNull(clientPuk.getVoorletters());

        Client clientPukOpgehaald = clientRepository.findById(this.clientPuk.getId()).orElse(null);
        assertNotNull(clientPukOpgehaald);
        assertEquals("Petteflat", clientPukOpgehaald.getFamilienaam());

        /*
         Wanneer:   Gegevens in het opgehaalde object worden gewijzigd,
         En:        Deze gegevens worden gepersisteerd,
         En:        Deze gegevens opnieuw worden opgevraagd en het originele object,
         Dan:       Zijn het opgehaalde object en het opnieuw opgevraagde originele object gelijk aan elkaar.
         */
        clientPukOpgehaald.setFamilienaam("Kraanwagen");

        clientRepository.save(clientPukOpgehaald);

        this.clientPuk = clientRepository.findById(this.clientPuk.getId()).orElse(null);
        assertEquals("Kraanwagen", this.clientPuk.getFamilienaam());
        assertEquals("Puk", this.clientPuk.getVoorletters());
        assertEquals(clientPuk.getFamilienaam(), clientPukOpgehaald.getFamilienaam());

        /*
         Wanneer:   Een client uit de database wordt opgehaald op basis van het id,
         En:        De betreffende client is gevonden en aan een client-object toegekend,
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
