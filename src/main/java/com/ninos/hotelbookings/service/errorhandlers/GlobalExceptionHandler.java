package com.ninos.hotelbookings.service.errorhandlers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * global HTTP endpoints exception handler
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {
            IllegalArgumentException.class,
            ValidationException.class,
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class,
            InvalidMediaTypeException.class,
            ConstraintViolationException.class,
            InvalidFormatException.class
    })
    public ResponseEntity<Void> handleInvalidInputExceptions(Exception ex) {
        logger.error("Received a bad request exception: {}", ex.getMessage());
        logger.debug("{}", ex.getMessage(), ex);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleGeneralException(Exception ex) {
        logger.error("{}", ex.getMessage(), ex);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
