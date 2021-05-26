package com.gerenciador.meucontrole.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

import com.gerenciador.meucontrole.controller.statics.UrlResources;
import com.gerenciador.meucontrole.dto.ClienteRequest;
import com.gerenciador.meucontrole.dto.ClienteResponse;
import com.gerenciador.meucontrole.service.ClienteService;

@RestController
@RequestMapping(value = UrlResources.CLIENTE)
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<Page<ClienteResponse>> getClientes(@PageableDefault(page = 0, size = 10, direction = Direction.DESC, sort = "id") Pageable page) {
		return ResponseEntity.ok(clienteService.findAllPageable(page));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteResponse> getCliente(@PathVariable Long id) {
		return ResponseEntity.ok(clienteService.findById(id));
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ClienteResponse gravaCliente(@RequestBody @Valid ClienteRequest clienteRequest) {
		return clienteService.save(clienteRequest);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deletaCliente(@PathVariable Long id) {
		clienteService.delete(id);
	}

}
