package org.cross.medicore.security.api.dto;

import org.cross.medicore.security.internals.entities.User;

/**
 * Projection for {@link User}
 */
public interface DeletedUserInfo {
    Long getId();

    String getUsername();
}