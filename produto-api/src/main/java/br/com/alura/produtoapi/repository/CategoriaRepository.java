package br.com.alura.produtoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.produtoapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
