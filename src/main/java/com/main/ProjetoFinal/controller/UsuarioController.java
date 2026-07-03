package com.main.ProjetoFinal.controller;

import com.main.ProjetoFinal.model.UsuarioDTO;
import com.main.ProjetoFinal.model.UsuarioRequestDTO;
import com.main.ProjetoFinal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api/auth")
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;
    
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody UsuarioDTO cliente){
        service.cadastrar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro feito com sucesso!");
    }
    
    @PostMapping("/logar")
    public String login(@RequestBody UsuarioRequestDTO cliente){
        return service.logar(cliente);
    }
    
    @GetMapping("/home")
    public String home(){
        return "home";
    }
}