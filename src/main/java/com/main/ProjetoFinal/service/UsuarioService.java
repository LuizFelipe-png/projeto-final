/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.service;

import com.main.ProjetoFinal.model.ClienteDTO;
import com.main.ProjetoFinal.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Aluno
 */
@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repository;
    
    @Autowired
    private TokenService tokenService;
    
    public void cadastrar(ClienteDTO cliente){
        String mensagem = "";
        if(cliente.getNome().equals("")){
            mensagem = "Nome não preenchido!";
        }if(cliente.getUsuario().equals("")){
            mensagem = "Usuario não preenchido!";
        }if(cliente.getEmail().equals("")){
            mensagem = "Email não preenchido!";
        }if(cliente.getTelefone().equals("")){
            mensagem = "Telefone não preenchido!";
        }if(cliente.getSenha().equals("")){
            mensagem = "Senha não preenchida!";
        }
        
        if(!mensagem.equals("")){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem);
        }
        
        repository.cadastrar(cliente);
    }
    
    public String logar(ClienteDTO cliente) {
        String mensagem = "";
        if(cliente.getEmail().equals("")) {
            mensagem = "Email não preenchido";
        } else if (cliente.getSenha().equals("")) {
            mensagem = "Senha não preenchida";
        }
        
        if(!mensagem.equals("")) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem);
        }
        
        ClienteDTO dadosLogado = repository.logar(cliente.getEmail(), cliente.getSenha());
        return tokenService.gerarToken(dadosLogado);
    }
}
