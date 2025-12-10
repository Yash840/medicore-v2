package org.cross.medicore.security.internals.service;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.security.internals.constants.PermissionName;
import org.cross.medicore.security.internals.entities.Permission;
import org.cross.medicore.security.internals.persistence.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class PermissionService {
    private final PermissionRepository permissionRepository;

    public void addPermission(PermissionName name){
        permissionRepository.save(Permission.of(name));
    }

    public void addPermissions(List<PermissionName> permissionNames){
        List<Permission> permissions = new ArrayList<>();

        permissionNames.forEach(permissionName -> {
            permissions.add(Permission.of(permissionName));
        });

        permissionRepository.saveAll(permissions);
    }


    public Permission getPermission(PermissionName permissionName){
        return permissionRepository.findByName(permissionName)
                .orElseThrow(() -> new RuntimeException("unknown permission"));
    }

    public List<Permission> getPermissions(List<PermissionName> permissionNames){
        return permissionRepository.findAllByNameIn(permissionNames);
    }
}
