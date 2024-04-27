package com.jpbastos.gerenciadorPessoas.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6410956304358624285L;

	public ResourceNotFoundException(Object id) {
		super("Recurso não encontrado. Id " + id);
	}
}
