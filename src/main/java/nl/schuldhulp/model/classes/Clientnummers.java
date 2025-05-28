package nl.schuldhulp.model.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientnummers")
@Data
@Getter
@Setter
public class Clientnummers {

    @Id
    @Column(name = "datum_code", length = 6, nullable = false)
    private String datumCode;

    @Column(name = "laatste_volgdeel")
    private int volgnummer;

    @Override
    public String toString() {
        return "Clientnummer:\n\t" +
            "Datumdeel: " + (this.datumCode == null ? "" : this.datumCode) + "\n\t" +
            "Volgnummer (int): " + this.volgnummer + "\n\n";
    }
}
