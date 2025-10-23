package com.jabrowa.backend.model.entities;

import com.jabrowa.backend.model.enums.Gender;
import com.jabrowa.backend.model.enums.PreferredNameUses;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

// todo: 23-10-2025 Unit test the Lombok Getters of the attributes of entity class 'backend.model.entities.Person'
// todo: 24-10-2025 Associate attributes of entity class 'backend.model.entities.Person' with the corresponding database table 'postgres.thieving.person' columns.
// todo: 23-10-2025 Implement a method 'toPrettyString()' in entity class 'backend.model.entities.Person' to show the properties of the current enumerated value in an easy readable manner.
// todo: 23-10-2025 Add Javadoc for entity class 'backend.model.entities.Person'.
// todo: 25-10-2025 Document mapping between entity class 'backend.model.entities.Person' and database table 'postgres.thieving.person'.
@Getter
@Entity
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name= "family_name",  nullable = false, length = 127)
    private String familyName;
    @Column(name= "prefix_family_name", length = 63)
    private String prefixesFamilyName;
    @Column(name= "given_name", length = 127)
    private String givenName;
    @Column(nullable = false)
    private String surnames;
    @Column(name= "")
    private String nickName;
    @Column(name= "")
    private String namePrefixes;
    @Column(name= "")
    private String nameSuffixes;
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private PreferredNameUses preferredNameUse;

    @Column(name= "")
    private LocalDate dateOfBirth;
    @Column(name= "")
    private int age;
    private String placeOfBirth;
    @Column(name= "")
    private String countryOfBirth;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();
    private Instant updatedAt;

}
