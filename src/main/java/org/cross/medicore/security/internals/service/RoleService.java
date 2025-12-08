package org.cross.medicore.security.internals.service;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.security.internals.constants.RoleName;
import org.cross.medicore.security.internals.entities.Permission;
import org.cross.medicore.security.internals.entities.Role;
import org.cross.medicore.security.internals.persistence.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class RoleService {
    private final RoleRepository roleRepository;

    public Role getRole(RoleName roleName){
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("unknown role"));
    }

    // If role already exists update it otherwise insert new one.
    public Role createRole(RoleName roleName, List<Permission> permissions){
        Role existingRole = getRole(roleName);

        //Role already exists, add extra permissions and save
        if(!Objects.isNull(existingRole)){
            permissions.forEach(existingRole::addPermission);

            return roleRepository.save(existingRole);
        }

        //Create new one
        Role role = Role.of(roleName, permissions);
        return roleRepository.save(role);
    }
}
