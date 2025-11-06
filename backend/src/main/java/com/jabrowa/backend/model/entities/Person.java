package com.jabrowa.backend.model.entities;

import com.jabrowa.backend.model.enums.Gender;
import com.jabrowa.backend.model.enums.PreferredNameUses;
import com.jabrowa.backend.utilities.EnumUtilities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

import static com.jabrowa.backend.utilities.EnumUtilities.selectDefault;

/**
 * <strong>Person</strong> - abstract class<br><br>
 * The abstract description of a Person.
 * Each specific type of person entity (Client, Practitioner, ClientRelation etc.) inherits this description.
 * The properties of this class are:
 * <ul>
 *      <li><strong>id</strong> - Unique automatic created and immutable identifier of the person (UUID)</li>
 *      <li><strong>familyName</strong> - The family name of the person. This might be the name of the partner</li>
 *      <li><strong>prefixesFamilyName</strong>- Any common prefixes belonging to the family name (e.g., "van", "de"</li>
 *      <li><strong>givenName</strong> - The given name (family name given at birth aka maiden name)</li>
 *      <li><strong>initials</strong> - The initials of the person. The fully specified forenames are not relevant
 *      for the abstract class.</li>
 *      <li><strong>nickName</strong> - The nickname of the person</li>
 *      <li><strong>prefixTitles</strong> - Applicable academic or noble titles used as prefix (e.g. "Dr.", "Mr.",
 *      "Baron")</li>
 *      <li><strong>suffixTitles</strong> - Applicable academic or other suffixes (e.g., "Msc", "Bsc", "Jr.")</li>
 *      <li><strong>preferredNameUse</strong> - The preferred way the person want to use the name.</li>
 *      <li><strong>gender</strong> - The gender of the person.</li>
 *      <li><strong>createdAt</strong> - Date and time of creation of the record.</li>
 *      <li><strong>updatedAt</strong> - Date and time of the last update of the record.</li>
 * </ul>
 * This class is annotated with JPA annotations to map it to a database table named "person".
 */
@Getter
@Setter
@Entity
@Table(name = "person")
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;
    @Column(name= "family_name", nullable = false, length = 127)
    private String familyName;
    @Column(name= "prefixes_family_name", length = 63)
    private String prefixesFamilyName;
    @Column(name= "givenname", nullable = false, length = 127)
    private String givenName;
    @Column(nullable = false, length = 31)
    private String nickname;
    @Column(nullable = false, length = 31)
    private String initials;
    @Column(name= "prefix_titles", length = 63)
    private String prefixTitles;
    @Column(name= "suffix_titles", length = 63)
    private String suffixTitles;
    @Column(name= "preferred_name_use", nullable = false)
    @Enumerated(EnumType.STRING)
    private PreferredNameUses preferredNameUse;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public Person() {
        this.preferredNameUse = selectDefault(PreferredNameUses.class);
        this.gender = selectDefault(Gender.class);
    }

    private boolean validated() {

        if (familyName == null || familyName.isEmpty() ||
                givenName == null || givenName.isEmpty()) {
            return false;
        }

        return true;
    }

//            *      <li>dateOfBirth - The date of birth of the person</li>
//            *      <li>age - The age of the person, calculated from the date of birth</li>
//            *      <li>placeOfBirth - The place where the person was born</li>
//            *      <li>countryOfBirth - The country where the person was born</li>
//    @Column(name= "")
//    private LocalDate dateOfBirth;
//    @Column(name= "")
//    private int age;
//    private String placeOfBirth;
//    @Column(name= "")
//    private String countryOfBirth;
//
//    @Column(nullable = false)
//    @Enumerated(EnumType.ORDINAL)
//    private Gender gender;


    /**
     * <strong>toPrettyString(<i></i>)</strong> (method)<br><br>
     * Constructs an easy readable formatted string from the current instance of the 'Person'.
     * @return String containing the easy readable presentation of the person.
     */
    public String toNiceString() {
        return "Class: " + this.getClass().getSimpleName() + "\n" +
                "\tId:                       " + (this.id != null ? this.id.toString() : "-") + "\n" +
                "\tFamilienaam:              " + (this.familyName == null  ? "-" : this.familyName) + "\n" +
                "\tVoorvoegsels:             " +
                    (this.prefixesFamilyName == null ? "-" : this.getPrefixesFamilyName()) + "\n" +
                "\tGeboortenaam:             " + (this.givenName == null ? "-" : this.getGivenName()) + "\n" +
                "\tInitialen:                " + (this.initials != null ? "-" : this.getInitials()) + "\n" +
                "\tRoepnaamn:                " + (this.nickname != null ? this.getNickname() : "-") + "\n" +
                "\tTitel(s) voorvoegsels:    " + (this.prefixTitles != null ? this.getPrefixTitles() : "-") + "\n" +
                "\tTitel(s) achtervoegsels:  " + (this.suffixTitles != null ? this.getSuffixTitles() : "-") + "\n" +
                "\tNaam gebruikt:            " +
                    (this.preferredNameUse != null ? this.getPreferredNameUse().getCode() + " - " +
                            this.getPreferredNameUse().getDisplay() : "-") + "\n" +
                "\tGeslacht:                 " +
                    (this.gender != null ? this.getGender().getCode() + " - " + this.getGender().getDisplay() : "-");
    }
}
