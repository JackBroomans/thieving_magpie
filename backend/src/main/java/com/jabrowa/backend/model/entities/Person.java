package com.jabrowa.backend.model.entities;

import com.jabrowa.backend.model.enums.Gender;
import com.jabrowa.backend.model.enums.PreferredNameUses;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "person")
/**
 * <strong>Person</strong> - abstract class<br><br>
 * This class serves as a base class for all person-related entities in the system. This means that any entity representing a person
 * should inherit from this class to ensure consistency and reusability of common attributes and behaviors associated with a person.
 * The properties of this class are:
 * <ul>
 *      <li>id - Unique identifier for the person (UUID)</li>
 *      <li>familyName - The family name (surname) of the person</li>
 *      <li>prefixesFamilyName - Any prefixes associated with the family name (e.g., "van", "de")</li>
 *      <li>givenName - The given name (first name) of the person</li>
 *      <li>surnames - The initials of the person</li>
 *      <li>nickName - The nickname of the person</li>
 *      <li>namePrefixes - Any prefixes associated with the person's name (e.g., titles like "Dr.", "Mr.,Msc, Dr")</li>
 *      <li>nameSuffixes - Any suffixes associated with the person's name (e.g., "Jr.", "Sr..")</li>
 *      <li>preferredNameUse - The preferred way to use the person's name, represented by the PreferredNameUses enum</li>
 *      <li>dateOfBirth - The date of birth of the person</li>
 *      <li>age - The age of the person, calculated from the date of birth</li>
 *      <li>placeOfBirth - The place where the person was born</li>
 *      <li>countryOfBirth - The country where the person was born</li>
 *      <li>gender - The gender of the person, represented by the Gender enum</li>
 *      <li>createdAt - The timestamp when the person entity was created</li>
 *      <li>updatedAt - The timestamp when the person entity was last updated</li>
 * </ul>
 * This class is annotated with JPA annotations to map it to a database table named "person".
 */
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name= "family_name",  nullable = false, length = 127)
    private String familyName;
    @Column(name= "prefix_family_name", length = 63)
    private String prefixesFamilyName;
    @Column(name= "given_name", nullable = false, length = 127)
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
        return "Class: " + this.getClass().getName() + "\n" +
                "\tId: " + (this.id != null ? this.id.toString() : "-") + "\n" +
                "\tFamily name: " + (this.familyName == null  ? "-" : this.familyName) + "\n" +
                "\tPrefix(es) (name): " + (this.prefixesFamilyName == null ? "-" : this.prefixesFamilyName) + "\n" +
                "\tGiven name: " + (this.givenName == null ? "-" : this.givenName) + "\n" +
                "\tInitials: " + (this.surnames != null ? "-" : this.surnames) + "\n" +
                "\tNick name: " + (this.nickName != null ? this.nickName : "-") + "\n" +
                "\tPrefix(es) (titles): " + (this.namePrefixes != null ? this.namePrefixes : "-") + "\n" +
                "\tSuffix(es): " + (this.nameSuffixes != null ? this.nameSuffixes : "-") + "\n" +
                "\tName use: " + (this.preferredNameUse != null ? this.preferredNameUse.getDisplay() : "-");
    }
}
