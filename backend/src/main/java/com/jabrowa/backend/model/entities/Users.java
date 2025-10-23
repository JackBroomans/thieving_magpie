package com.jabrowa.backend.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.UUID;

// todo: 23-10-2025 Add an enumerator 'NameFormats' in 'model.enums' to define the possible formats in which a name can be assembled from the name attributes.
// todo: 23-10-2025 Add the Javadoc for the enumerator 'NameFormats' in de directory 'model.enums'.
// todo: 23-10-2025 Create a method 'selectDefault()' to search and select the enumerated item which is set as default in the enumerator 'model.enums.NameFormats'
// todo: 23-10-2025 Implement a methode 'toPrettyString()' in the enumerator 'model.enumerators.Nameformats()' whick constructs the current instance in a easy readable format.

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false, length = 150)
    private String username;

    @Column(nullable = false)
    private String passwordHash; // BCrypt hashed

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private boolean accountNonLocked = true;

    @Column(nullable = false)
    private boolean credentialsNonExpired = true;

    // Whether the user has any 2FA configured
    @Column(nullable = false)
    private boolean twoFactorEnabled = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<UserRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TwoFactorCredential> twoFactorCredentials = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RecoveryCode> recoveryCodes = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TrustedDevice> trustedDevices = new HashSet<>();

    private Instant createdAt = Instant.now();

    // getters and setters omitted for brevity
}

}
