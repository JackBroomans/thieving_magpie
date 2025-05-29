package nl.schuldhulp.model.classes;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;
import java.util.regex.Pattern;


@Entity
@Table(
    name = "client",
    indexes = {
        @Index(name = "idx_client_clientnummer", columnList = "clientnummer"),
        @Index(name = "idx_client_familienaam", columnList = "familienaam")
    }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {

    // Basisvorm: jjmmdd-nnnn
    private static final Pattern FORMAT_PATTERN = Pattern.compile("^\\d{6}-\\d{4}$");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Id
    @Column(length = 36, nullable = false)
    private String id = UUID.randomUUID().toString();

    @Column(length = 11, nullable = false)
    private String clientnummer;

    @Column(length = 127, nullable = false)
    private String familienaam;

    @Column(length = 31)
    private String voorvoegsels;

    @Column(length = 31)
    private String voorletters;

    @Column(length = 31)
    private String aanhef;

   /**
     * <strong>isClientnummerGeldig<i>(String)</i></strong>
     * Valideert een cliëntnummer of dit aan de eisen voldoet. Er wordt op de volgende punten gevalideerd:
     * <ul>
     *      <li>Het formaat voldoet, met eenreguliere expressie (jjmmdd-nnnn).</li>
     *      <li>Het datumdeel is gebaseerd op een bestaande datum.</li>
     * </ul>
     * @param clientnummer Tekenreeks van het te controleren cliëntnummer.
     * @return <i>true</i> wanneer hel cliëntnummer geldig is, anders  wordt <i>false</i> geretourneerd.
     */
    public static boolean isClientnummerGeldig(String clientnummer) {
        if (clientnummer == null || !FORMAT_PATTERN.matcher(clientnummer).matches()) {
            return false;
        }

        String datumDeel = "20" + clientnummer.substring(0, 6);

        try {
            LocalDate.parse(datumDeel, DATE_FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Client:\n\t" +
            "Id = " + this.id + "\n\t" +
            "Clientnummer = " + (this.clientnummer == null ? "" : this.clientnummer) + "\n\t" +
            "Familienaam = " + (this.familienaam == null ? "" : this.familienaam) + "\n\t" +
            "Voorvoegsels = " + (this.voorvoegsels == null ? "" : this.voorvoegsels) + "\n\t" +
            "Voorletters = " + (this.voorletters == null ? "" : this.voorletters) + "\n\t" +
            "Aanhef = " + (this.aanhef == null ? "" : this.aanhef) + "\n\n";
    }
}
