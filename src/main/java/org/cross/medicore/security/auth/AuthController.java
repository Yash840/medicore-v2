package org.cross.medicore.security.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.cross.medicore.security.api.dto.LoginSuccessDto;
import org.cross.medicore.security.api.dto.UserLoginRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginSuccessDto>
    loginUser(@RequestBody @NonNull UserLoginRequestDto dto, HttpServletResponse response){
        String authToken = authService.login(dto.username(), dto.password());
        Cookie cookie = new Cookie("authToken", authToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*60*24);

        response.addCookie(cookie);

        return ResponseEntity.ok(new LoginSuccessDto(LocalDateTime.now(), true));
    }
}
