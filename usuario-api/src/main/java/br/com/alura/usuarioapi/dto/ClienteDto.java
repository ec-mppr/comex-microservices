package br.com.alura.usuarioapi.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteDto(
        @NotBlank @CPF String cpf,

        @NotBlank String nome,

        @Email String email,

        @Length(min = 6, max = 64) String senha,

        @Length(min = 8, max = 14) String telefone,

        String logradouro,

        String bairro,

        String cidade,

        @Length(min = 2, max = 2) String estado,

        String cep

) {

}
