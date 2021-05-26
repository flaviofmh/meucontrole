package com.gerenciador.meucontrole.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ClienteEndereco {

	private String logradouro;

	private String numero;

	private String cep;

	private String cidade;

	private String estado;

	private String complemento;

	public String getLogradouro() {
		return logradouro;
	}

}
