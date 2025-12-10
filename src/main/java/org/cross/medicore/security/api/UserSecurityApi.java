package org.cross.medicore.security.api;

import org.cross.medicore.security.internals.entities.User;

public interface UserSecurityApi {
    User getUser(String username);
    User getUser(long userId);
}
