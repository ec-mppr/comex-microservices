package br.com.alura.produtoapi.dto;

import org.hibernate.validator.constraints.Length;

import br.com.alura.produtoapi.model.Produto;
import jakarta.validation.constraints.Positive;

public record ProdutoDto(@Length(min = 2, message = "O nome deve ter no mínimo 2 caracteres") String nome,
    @Positive(message = "O preço deve ser maior que R$0,00") double preco,
    String descricao,
    Integer quantidade,
    Long categoria) {

  public ProdutoDto(Produto produto) {
    this(produto.getNome(), produto.getPreco(), produto.getDescricao(), produto.getQuantidade(),
        produto.getCategoria().getId());
  }
}
