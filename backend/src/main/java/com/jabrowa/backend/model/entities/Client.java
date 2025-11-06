package com.jabrowa.backend.model.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;

@Entity
public class Client extends Person {

    public Client() {
        super();
    }
}
