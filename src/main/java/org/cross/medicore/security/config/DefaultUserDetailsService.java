package org.cross.medicore.security.config;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.security.api.UserApi;
import org.cross.medicore.security.api.UserSecurityApi;
import org.cross.medicore.security.internals.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {
    private final UserSecurityApi userApi;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userApi.getUser(username);

        return DefaultUserDetails.from(user);
    }
}
