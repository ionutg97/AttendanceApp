package com.psbd.Attendance.exception.exceptionHandler;


import com.psbd.Attendance.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.logging.Logger;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidPathException(Exception ex, WebRequest request) {
        return handleInternalException(ex, request, ErrorReason.PATH_FILE_INVALID);
    }

    private ResponseEntity<ApiErrorResponse> handleInternalException(Exception ex, WebRequest request, ErrorReason errorReason) {
        log.info("Caught {} while processing request [message={}]" + ex.getClass().getSimpleName() + ex.getMessage());
        ApiErrorResponse response = ApiErrorResponse
                .builder()
                .timestamp(new Date())
                .error(errorReason.name())
                .errorCode(errorReason.getHttpStatus().value())
                .message(ex.getMessage())
                .path(getPath(request))
                .build();
        return new ResponseEntity<>(response, errorReason.getHttpStatus());
    }

    private String getPath(WebRequest request) {
        String substring = ((ServletWebRequest) request).getRequest().getRequestURI().substring(request.getContextPath().length());
        return request.getContextPath();
    }


}