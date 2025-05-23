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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Client {

    @Id
    @Column(length = 36, nullable = false)
    private String id = UUID.randomUUID().toString();

    @Column(length = 14, nullable = false)
    private String clientnummer;

    @Column(length = 127, nullable = false)
    private String familienaam;

    @Column(length = 31)
    private String voorvoegsels;

    @Column(length = 31)
    private String voorletters;

}
