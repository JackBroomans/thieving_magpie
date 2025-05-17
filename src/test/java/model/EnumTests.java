package model;

import nl.schuldhulp.model.enums.StatusBerichtVanBetaling;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnumTests {

    @Test
    public void StatusVanBetalingTest() {

        // Wanneer: Een bepaalde status actief is.
        // Dan:     Dient het volgnummer van die status overeen te komen met het 'opgevraagde volgnummer'
        //          van die status.
        StatusBerichtVanBetaling status = StatusBerichtVanBetaling.AANGEBODEN;
        assertEquals(2, status.getVolgorde(),1);

        // Wanneer: Een status wordt opgevraagd op basis van het volgnummer.
        // Dan:     Dient de betreffende status te worden geretourneerd.
        status = status.fromVolgorde(3);
        assertEquals(3, status.getVolgorde());
        assertEquals("GELEZEN", status.name());

        // Wanneer: Een bepaalde status actief is, en de daarop volgende status wordt opgevraagd en geactiveerd,
        // En:      De nieuwe status een bestaande dus geldige status is.
        // Dan:     Dient de volgende status te worden geretourneerd.
        status = status.fromVolgorde(status.getVolgorde() + 1);
        assertEquals("GEARCHIVEERD", status.name());

        // Wanneer: Een bepaalde status actief is, en de daarop volgende status wordt opgevraagd en geactiveerd,
        // En:      De nieuwe status niet bestaat, dus ongeldig is.
        // Dan:     Treedt er een 'IllegalStateException' op.
        assertThrows(IllegalStateException.class, () -> {
            StatusBerichtVanBetaling testStatus = StatusBerichtVanBetaling.GEARCHIVEERD;
            testStatus = testStatus.fromVolgorde(testStatus.getVolgorde() - 5);
        });
        assertThrows(IllegalStateException.class, () -> {
            StatusBerichtVanBetaling testStatus = StatusBerichtVanBetaling.GEARCHIVEERD;
            testStatus = testStatus.fromVolgorde(testStatus.getVolgorde() + 5);
        });
    }
}
