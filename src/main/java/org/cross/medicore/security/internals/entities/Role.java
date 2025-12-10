package org.cross.medicore.security.internals.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.cross.medicore.security.internals.constants.RoleName;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    @ManyToMany()
    @JoinTable(name = "role_permission",
    joinColumns = @JoinColumn(name = "role_id"),
    inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();

    public void addUser(User user){
        if(!Objects.isNull(user))
            this.users.add(user);
    }

    public void addPermission(Permission permission){
        if(!Objects.isNull(permission))
            this.permissions.add(permission);
    }

    public Role(RoleName name) {
        this.name = name;
    }

    public static Role of(RoleName roleName, List<Permission> permissions){
        Role role = new Role(roleName);

        permissions.forEach(role::addPermission);

        return role;
    }
}
