package org.cross.medicore.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        String path,
        LocalDateTime timestamp,
        boolean success,
        String message,
        String platformVersion
) {
    public ErrorResponse(
            String path, boolean success, String message, String platformVersion
    ){
        this(path, LocalDateTime.now(), success, message, platformVersion);
    }
}