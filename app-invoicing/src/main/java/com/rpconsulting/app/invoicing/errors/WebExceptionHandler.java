package com.rpconsulting.app.invoicing.errors;

import com.rpconsulting.app.invoicing.dtos.errors.ErrorDto;
import com.rpconsulting.app.invoicing.errors.exceptions.AlreadyExistsException;
import com.rpconsulting.app.invoicing.errors.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AlreadyExistsException.class})
    public ResponseEntity<?> handleAlreadyExistsException(AlreadyExistsException ex, HttpServletRequest request) {
        ErrorDto error = ErrorDto
                .builder()
                .messages(Collections.singletonList(ex.getMessage()))
                .resource(request.getRequestURI())
                .build();
        return buildResponseEntity(CONFLICT, error);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        ErrorDto error = ErrorDto
                .builder()
                .messages(Collections.singletonList(ex.getMessage()))
                .resource(request.getRequestURI())
                .build();
        return buildResponseEntity(NOT_FOUND, error);
    }

    private ResponseEntity buildResponseEntity(HttpStatus httpStatus, ErrorDto error) {
        return new ResponseEntity(error, httpStatus);
    }

}
