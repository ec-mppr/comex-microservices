package br.com.alura.usuarioapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.usuarioapi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
