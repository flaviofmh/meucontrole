package com.gerenciador.meucontrole.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ClienteResponse {

	private Long id;

	private String nome;

	private String sobreNome;

	private String cpf;

	private LocalDate dataNascimento;

	private boolean ativo;

	private String profissao;

	private ClienteEnderecoRequest endereco;

	private List<ClienteContatoRequest> contatos;

}
