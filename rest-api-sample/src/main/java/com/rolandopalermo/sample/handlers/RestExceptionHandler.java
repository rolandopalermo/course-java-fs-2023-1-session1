package com.rolandopalermo.sample.handlers;

import com.rolandopalermo.sample.dtos.ErrorDto;
import com.rolandopalermo.sample.exceptions.AlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.springframework.http.HttpStatus.CONFLICT;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AlreadyExistsException.class})
    public ResponseEntity<?> handleAlreadyExistsException(AlreadyExistsException ex, HttpServletRequest request) {
        ErrorDto error = ErrorDto
                .builder()
                .messages(Collections.singletonList(ex.getMessage()))
                .resource(request.getRequestURI())
                .build();
        return buildResponseEntity(CONFLICT, error);
    }

    private ResponseEntity buildResponseEntity(HttpStatus httpStatus, ErrorDto error) {
        return new ResponseEntity(error, httpStatus);
    }

}
