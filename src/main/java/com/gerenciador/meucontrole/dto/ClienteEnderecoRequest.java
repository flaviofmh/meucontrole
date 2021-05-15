package com.gerenciador.meucontrole.dto;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ClienteEnderecoRequest {

	@Size(max = 255, message = "O logradouro deve ter no máximo 255 caractere")
	private String logradouro;

	@Size(max = 255, message = "O numero deve ter no máximo 255 caractere")
	private String numero;

	@Size(min = 8, max = 8, message = "O cep deve ter 8 caractere")
	private String cep;

	@Size(max = 255, message = "A cidade deve ter 8 caractere")
	private String cidade;

	@Size(min = 2, max = 2, message = "O estado deve ter 2 caractere")
	private String estado;

	@Size(max = 255, message = "O complemento deve ter no máximo 255 caractere")
	private String complemento;

}
