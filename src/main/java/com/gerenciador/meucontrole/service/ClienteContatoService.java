package com.gerenciador.meucontrole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.meucontrole.model.ClienteContato;
import com.gerenciador.meucontrole.repository.ClienteContatoRepository;

@Service
public class ClienteContatoService {

	@Autowired
	private ClienteContatoRepository contatoRepository;

	public void delete(ClienteContato contato) {
		contatoRepository.delete(contato);
	}

	public void save(ClienteContato contato) {
		contatoRepository.save(contato);
	}

}
