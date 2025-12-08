package org.cross.medicore.security.internals.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.cross.medicore.security.internals.constants.PermissionName;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@RequiredArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private final PermissionName name;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role){
        if(!Objects.isNull(role))
            roles.add(role);
    }

    // TODO : Add toGrantedAuthority Method.
}
