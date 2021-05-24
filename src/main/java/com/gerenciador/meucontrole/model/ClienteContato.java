package com.gerenciador.meucontrole.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClienteContato {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@EqualsAndHashCode.Include
	private Long id;

	private String telefone;
	
	@Email
	private String email;

	@Enumerated(EnumType.STRING)
	private TipoContato tipoContato;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public ClienteContato() {
	}

}
