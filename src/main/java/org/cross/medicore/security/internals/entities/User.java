package org.cross.medicore.security.internals.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, updatable = false, unique = true)
    private String username;

    @Column(name = "hashed_password", nullable = false)
    private String hashedPassword;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    @Setter
    private Role role;

    @Column(name = "created_at",nullable = false, updatable = false)
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDate updatedAt;

    public User(String username, String hashedPassword, Role role) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.role = role;
    }
}
