/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.service;

import com.main.ProjetoFinal.model.OperadorDTO;
import com.main.ProjetoFinal.repository.OperadorRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Win
 */
@Service
public class OperadorService {
    
    @Autowired
    private OperadorRepository dao;

    @Autowired
    private TokenService tokenService;
   

    public void cadastrarLote(String token, OperadorDTO operador) {
        tokenService.extrairClaims(token);
        int linhas = dao.cadastrarLote(operador);
        if (linhas == 0) {
            throw new RuntimeException("Erro ao cadastrar pedido.");
        }
    }
    
    public List<OperadorDTO> listarPedidos(String token, OperadorDTO operador){
        tokenService.extrairClaims(token);
        return dao.listarPedidos();

    }
    
    
}



