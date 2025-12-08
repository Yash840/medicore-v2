package org.cross.medicore.security.internals.mapper;

import org.cross.medicore.security.api.dto.UserDetailsDto;
import org.cross.medicore.security.internals.entities.Permission;
import org.cross.medicore.security.internals.entities.Role;
import org.cross.medicore.security.internals.entities.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDetailsDto toUserDetailsDto(User user){
        Role role = user.getRole();
        String roleName = role.getName().toString();
        Set<String> permissions = role.getPermissions().stream()
                .map((permission -> permission.getName().toString()))
                .collect(Collectors.toSet());

        return new UserDetailsDto(
                user.getId(),
                user.getUsername(),
                roleName,
                permissions,
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
