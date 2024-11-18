package com.example.AulaJwt.controller;

import com.example.AulaJwt.security.JwtUtil;
import com.example.AulaJwt.service.UserService;
import com.example.AulaJwt.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Object> cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = userService.cadastrarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario autenticado = userService.autenticarUsuario(usuario.getNome(), usuario.getSenha());
            if (autenticado != null) {
                String token = jwtUtil.gerarToken(autenticado.getNome());
                return ResponseEntity.ok().body("Bearer " + token);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nome ou senha incorretos");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
        }
    }
}
