package com.jabrowa.backend.model.entities;

import com.jabrowa.backend.model.enums.DateTimeFormats;
import com.jabrowa.backend.model.enums.Gender;
import com.jabrowa.backend.model.enums.PreferredNameUses;
import com.jabrowa.backend.model.interfaces.HasKeyValue;
import com.jabrowa.backend.utilities.EnumUtilities;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.jabrowa.backend.utilities.EnumUtilities.selectDefault;

/**
 * <strong>Person</strong> - abstract class<br><br>
 * The abstract description of a Person.
 * Each specific type of person entity (Client, Practitioner, ClientRelation etc.) inherits this description.
 * The properties of this class are:
 * <ul>
 *      <li><strong>id</strong> - Unique automatic created and immutable identifier of the person (UUID)</li>
 *      <li><strong>givenName</strong> - The given name (family name given at birth aka maiden name)</li>
 *      <li><strong>prefixesFamilyName</strong>- Any common prefixes belonging to the given name (e.g., "van", "de"</li>
 *      <li><strong>familyName</strong> - The family name of the person. This might be the name of the partner</li>
 *      <li><strong>prefixesFamilyName</strong>- Any common prefixes belonging to the family name (e.g., "van", "de"</li>
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
@MappedSuperclass
public abstract class Person {
    @Transient
    private Logger LOGGER = LoggerFactory.getLogger(Person.class);
    @Transient
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormats.DATE_TIME_WITH_SECONDS.getFormatter();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    @Column(name = "given_name", nullable = false, length = 127)
    private String givenName;
    @Column(name = "prefixes_given_name", length = 63)
    private String prefixesGivenName;
    @Column(name = "family_name", length = 127)
    private String familyName;
    @Column(name = "prefixes_family_name", length = 63)
    private String prefixesFamilyName;
    @Column(nullable = false, length = 31)
    private String initials;
    @Column(nullable = false, length = 31)
    private String nickname;
    @Column(name = "prefix_titles", length = 63)
    private String prefixTitles;
    @Column(name = "suffix_titles", length = 63)
    private String suffixTitles;
    @Column(name = "preferred_name_use", nullable = false)


    @Transient
    private PreferredNameUses preferredNameUse;
    @Basic(fetch = FetchType.LAZY)
    private short preferredNameUseKeyValue;

    @Transient
    private Gender gender;
    @Basic(fetch = FetchType.LAZY)
    private short genderKeyValue;

    /**
     * <strong>postLoad<i>()</i></strong><br><br>
     * Synchronizes the instance of the applicable enumerator, based on its key value which is fetched from
     * the database. This synchronization is automatically performed after a database round-trip where the enumerator's
     * key value is fetched.
     */
    @PostLoad
    public void postLoad() {
        this.gender = handleEnumGeneric(Gender.class, genderKeyValue, "gender");
        this.genderKeyValue = this.gender.getNumber();

        this.preferredNameUse = handleEnumGeneric(PreferredNameUses.class, preferredNameUseKeyValue, "preferredNameUse");
        this.preferredNameUseKeyValue = this.preferredNameUse.getNumber();
    }

    /**
     * <strong>handleEnumGeneric(<i>Enum, keyValue, fieldName/i>)</strong><br><br>
     * @param enumClass The enumerator class for which the key value and the (backend) used enumerator must be
     *                  synced right after the key value is loaded from the database.
     * @param keyValue  The fetched (from the database) key value of the enumerator to which it applies.
     * @param fieldName The name of the entity property (field) that is applied by the load operation.
     * @return An instances of the synchronized enumerator, with the fetched key value.
     */
    private <E extends Enum<E> & HasKeyValue> E handleEnumGeneric(
            Class<E> enumClass,
            int keyValue,
            String fieldName
    ) {
        return EnumUtilities.getByKeyValue(enumClass, (short) keyValue)
                .orElseGet(() -> {
                    E def = EnumUtilities.selectDefault(enumClass);
                    LOGGER.warn("Unknown {} key '{}' — using default '{}'", fieldName, keyValue, def);
                    return def;
                });
    }

    /**
     * <strong>prePersistGender()</strong><br><br>
     * Denormalizes the (transient) enumerator to its key-value before persisting, to make the database stores the
     * (compact_ key-value while the backend uses the enum.
     * <i>This method runs automatically before persist or update operations.</i>
     */
    @PrePersist
    @PreUpdate
    public void prePersistGender() {
        if (gender == null || genderKeyValue <= 0) {
            LOGGER.warn("Gender not specified or wrong key-value. Default Gender is applied.");
            this.gender = EnumUtilities.selectDefault(Gender.class);
        }
        this.genderKeyValue = this.getGender().getNumber();
    }

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt = Instant.now();

    public Person() {
        this.preferredNameUse = selectDefault(PreferredNameUses.class);
        this.gender = selectDefault(Gender.class);
    }

    /**
     * <strong>isValidated<i>()</i></strong><br><br>
     * Checks if the mandatory fields are specified.
     * @return a boolean indicating if all the mandatory fields are specified.
     */
    public boolean isValidated() {
        return givenName != null && !givenName.isEmpty() &&
                initials != null && !initials.isEmpty() &&
                updatedAt != null && !updatedAt.isAfter(Instant.now());
    }

    /**
     * <strong>toPrettyString(<i></i>)</strong> (method)<br><br>
     * Constructs an easy readable formatted string from the current instance of the 'Person'.
     * @return String containing the easy readable presentation of the person.
     */
    public String toNiceString() {
        return "Class: " + this.getClass().getSimpleName() + "\n" +
                "\tId:                         " + (this.id != null ? this.id.toString() : "-") + "\n" +
                "\tGeboortenaam:               " + (this.givenName != null ? this.getGivenName() : "-") + "\n" +
                "\tFamilienaam:                " + (this.familyName != null ? this.familyName : "-") + "\n" +
                "\tVoorvoegsels:               " +
                (this.prefixesFamilyName != null ? this.getPrefixesFamilyName() : "-") + "\n" +
                "\tInitialen:                  " + (this.initials != null ? this.getInitials() : "-") + "\n" +
                "\tRoepnaamn:                  " + (this.nickname != null ? this.getNickname() : "-") + "\n" +
                "\tTitel(s) voorvoegsels:      " + (this.prefixTitles != null ? this.getPrefixTitles() : "-") + "\n" +
                "\tTitel(s) achtervoegsels:    " + (this.suffixTitles != null ? this.getSuffixTitles() : "-") + "\n" +
                "\tNaam gebruikt:              " +
                (this.preferredNameUse != null ? this.getPreferredNameUse().getCode() + " - " +
                        this.getPreferredNameUse().getDisplay() : "-") + "\n" +
                "\tGeslacht:                   " +
                (this.gender != null ? this.getGender().getCode() + " - " + this.getGender().getDisplay() : "-") +
                "\n" +
                "\tTijdstip aanmaken:          " + (this.getCreatedAt() != null ?
                    DateTimeFormats.DATE_TIME_WITH_SECONDS.format(this.getCreatedAt()) : "-") + "\n" +
                "\tTijdstip laatste wijziging: " + (this.getUpdatedAt() != null ?
                    DateTimeFormats.DATE_TIME_WITH_SECONDS.format(this.getUpdatedAt()) : "-");
    }
}
