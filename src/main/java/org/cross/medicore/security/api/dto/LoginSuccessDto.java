package org.cross.medicore.security.api.dto;

import java.time.LocalDateTime;

public record LoginSuccessDto(
        LocalDateTime timestamp,
        boolean success
) {
    public LoginSuccessDto(){
         this(LocalDateTime.now(), true);
    }
}
