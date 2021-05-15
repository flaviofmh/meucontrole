package com.gerenciador.meucontrole.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@EqualsAndHashCode.Include
	private Long id;

	@NotBlank(message = "O nome é obrigatório")
	@Size(max = 255, message = "O nome deve ter no máximo 255 caractere")
	private String nome;

	@NotBlank(message = "O sobrenome é obrigatório")
	@Size(max = 255, message = "O sobrenome deve ter no máximo 255 caractere")
	private String sobreNome;

	@Column(unique = true)
	@CPF(message = "CPF invalido")
	private String cpf;

	private LocalDate dataNascimento;

	private boolean ativo;

	private String profissao;

	@CreationTimestamp
	private LocalDateTime dataCadastro;

	@UpdateTimestamp
	private LocalDateTime dataAtualizacao;

	@Embedded
	private ClienteEndereco endereco;

	@OneToMany(mappedBy = "cliente")
	private List<ClienteContato> contatos;

	public Cliente() {
	}

}
