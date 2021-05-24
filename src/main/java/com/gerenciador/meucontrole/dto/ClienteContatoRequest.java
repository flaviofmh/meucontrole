package com.gerenciador.meucontrole.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.gerenciador.meucontrole.model.TipoContato;

import lombok.Data;

@Data
public class ClienteContatoRequest {

	private String telefone;

	@Email(message = "Deve ser um e-mail válido")
	private String email;

	@NotNull(message = "O tipo de contato deve ser informado")
	private TipoContato tipoContato;

}
