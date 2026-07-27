/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.controller;

import com.main.ProjetoFinal.model.IncidentesDTO;
import com.main.ProjetoFinal.service.IncidenteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Win
 */
@RestController
@RequestMapping("/industria/incidentes")
public class IncidenteController {

    @Autowired
    private IncidenteService service;

    @PostMapping
    public void cadastrarIncidente(@RequestHeader("Authorization") String auth, @RequestBody IncidentesDTO incidente) {
        String token = auth.replace("Bearer ", "");
        service.cadastrarIncidente(token, incidente);
    }

    @GetMapping
    public List<IncidentesDTO> listarIncidentes(@RequestHeader("Authorization") String auth) {
        String token = auth.replace("Bearer ", "");
        return service.listarIncidentes(token);
    }
}
