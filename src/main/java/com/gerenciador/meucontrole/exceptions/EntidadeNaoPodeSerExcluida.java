package com.gerenciador.meucontrole.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class EntidadeNaoPodeSerExcluida extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoPodeSerExcluida(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
