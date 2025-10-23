package com.jabrowa.backend.model.entities;

import jakarta.persistence.Column;

import java.time.LocalDate;

// todo: 23-10-2025 Bring local project under GitHub version control as the new 'thieving_magpie' repository.
// todo: 26-10-2025 Investigate the required attributes and structures to define and persist the identity data of a person.
// todo: 26-10-2025 Add the required attributes (fields) to the entity class 'model.entities.Identity'
// todo: 26-10-2025 Create Flyway migration to add the corresponding table 'thieving.identity' to the database 'progres' for the entity class 'model.entities.Identity'.
// todo: 26-10-2025 Add method 'toPrettyString()' to entity class 'model.entities.Identity' to present the current 'Identity' instance in a easy readable manner.
// todo: 26-10-2025 Add an enumerator 'NameFormats' in 'model.enums' to define the possible formats in which a name can be assembled from the name attributes.
// todo: 26-10-2025 Add the Javadoc for the enumerator 'NameFormats' in de directory 'model.enums'.
// todo: 26-10-2025 Create a method 'selectDefault()' to search and select the enumerated item which is set as default in the enumerator 'model.enums.NameFormats'
// todo: 26-10-2025 Implement a methode 'toPrettyString()' in the enumerator 'model.enumerators.Nameformats()' whick constructs the current instance in a easy readable format.
// todo: 26-10-2025 Implement the method 'toNameString(NameFormats.<chosen format>)' in the entity class 'model.entities.Identity', to assemble the name according the chosen format.
// todo: 26-10-2025 Create the Javadocs for the entity class 'model.entities.Identity' and its methods; 'toNameString()' and 'toPrettyString()'.
// todo: 27-10-2025 Unit test the functionality of the Lombok Getters and Setters in the entity class 'model.entities.Identity'.
// todo: 27-10-2025 Unit test the functionality of the method 'toNameString(NameFormats.<chosen format>)' in the entity class 'model.entities.Identity'.
// todo: 27-10-2025 Check the outcome of the method 'toPrettyString()' in the entity class 'model.entities.Identity'.
// todo: 27-10-2025 Unit test method '' in enumerator ''
public class Identity {

    @Column(name= "")
    private LocalDate dateOfBirth;
    @Column(name= "")
    private int age;
    private String placeOfBirth;
    @Column(name= "")
    private String countryOfBirth;

}
