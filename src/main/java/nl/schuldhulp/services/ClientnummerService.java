package nl.schuldhulp.services;

import nl.schuldhulp.model.classes.Clientnummers;
import nl.schuldhulp.model.repository.ClientnummersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <strong>ClientnummerService</strong><br>
 * Geeft een nieuw uniek clientnummer uit door de volgende stappen uit te voeren:
 * <ol>
 *     <li>Bepaalt de huidige datum (jjmmdd).</li>
 *     <li>Haalt het record op voor die datum, of maakt het als het niet bestaat.</li>
 *     <li>Verhoogt het volgnummer.</li>
 *     <li>Maakt het eindresultaat: jjmmdd-nnnn.</li>
 * </ol>
 */
@Service
public class ClientnummerService {

    @Autowired
    private ClientnummersRepository repository;

    /**
     * <strong>getNieuwClientnummer()</strong><br>
     * Geeft een nieuw uniek cliëntmummer uit bestaande uit met een datumdeel in de vorm <i>jjmmdd</i> en een
     * volgnummer van vier cijfers te beginnen met 0001 voor de betreffende datum.
     * @return Retourneert het nieuwe clientnummer.
     */
    public String getNieuwClientnummer() {
        String datumCode = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));

        Clientnummers record = repository.findById(datumCode)
            .orElseGet(() -> {
                Clientnummers nieuwNummer = new Clientnummers();
                nieuwNummer.setDatumCode(datumCode);
                nieuwNummer.setVolgnummer(0);
                return nieuwNummer;
            });

        int nieuwVolgnummer = record.getVolgnummer() + 1;
        record.setVolgnummer(nieuwVolgnummer);
        repository.save(record);

        String volgnummerFormatted = String.format("%04d", nieuwVolgnummer);
        return datumCode + "-" + volgnummerFormatted;
    }
}
