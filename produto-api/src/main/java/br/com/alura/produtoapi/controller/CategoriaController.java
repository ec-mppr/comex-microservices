package br.com.alura.produtoapi.controller;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.produtoapi.dto.CategoriaDto;
import br.com.alura.produtoapi.dto.CategoriaUpdateRequest;
import br.com.alura.produtoapi.model.Categoria;
import br.com.alura.produtoapi.service.CategoriaService;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/lista")
    public ResponseEntity<List<CategoriaDto>> lista() {
        List<Categoria> listaCategorias = service.listar();
        List<CategoriaDto> listaCategoriaDTOs = listaCategorias.stream().map(CategoriaDto::new).toList();
        return ResponseEntity.ok(listaCategoriaDTOs);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<CategoriaDto> cadastro(@RequestBody @Valid CategoriaDto request,
            UriComponentsBuilder uriBuilder) {
        Categoria categoria = Categoria.fromRecord(request);
        service.cadastrar(categoria);

        var uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }

    @GetMapping("/busca/{id}")
    public ResponseEntity<CategoriaDto> busca(@PathVariable Long id) {
        Categoria categoria = service.buscar(id);
        CategoriaDto categoriaDTO = new CategoriaDto(categoria);
        return ResponseEntity.ok(categoriaDTO);
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<Void> deleta(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualiza")
    public ResponseEntity<CategoriaDto> atualiza(@RequestBody @Valid CategoriaUpdateRequest request) {
        var categoria = service.atualizar(request);
        return ResponseEntity.ok(new CategoriaDto(categoria));
    }

}
