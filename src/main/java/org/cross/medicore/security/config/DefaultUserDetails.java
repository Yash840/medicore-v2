package org.cross.medicore.security.config;

import org.cross.medicore.security.internals.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultUserDetails implements UserDetails {
    private final String username;
    private final String hashedPassword;
    private final List<GrantedAuthority> authorities;

    private DefaultUserDetails(String username, String hashedPassword, List<GrantedAuthority> authorities) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.authorities = authorities;
    }

    public static DefaultUserDetails from(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        user.getRole().getPermissions()
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.toString())));

        return new DefaultUserDetails(
                user.getUsername(),
                user.getHashedPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return hashedPassword;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
