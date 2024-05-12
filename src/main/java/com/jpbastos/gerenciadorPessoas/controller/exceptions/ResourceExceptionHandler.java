package com.jpbastos.gerenciadorPessoas.controller.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jpbastos.gerenciadorPessoas.service.exceptions.DatabaseException;
import com.jpbastos.gerenciadorPessoas.service.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ControllerAdvice
@Data
@EqualsAndHashCode(callSuper = true)
public class ResourceExceptionHandler extends RuntimeException {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		return createErrorResponse(e, HttpStatus.NOT_FOUND, "Recurso não encontrado", request);
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		return createErrorResponse(e, HttpStatus.BAD_REQUEST, "Erro de banco de dados", request);
	}

	private ResponseEntity<StandardError> createErrorResponse(Exception e, HttpStatus status, String error,
			HttpServletRequest request) {
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
