package model;

import nl.schuldhulp.SchuldhulpApplication;
import nl.schuldhulp.model.classes.Client;
import nl.schuldhulp.model.classes.Clientnummers;
import nl.schuldhulp.model.repository.ClientnummersRepository;
import nl.schuldhulp.services.ClientnummerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = SchuldhulpApplication.class)
@Transactional
@ComponentScan(basePackages = "nl.schuldhulp.model.repository")
public class ClientnummerTest {

    private final String VANDAAG = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));

    @Mock
    private ClientnummersRepository repository;
    @InjectMocks
    private ClientnummerService clientnummerService;

    @Test
    public void isClientnummerGeldigTest() {
        /*
         Wanneer:   Een clientnummer is gegenereerd en het formaat is ongeldig,
         Dan:       Geeft de validatie 'false' terug.
         Indien:    Het datumdeel ongedig is
        */
        String clientnummer = "25040-0000";
        assertFalse(Client.isClientnummerGeldig(clientnummer));
        /*
         Of:     Het koppelteken ontbreekt
        */
        clientnummer = "2505230116";
        assertFalse(Client.isClientnummerGeldig(clientnummer));
        /*
         Of:       Het volgnummer ongeldig is (te lang, te kort of geen getal)
        */
        clientnummer = "250523-01234";
        assertFalse(Client.isClientnummerGeldig(clientnummer));
        clientnummer = "250523-012";
        assertFalse(Client.isClientnummerGeldig(clientnummer));
        clientnummer = "250523-A012";
        assertFalse(Client.isClientnummerGeldig(clientnummer));
        /*
          Of:       Het datumdeel geen bestaande datum voorstelt
         */
        clientnummer = "251831-0011";
        assertFalse(Client.isClientnummerGeldig(clientnummer));

        /*
         Wanneer:   Een clientnummer is gegenereeerd en het formaat is geldig,
         Dan:       Geeft de validatie 'true' terug.
        */
        clientnummer = "200523-0123";
        assertTrue(Client.isClientnummerGeldig(clientnummer));
    }

    @Test
    public void getNieuwClientnummerTest() {
        String vandaag = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));

        // Indien de repository methode 'findById()' met de huidige datum in het datumdeel wordt aangeroepen, dan
        // wordt altijd een leeg Optional object geretourneerd. hiermee wordt gesimuleerd dat er nog geen
        // clientnummmers zijn uitgegeven 'vandaag'.
        Mockito.when(repository.findById(vandaag)).thenReturn(Optional.empty());

        ArgumentCaptor<Clientnummers> clientnummersArgumentCaptor = ArgumentCaptor.forClass(Clientnummers.class);

        /*
         Wanneer: Een nieuw klantnummer wordt aangevraagd,
         En:      Er is geen client op dezelfde dag aangemeld
         Dan:     Voldoet het klantnummer aan het juiste formaat en is nummer 0001 toegevoegd aan het datumdeel.
        */
        String gegenereerd = clientnummerService.getNieuwClientnummer();
        assertTrue(Client.isClientnummerGeldig(gegenereerd));
        assertEquals(vandaag.concat("-0001"), gegenereerd);

        Mockito.verify(repository).save(clientnummersArgumentCaptor.capture());
    }

    @Test
    void testGenereerClientnummerBestaandRecord() {
        /*
         Wanneer: Een nieuw klantnummer wordt aangevraagd,
         En:      Er is een client op dezelfde dag aangemeld
         Dan:     Wordt het hoogste volgnummer met 1 verhoogd.
        */
        Clientnummers bestaand = new Clientnummers();
        bestaand.setDatumCode(VANDAAG);
        bestaand.setVolgnummer(42);

        Mockito.when(repository.findById(VANDAAG)).thenReturn(Optional.of(bestaand));
        Mockito.when(repository.save(Mockito.any())).thenAnswer(i -> i.getArgument(0));

        // Act
        String gegenereerd = clientnummerService.getNieuwClientnummer();

        // Assert
        assertEquals(VANDAAG + "-0043", gegenereerd);
    }
}
