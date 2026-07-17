/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.service;

import com.main.ProjetoFinal.model.OperadorDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 *
 * @author Win
 */
@Service
public class OperadorService {

    private final RestClient restClient;

    public OperadorService() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:9000")
                .build();
    }

    public List<OperadorDTO> listarPedidos(String token) {
        return restClient.get()
                .uri("/industria/pedidos")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(new ParameterizedTypeReference<List<OperadorDTO>>() {});
    }

    public void cadastrarLote(String token, OperadorDTO operador) {
        restClient.post()
                .uri("/industria/pedidos")
                .header("Authorization", "Bearer " + token)
                .body(operador)
                .retrieve()
                .toBodilessEntity();
    }
}



