package com.gerenciador.meucontrole.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciador.meucontrole.model.ClienteContato;

public interface ClienteContatoRepository extends JpaRepository<ClienteContato, Long>  {

}
