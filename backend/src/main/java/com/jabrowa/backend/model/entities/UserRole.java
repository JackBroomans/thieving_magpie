package com.jabrowa.backend.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// todo:

@Getter
@Setter
@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roleName; // e.g. ROLE_USER, ROLE_ADMIN

    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;

    // getters and setters omitted
}
