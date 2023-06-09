package com.rpconsulting.app.invoicing.handlers;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rpconsulting.app.invoicing.exceptions.AlreadyExistsException;
import com.rpconsulting.app.invoicing.dtos.ErrorDto;

@Order(Ordered.HIGHEST_PRECEDENCE) //Le indicamos que se carge con la mayor preferencia posible
@ControllerAdvice //Le indico que tiene la resposabilidad de manejar las excepciones
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({AlreadyExistsException.class})
	public ResponseEntity<?> handleAlreadyExistsException(AlreadyExistsException ex, HttpServletRequest request) {
		ErrorDto error = new ErrorDto();
		error.setMessages(ex.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setUrl(request.getRequestURI());
		
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}
}

