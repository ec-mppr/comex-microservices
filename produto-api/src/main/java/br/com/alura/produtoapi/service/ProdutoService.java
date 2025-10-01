package br.com.alura.produtoapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.produtoapi.model.Produto;
import br.com.alura.produtoapi.repository.*;

@Component
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public void cadastrar(Produto produto) {
        repository.save(produto);
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto buscar(Long id) {
        return repository.getReferenceById(id);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
