package br.com.alura.produtoapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.produtoapi.dto.CategoriaUpdateRequest;
import br.com.alura.produtoapi.model.Categoria;
import br.com.alura.produtoapi.repository.CategoriaRepository;

@Component
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public void cadastrar(Categoria categoria) {
        repository.save(categoria);
    }

    public List<Categoria> listar() {
        return repository.findAll();
    }

    public Categoria buscar(Long id) {
        return repository.getReferenceById(id);
    }

    @Transactional
    public void deletar(Long id) {
        var categoria = repository.getReferenceById(id);
        categoria.delete();
    }

    @Transactional
    public Categoria atualizar(CategoriaUpdateRequest request) {
        var categoria = repository.getReferenceById(request.id());
        categoria.update(request);
        return categoria;
    }
}
