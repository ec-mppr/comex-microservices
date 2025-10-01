package br.com.alura.produtoapi.dto;

import org.hibernate.validator.constraints.Length;
import br.com.alura.produtoapi.model.Categoria;
import jakarta.validation.constraints.NotBlank;

public record CategoriaDto(
    @NotBlank(message = "A categoria precisa ter um nome") @Length(min = 2, message = "O nome da categoria precisa ter no mínimo 2 caracteres") String nome,
    Long id) {

  public CategoriaDto(Categoria categoria) {
    this(categoria.getNome(), categoria.getId());
  }
}
