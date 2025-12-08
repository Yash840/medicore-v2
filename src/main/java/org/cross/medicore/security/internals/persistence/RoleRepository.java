package org.cross.medicore.security.internals.persistence;

import org.cross.medicore.security.internals.constants.RoleName;
import org.cross.medicore.security.internals.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
