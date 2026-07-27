/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.service;

import com.main.ProjetoFinal.model.IncidentesDTO;
import com.main.ProjetoFinal.repository.IncidenteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Win
 */
@Service
public class IncidenteService {

    @Autowired
    private IncidenteRepository repository;

    @Autowired
    private TokenService tokenService;

    public void cadastrarIncidente(String token, IncidentesDTO incidente) {
        tokenService.extrairClaims(token);

        if (incidente.getTipo().equals("Problema no Caminhão")) {
            incidente.setAcao_tomada("Novo Caminhão Enviado");
        } else {
            incidente.setAcao_tomada("Nova Remessa em Produção");
        }

        int linhas = repository.cadastrarIncidente(incidente);
        if (linhas == 0) {
            throw new RuntimeException("Erro ao cadastrar incidente.");
        }
    }

    public List<IncidentesDTO> listarIncidentes(String token) {
        tokenService.extrairClaims(token);
        return repository.listarIncidentes();
    }
}
