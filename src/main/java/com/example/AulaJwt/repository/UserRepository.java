package com.example.AulaJwt.repository;

import com.example.AulaJwt.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNome(String nome);
}
