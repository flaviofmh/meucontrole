package com.gerenciador.meucontrole.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
public class ClienteRequest {

	@NotBlank(message = "O nome é obrigatório")
	@Size(max = 255, message = "O nome deve ter no máximo 255 caractere")
	private String nome;

	@NotBlank(message = "O sobrenome é obrigatório")
	@Size(max = 255, message = "O sobrenome deve ter no máximo 255 caractere")
	private String sobreNome;

	@CPF(message = "CPF invalido")
	private String cpf;

	@Past(message = "a data de nascimento deve ser no passado")
	@NotNull(message = "A data de nascimento é obrigatório")
	private LocalDate dataNascimento;

	private boolean ativo;

	@Size(max = 255, message = "A profissao deve ter no máximo 255 caractere")
	private String profissao;

	@Valid
	private ClienteEnderecoRequest endereco;

	private List<ClienteContatoRequest> contatos;

}
