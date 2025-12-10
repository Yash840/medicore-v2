package org.cross.medicore.security.internals.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.cross.medicore.security.internals.constants.PermissionName;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private PermissionName name;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role){
        if(!Objects.isNull(role))
            roles.add(role);
    }

    public Permission(PermissionName name) {
        this.name = name;
    }

    public static Permission of(PermissionName name){
        return new Permission(name);
    }

    public static Permission of(PermissionName name, List<Role> roles){
        Permission permission =  Permission.of(name);
        roles.forEach(permission::addRole);

        return permission;
    }

    // TODO : Add toGrantedAuthority Method.
}
