package org.cross.medicore.security.internals.service;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.security.internals.constants.RoleName;
import org.cross.medicore.security.internals.entities.Permission;
import org.cross.medicore.security.internals.entities.Role;
import org.cross.medicore.security.internals.persistence.RoleRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class RoleService {
    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Role getRole(RoleName roleName){
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("unknown role: " + roleName.toString()));
    }

    // If role already exists update it otherwise insert new one.
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Role addRole(RoleName roleName, List<Permission> permissions){
        try {
            Role existingRole = getRole(roleName);
            //Role already exists, add extra permissions and save
            permissions.forEach(existingRole::addPermission);
            return roleRepository.save(existingRole);
        } catch (Exception e) {
            //Create new one
            Role role = Role.of(roleName, permissions);
            return roleRepository.save(role);
        }
    }
}
