/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.controller;

import com.main.ProjetoFinal.model.ClienteDTO;
import com.main.ProjetoFinal.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Aluno
 */
@Controller
@RequestMapping("/api/auth")
public class ClienteController {
    
    @Autowired
    private ClienteService service;
    
    @PostMapping("/cadastrar")
    public String cadastrar (@RequestBody ClienteDTO cliente){
        service.cadastrar(cliente);
        return "Cadastro feito com sucesso!";
    }
    
    @PostMapping("/logar")
    public String login(@RequestBody ClienteDTO cliente){
        return service.logar(cliente);
    }
}
