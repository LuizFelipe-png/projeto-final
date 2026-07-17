/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.model;

/**
 *
 * @author Win
 */
public class UsuarioResponseDTO {
    
    private String nome;
    private String token;
    private String role;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(String nome, String token, String role) {
        this.nome = nome;
        this.token = token;
        this.role = role;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
