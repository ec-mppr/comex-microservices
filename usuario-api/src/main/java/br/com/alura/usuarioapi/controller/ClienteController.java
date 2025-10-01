package br.com.alura.usuarioapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.usuarioapi.dto.ClienteDto;
import br.com.alura.usuarioapi.model.Cliente;
import br.com.alura.usuarioapi.model.Usuario;
import br.com.alura.usuarioapi.repository.ClienteRepository;
import br.com.alura.usuarioapi.repository.UsuarioRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

  @Autowired
  ClienteRepository clienteRepository;

  @Autowired
  UsuarioRepository usuarioRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @GetMapping("/lista")
  public ResponseEntity<List<Cliente>> lista() {
    List<Cliente> listaClientes = clienteRepository.findAll();
    return ResponseEntity.ok().body(listaClientes);
  }

  @PostMapping("/cadastro")
  public ResponseEntity<Cliente> cadastro(@RequestBody @Valid ClienteDto request) {
    Cliente cliente = Cliente.fromRecord(request);
    Usuario usuario = new Usuario(null, request.email(), passwordEncoder.encode(request.senha()));
    usuarioRepository.save(usuario);
    cliente.setUsuario(usuario);
    clienteRepository.save(cliente);
    return ResponseEntity.ok().body(cliente);
  }
}
