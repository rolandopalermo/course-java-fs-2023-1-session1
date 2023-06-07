package com.rolandopalermo.sample.handlers;

import com.rolandopalermo.sample.dtos.ErrorDto;
import com.rolandopalermo.sample.exceptions.AlreadyExistsException;
import com.rolandopalermo.sample.exceptions.AlreadyExistsV2Exception;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collections;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AlreadyExistsException.class})
    public ResponseEntity<?> handleAlreadyExistsException(AlreadyExistsException ex, HttpServletRequest request) {
        ErrorDto error = new ErrorDto();
        error.setErrors(Collections.singletonList(ex.getMessage()));
        error.setTimestamp(LocalDateTime.now());
//        error.setUrl(((ServletWebRequest) request).getRequest().getRequestURI());
        error.setUrl(request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({AlreadyExistsV2Exception.class})
    public ResponseEntity<?> handleAlreadyExistsV2Exception(AlreadyExistsV2Exception ex, HttpServletRequest request) {
        ErrorDto error = new ErrorDto();
        error.setErrors(ex.getErrors());
        error.setTimestamp(LocalDateTime.now());
//        error.setUrl(((ServletWebRequest) request).getRequest().getRequestURI());
        error.setUrl(request.getRequestURI());
        error.setNestedErrors(ex.getNestedErrors());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
