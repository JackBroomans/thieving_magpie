package nl.schuldhulp.functies;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class clientFuncties {

        // Basisvorm: jjmmdd-nnnn
        private static final Pattern FORMAT_PATTERN = Pattern.compile("^\\d{6}-\\d{4}$");
        private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

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

}
