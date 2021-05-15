package com.gerenciador.meucontrole.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.meucontrole.dto.ClienteRequest;
import com.gerenciador.meucontrole.dto.ClienteResponse;
import com.gerenciador.meucontrole.model.Cliente;
import com.gerenciador.meucontrole.repository.ClienteContatoRepository;
import com.gerenciador.meucontrole.repository.ClienteRepository;

@RestController
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteContatoRepository clienteContatoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<List<ClienteResponse>> getClientes() {

		List<Cliente> clientes = clienteRepository.findAll();

		List<ClienteResponse> clientesResponse = new ArrayList<ClienteResponse>();

		clientes.forEach(c -> {
			ClienteResponse map = modelMapper.map(c, ClienteResponse.class);
			clientesResponse.add(map);

		});

		return ResponseEntity.ok(clientesResponse);
	}

	@PostMapping
	public ClienteResponse gravaCliente(@RequestBody @Valid ClienteRequest clienteRequest) {

		Cliente novoCliente = modelMapper.map(clienteRequest, Cliente.class);

		Cliente clienteSalvo = clienteRepository.save(novoCliente);

		novoCliente.getContatos().forEach(novoContato -> {
			novoContato.setCliente(clienteSalvo);
			clienteContatoRepository.save(novoContato);
		});

		Optional<Cliente> clienteOptional = clienteRepository.findById(clienteSalvo.getId());

		ClienteResponse clienteResponse = new ClienteResponse();

		if (clienteOptional.isPresent()) {

			clienteResponse = modelMapper.map(clienteOptional.get(), ClienteResponse.class);
		}

		return clienteResponse;

	}

}
