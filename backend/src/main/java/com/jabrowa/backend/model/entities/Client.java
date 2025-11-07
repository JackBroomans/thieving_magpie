package com.jabrowa.backend.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "person", schema = "annoying_raven")
public class Client extends Person {

    public Client() {
        super();
    }
}
