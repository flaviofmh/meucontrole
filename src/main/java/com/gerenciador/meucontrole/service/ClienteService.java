package com.gerenciador.meucontrole.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gerenciador.meucontrole.dto.ClienteRequest;
import com.gerenciador.meucontrole.dto.ClienteResponse;
import com.gerenciador.meucontrole.exceptions.EntidadeNaoEncontradaException;
import com.gerenciador.meucontrole.exceptions.EntidadeNaoPodeSerExcluida;
import com.gerenciador.meucontrole.model.Cliente;
import com.gerenciador.meucontrole.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Page<ClienteResponse> findAllPageable(Pageable page) {

		Page<Cliente> clientesPage = clienteRepository.findAll(page);

		Page<ClienteResponse> clienteResponsePage = clientesPage
				.map(cliente -> ClienteResponse.fromCliente(modelMapper, cliente));

		return clienteResponsePage;
	}

	public ClienteResponse findById(Long id) {

		Optional<Cliente> clienteOptional = clienteRepository.findById(id);

		ClienteResponse clienteResponse = new ClienteResponse();

		if (clienteOptional.isPresent()) {
			clienteResponse = modelMapper.map(clienteOptional.get(), ClienteResponse.class);
		} else {
			throw new EntidadeNaoEncontradaException("Cliente de Id [" + id + "] não encontrado");
		}

		return clienteResponse;
	}

	public ClienteResponse save(ClienteRequest clienteRequest) {

		Cliente novoCliente = modelMapper.map(clienteRequest, Cliente.class);

		novoCliente.getContatos().forEach(contato -> {
			contato.setCliente(novoCliente);
		});

		Cliente clienteSalvo = clienteRepository.save(novoCliente);

		return modelMapper.map(clienteSalvo, ClienteResponse.class);
	}

	public void delete(Long id) {

		Optional<Cliente> clienteOptional = clienteRepository.findById(id);

		if (clienteOptional.isPresent()) {

			Cliente cliente = clienteOptional.get();

			if (cliente.isAtivo())
				throw new EntidadeNaoPodeSerExcluida("Cliente de Id " + id + " não pode ser excluíd, pois está ativo");

			clienteRepository.delete(cliente);
		} else {
			throw new EntidadeNaoEncontradaException("Cliente de Id [" + id + "] não encontrado");
		}

	}

}
