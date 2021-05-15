package com.gerenciador.meucontrole.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciador.meucontrole.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
