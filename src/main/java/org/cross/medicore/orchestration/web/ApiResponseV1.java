package org.cross.medicore.orchestration.web;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Getter
public class ApiResponseV1<T> {
    private String path;
    private LocalDateTime timestamp;
    private int status;
    private boolean success;
    private T data;

    public static <T> ApiResponseV1<T> of(T data,boolean success, String path, int status){
        return ApiResponseV1.<T>builder()
                .success(success)
                .status(status)
                .path(path)
                .timestamp(LocalDateTime.now())
                .data(data)
                .build();
    }

    public static <T> ApiResponseV1<T> ok(T data, String path){
        return ApiResponseV1.of(data,true, path, HttpStatus.OK.value());
    }

    public static <T> ApiResponseV1<T> created(T data, String path){
        return ApiResponseV1.of(data,true, path, HttpStatus.CREATED.value());
    }
}
