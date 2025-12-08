package org.cross.medicore.security.internals.service;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.security.internals.constants.PermissionName;
import org.cross.medicore.security.internals.entities.Permission;
import org.cross.medicore.security.internals.persistence.PermissionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PermissionService {
    private final PermissionRepository permissionRepository;

    public Permission getPermission(PermissionName permissionName){
        return permissionRepository.findByName(permissionName)
                .orElseThrow(() -> new RuntimeException("unknown permission"));
    }
}
