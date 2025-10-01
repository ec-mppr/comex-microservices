package br.com.alura.produtoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.produtoapi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
