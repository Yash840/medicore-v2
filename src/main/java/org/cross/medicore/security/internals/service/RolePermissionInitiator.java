package org.cross.medicore.security.internals.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cross.medicore.security.internals.constants.PermissionName;
import org.cross.medicore.security.internals.constants.RoleName;
import org.cross.medicore.security.internals.entities.Permission;
import org.cross.medicore.security.internals.entities.Role;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class RolePermissionInitiator implements ApplicationRunner {
    private final PermissionService permissionService;
    private final RoleService roleService;

    private void initiatePermissions(){
        List<PermissionName> permissionNameList = List.of(PermissionName.values());
        permissionService.addPermissions(permissionNameList);
        log.info("initiatePermissions: initiated permissions: {}", permissionNameList);
    }

    private void initiateRoles(){
        //Creating Role For Patient
        List<PermissionName> patientPermissionNames = getPatientInfo();

        List<Permission> patientPermissions = permissionService.getPermissions(patientPermissionNames);

        Role patientRole = roleService.addRole(RoleName.ROLE_PATIENT, patientPermissions);
        log.info("initiateRole: initiated role: {}", patientRole);

        List<PermissionName> providerPermissionNames = getReadPatientInfo();

        List<Permission> providerPermissions = permissionService.getPermissions(providerPermissionNames);

        Role providerRole = roleService.addRole(RoleName.ROLE_PROVIDER, providerPermissions);
        log.info("initiateRole: initiated role: {}", providerRole);
    }

    private static List<PermissionName> getPatientInfo() {
        return List.of(
                PermissionName.READ_PATIENT_INFO,
                PermissionName.READ_MEDICAL_DOCS,
                PermissionName.MANAGE_APPOINTMENT
        );
    }

    private static List<PermissionName> getReadPatientInfo() {
        return List.of(
                PermissionName.READ_PATIENT_INFO,
                PermissionName.READ_MEDICAL_DOCS,
                PermissionName.MANAGE_APPOINTMENT,
                PermissionName.MANAGE_ENCOUNTER,
                PermissionName.READ_PROVIDER_INFO
        );
    }

    private void initiateRolesAndPermissions(){
        initiatePermissions();
        initiateRoles();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        initiateRolesAndPermissions();
    }
}
