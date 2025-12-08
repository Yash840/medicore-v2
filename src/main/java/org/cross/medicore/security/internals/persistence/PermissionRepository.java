package org.cross.medicore.security.internals.persistence;

import org.cross.medicore.security.internals.constants.PermissionName;
import org.cross.medicore.security.internals.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(PermissionName name);

}
