package com.jpbastos.gerenciadorPessoas.service.exceptions;

public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = -6581310388879070912L;

	public DatabaseException(String msg) {
		super(msg);
	}

}
