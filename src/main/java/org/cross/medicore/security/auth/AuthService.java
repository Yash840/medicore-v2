package org.cross.medicore.security.auth;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.security.api.UserApi;
import org.cross.medicore.security.api.UserSecurityApi;
import org.cross.medicore.security.internals.entities.User;
import org.cross.medicore.security.jwt.JwtService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserApi userApi;

    public String login(String username, String password){
        boolean isValidUser = userApi.validateUser(username, password);

        if(isValidUser){
            return jwtService.generateToken(username);
        }

        throw new IllegalStateException("invalid user details");
    }
}
