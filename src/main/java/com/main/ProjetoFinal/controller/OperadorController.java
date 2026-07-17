package com.main.ProjetoFinal.controller;

import com.main.ProjetoFinal.model.OperadorDTO;
import com.main.ProjetoFinal.service.OperadorService;
import com.main.ProjetoFinal.service.TokenService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/industria")
public class OperadorController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private OperadorService service;

    @GetMapping("/pedidos")
    public List<OperadorDTO> listarPedidos(@RequestHeader("Authorization") String auth){
        String token = auth.replace("Bearer ", "");
        tokenService.extrairClaims(token);
        return service.listarPedidos(token);
    }

    @PostMapping("/pedidos")
    public void cadastrarLote(@RequestHeader("Authorization") String auth, @RequestBody OperadorDTO operador){
        String token = auth.replace("Bearer ", "");
        tokenService.extrairClaims(token);
        service.cadastrarLote(token, operador);
    }
}