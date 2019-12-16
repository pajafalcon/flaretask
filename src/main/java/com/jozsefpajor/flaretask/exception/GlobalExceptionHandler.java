package com.jozsefpajor.flaretask.exception;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger("api/v1");

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseError> handleNotFoundException( Exception ex, WebRequest request ) {
        ResponseError errorDetails = ResponseError.builder()
                .occuredAt(new Date())
                .status(HttpStatus.NOT_FOUND.toString())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseError> handleBadRequest( MethodArgumentTypeMismatchException ex, WebRequest request ) {
        LOG.error(request.getContextPath(), ex);

        ResponseError errorDetails = ResponseError.builder()
                .occuredAt(new Date())
                .status(HttpStatus.BAD_REQUEST.toString())
                .message("Invalid argument.")
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
