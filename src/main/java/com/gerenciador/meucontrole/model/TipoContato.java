package com.gerenciador.meucontrole.model;

public enum TipoContato {

	EMAIL("EMAIL"), TELEFONE("TELEFONE");

	private String decricao;

	private TipoContato(String descricao) {
		this.decricao = descricao;
	}

	public String getDecricao() {
		return this.decricao;
	}

}
