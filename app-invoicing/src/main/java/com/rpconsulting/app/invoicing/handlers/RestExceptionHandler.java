package com.rpconsulting.app.invoicing.handlers;

import com.rpconsulting.app.invoicing.dtos.ErrorDto;
import com.rpconsulting.app.invoicing.exceptions.AlreadyExistsException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler {

    @ExceptionHandler({AlreadyExistsException.class})
    public ResponseEntity<?> handleAlreadyExistsException(AlreadyExistsException ex, HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setUrl(request.getRequestURI());
        errorDto.setMessage(ex.getMessage());
        errorDto.setLocalDateTime(LocalDateTime.now());
        errorDto.setMethod(request.getMethod());
        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }
}
