package br.com.alura.usuarioapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.alura.usuarioapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  UserDetails findByEmail(String username);
}
