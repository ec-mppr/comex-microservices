package br.com.alura.usuarioapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.usuarioapi.dto.AuthDto;
import br.com.alura.usuarioapi.dto.JwtTokenDto;
import br.com.alura.usuarioapi.model.Usuario;
import br.com.alura.usuarioapi.service.JwtTokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")

public class AuthController {
  @Autowired
  private AuthenticationManager manager;

  @Autowired
  private JwtTokenService jwtTokenService;

  @PostMapping
  public ResponseEntity<JwtTokenDto> login(@RequestBody @Valid AuthDto dados) {
    var token = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
    System.out.println(dados.email() + " " + dados.senha());
    System.out.println(token);
    var authentication = manager.authenticate(token);
    System.out.println(authentication.getPrincipal());
    System.out.println(authentication.getAuthorities());
    JwtTokenDto jwtToken = new JwtTokenDto(jwtTokenService.generateToken((Usuario) authentication.getPrincipal()));
    return ResponseEntity.ok(jwtToken);
  }
}
