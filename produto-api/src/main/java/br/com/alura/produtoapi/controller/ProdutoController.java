package br.com.alura.produtoapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.produtoapi.dto.ProdutoDto;
import br.com.alura.produtoapi.model.Categoria;
import br.com.alura.produtoapi.model.Produto;
import br.com.alura.produtoapi.repository.CategoriaRepository;
import br.com.alura.produtoapi.repository.ProdutoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

  @Autowired
  private CategoriaRepository categoriaRepository;

  @Autowired
  private ProdutoRepository produtoRepository;

  @GetMapping("/lista")
  public ResponseEntity<List<Produto>> lista() {
    List<Produto> lista = produtoRepository.findAll();
    return new ResponseEntity<List<Produto>>(lista, HttpStatus.OK);
  }

  @PostMapping("/cadastro")
  public ResponseEntity<ProdutoDto> cadastro(@RequestBody @Valid ProdutoDto request, UriComponentsBuilder uribuilder) {
    Categoria categoria = categoriaRepository.getReferenceById(request.categoria());
    Produto produto = Produto.fromRecord(request, categoria);
    URI uri = uribuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
    produtoRepository.save(produto);
    return ResponseEntity.created(uri).body(new ProdutoDto(produto));
  }

  @GetMapping("/busca/{id}")
  public ResponseEntity<ProdutoDto> busca(@PathVariable Long id) {
    Produto produto = produtoRepository.getReferenceById(id);
    return ResponseEntity.ok(new ProdutoDto(produto));
  }

  @DeleteMapping("/deleta/{id}")
  public ResponseEntity<Void> deleta(@PathVariable Long id) {
    produtoRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
