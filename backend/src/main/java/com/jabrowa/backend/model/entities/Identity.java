package com.jabrowa.backend.model.entities;

import jakarta.persistence.Column;

import java.time.LocalDate;

//
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
