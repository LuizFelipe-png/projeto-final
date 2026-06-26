package com.main.ProjetoFinal.service;

import com.main.ProjetoFinal.model.ClienteDTO;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public SecretKey getSignKey() {
        byte[] keyBytes = Base64.getDecoder().decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String gerarToken (ClienteDTO cliente){

        if((cliente.getId() == 0 || cliente.getId() == null) || cliente.getNome().equals("") || cliente.getEmail().equals("")){
            throw new ResponseStatusException(
            HttpStatusCode.valueOf(400), "Um ou mais campos faltantes");
        }

        return Jwts.builder()
                .subject(cliente.getNome())
                .claim("nome", cliente.getNome())
                .claim("usuario", cliente.getUsuario())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3000000))
                .signWith(this.getSignKey()) 
                .compact();
    }
    
    public ClienteDTO extrairClaims(String token){
        Claims claims = Jwts.parser()
                .verifyWith(this.getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        
        ClienteDTO user = new ClienteDTO();
        user.setId(claims.get("id", Long.class));
        user.setNome(claims.get("nome", String.class));
        user.setUsuario(claims.get("usuario", String.class));
        
        return user;
    }
    
    public boolean validarToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(this.getSignKey()) 
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}