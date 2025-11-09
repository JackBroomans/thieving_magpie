package com.jabrowa.backend.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "client", schema = "annoying_raven")
public class Client extends Person {

    public Client() {
        super();
    }
}
