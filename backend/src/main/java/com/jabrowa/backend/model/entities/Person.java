package com.jabrowa.backend.model.entities;

import com.jabrowa.backend.model.enums.Gender;
import com.jabrowa.backend.model.enums.PreferredNameUses;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

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

    /**
     * <strong>toPrettyString(<i></i>)</strong> (method)<br><br>
     * Assembles the person's object attributes from current instance and converts them into an easy readable format.
     */
    public String toPrettyString() {
        return "Class: PreferredNameUses\n" +
                "\tId: " + this.id.toString() +
                "\tFamily name: " + this.familyName == null  ? "-" : this.familyName +
                "\tPrefix(es) (name): " + this.prefixesFamilyName == null ? "-" : this.prefixesFamilyName +
                "\tGiven name: " + this.givenName == null ? "-" : this.givenName +
                "\tInitials: " + this.surnames == null ? "-" : this.surnames +
                "\tNick name: " + this.nickName == null ? "-" : this.nickName +
                "\tPrefix(es) (titles): " + this.namePrefixes == null ? "-" : this.namePrefixes +
                "\tSuffix(es): " + this.nameSuffixes == null ? "-" : this.nameSuffixes +
                "\tName use: " + this.preferredNameUse.getDisplay();
    }
}
