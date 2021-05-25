package com.gerenciador.meucontrole.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.meucontrole.dto.ClienteRequest;
import com.gerenciador.meucontrole.dto.ClienteResponse;
import com.gerenciador.meucontrole.service.ClienteService;

@RestController
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteResponse>> getClientes() {
		return ResponseEntity.ok(clienteService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteResponse> getCliente(@PathVariable Long id) {
		return ResponseEntity.ok(clienteService.findById(id));
	}

	@PostMapping
	public ResponseEntity<ClienteResponse> gravaCliente(@RequestBody @Valid ClienteRequest clienteRequest) {
		clienteService.save(clienteRequest);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deletaCliente(@PathVariable Long id) {
		clienteService.delete(id);
	}

}
