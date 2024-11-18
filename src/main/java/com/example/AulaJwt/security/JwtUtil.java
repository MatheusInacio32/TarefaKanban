package com.example.AulaJwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Gerando uma chave segura de 256 bits para o algoritmo HS256
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 256 bits
    private static final long expirationTime = 1000 * 60 * 30; // 30 minutos

    // Método para gerar o token JWT
    public String gerarToken(String nome) {
        return Jwts.builder()
                .setSubject(nome)
                .claim("roles", "user") // Adiciona roles no token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }


    // Método para validar o token e extrair as claims
    private Claims validarToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)  // Chave usada para validar
                .build()
                .parseClaimsJws(token)  // Parse do token
                .getBody();  // Retorna os dados do token (claims)
    }

    // Método para extrair o ID ou nome do usuário do token
    public String extrairUserId(String token) {
        Claims claims = validarToken(token);  // Obtém as claims do token
        return claims.getSubject();  // Extrai o "subject", que é o nome/ID do usuário
    }

    // Método para verificar se o token está expirado
    public boolean tokenExpirado(String token) {
        Claims claims = validarToken(token);  // Obtém as claims do token
        Date expirationDate = claims.getExpiration();  // Obtém a data de expiração
        return expirationDate.before(new Date());  // Verifica se a data de expiração já passou
    }


}