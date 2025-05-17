package nl.schuldhulp.model.enums;

import java.util.Arrays;

/**
 * <strong>StatusBerichtVanBetaling</strong><br>
 * Enumeratie waarin de mogelijke statussen waarin een "<i>Bericht van Betaling"</i> kan verkeren zijn opgenomen.
 * De enumeratie kent één aanvullend attribuut "<i>volgorde</i>", waarmee de afgedwongen volgorde statussen binnen het
 * proces wordt aangegeven.<br>
 * Er is en methode "<i>getStandaardStatus()</i>" waarmee de initiële status kan worden opgevraagd.
 */
public enum StatusBerichtVanBetaling {
    AANGEMAAKT(1),
    AANGEBODEN(2),
    GELEZEN(3),
    GEARCHIVEERD(4);

    private final int volgorde;
    private static String NIET_GEVONDEN_STATUS_VAN_BETALING = "Status van betaling niet gevonden.";

    StatusBerichtVanBetaling (int volgorde) {
        this.volgorde = volgorde;
    }

    public int getVolgorde() {
        return volgorde;
    }

    /**
     * <strong>getStandaardStatus()</strong><br>
     * De methode selecteerd een "<i>Bericht van betaling"</i> op basis van het volgorde nummer.
     * @return De gevraagde "<i>Status</i> van betaling".
     * @throws IllegalStateException wanneer de status niet op basis van het volgordenummer kan worden gevonden.
     */
    public StatusBerichtVanBetaling fromVolgorde(int volgorde) {
        return Arrays.stream(values())
                .filter(status -> status.getVolgorde() == volgorde)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(NIET_GEVONDEN_STATUS_VAN_BETALING));
    }
}
