package com.example.AulaJwt.service;

import com.example.AulaJwt.security.JwtUtil;
import com.example.AulaJwt.model.Usuario;
import com.example.AulaJwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
    public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public Usuario cadastrarUsuario(Usuario usuario) {
        try {
            return userRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o usuário: " + e.getMessage());
        }
    }

    public Usuario autenticarUsuario(String nome, String senha) {
        Usuario usuario = userRepository.findByNome(nome);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }
        return null;
    }

}
