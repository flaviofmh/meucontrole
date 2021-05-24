package com.gerenciador.meucontrole.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
	private ClienteContatoService contatoService;

	@Autowired
	private ModelMapper modelMapper;

	public List<ClienteResponse> findAll() {

		List<Cliente> clientes = clienteRepository.findAll();

		if (clientes == null || clientes.size() == 0)
			throw new EntidadeNaoEncontradaException("Não existe cliente cadastrado");

		List<ClienteResponse> clientesResponse = new ArrayList<ClienteResponse>();

		clientes.forEach(c -> {
			ClienteResponse map = modelMapper.map(c, ClienteResponse.class);
			clientesResponse.add(map);
		});

		return clientesResponse;
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

	public void save(ClienteRequest clienteRequest) {

		Cliente novoCliente = modelMapper.map(clienteRequest, Cliente.class);

		Cliente clienteSalvo = clienteRepository.save(novoCliente);

		novoCliente.getContatos().forEach(novoContato -> {
			novoContato.setCliente(clienteSalvo);
			contatoService.save(novoContato);
		});

	}

	public void delete(Long id) {

		Optional<Cliente> clienteOptional = clienteRepository.findById(id);

		if (clienteOptional.isPresent()) {

			Cliente cliente = clienteOptional.get();
			
			if (cliente.isAtivo())
				throw new EntidadeNaoPodeSerExcluida("Cliente de Id " + id + " não pode ser excluíd, pois está ativo");

			cliente.getContatos().forEach(contato -> {
				contatoService.delete(contato);
			});

			clienteRepository.delete(cliente);
		} else {
			throw new EntidadeNaoEncontradaException("Cliente de Id [" + id + "] não encontrado");
		}

	}

}
