package org.cross.medicore.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidDetailsException.class)
    public ResponseEntity<ErrorResponse> invalidDetailsExceptionHandler(
            HttpServletRequest request,
            InvalidDetailsException ex){
       String path = request.getRequestURI();

       ErrorResponse errorResponse = new ErrorResponse(path, false, ex.getMessage(), "v1.0.0");

       return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(
            HttpServletRequest request,
            Exception ex){
        String path = request.getRequestURI();

        ErrorResponse errorResponse = new ErrorResponse(path, false, ex.getMessage(), "v1.0.0");

        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
