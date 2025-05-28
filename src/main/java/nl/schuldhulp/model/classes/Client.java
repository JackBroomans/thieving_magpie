package nl.schuldhulp.model.classes;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


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

    @Override
    public String toString() {
        return "Client:\n\t" +
            "Id: " + this.id + "\n\t" +
            "Familienaam: " + (this.familienaam == null ? "" : this.familienaam) + "\n\t" +
            "Voorvoegsels: " + (this.voorvoegsels == null ? "" : this.voorvoegsels) + "\n\t" +
            "Voorletters: " + (this.voorletters == null ? "" : this.voorletters) + "\n\t" +
            "Aanhef: " + (this.aanhef == null ? "" : this.aanhef) + "\n\n";
    }
}
