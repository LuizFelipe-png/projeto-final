package com.main.ProjetoFinal.controller;

import com.main.ProjetoFinal.model.OperadorDTO;
import com.main.ProjetoFinal.model.UsuarioDTO;
import com.main.ProjetoFinal.model.UsuarioResponseDTO;
import com.main.ProjetoFinal.service.OperadorService;
import com.main.ProjetoFinal.service.TokenService;
import com.main.ProjetoFinal.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService Usuarioservice;

    @Autowired
    private OperadorService service;
    
    @Autowired
    private TokenService tokenService;

    @PostMapping("/logar")
    public UsuarioResponseDTO login(@RequestBody UsuarioDTO cliente) {
        UsuarioDTO usuarioLogado = Usuarioservice.logar(cliente);
        if (usuarioLogado == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "E-mail ou senha inválidos.");
        }
        String token = tokenService.gerarToken(usuarioLogado);
        return new UsuarioResponseDTO(
                usuarioLogado.getNome(),
                token,
                usuarioLogado.getRole()
        );
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@RequestBody UsuarioDTO cliente) {
        Usuarioservice.cadastrar(cliente);
        return "redirect:/logar";
    }

    @GetMapping("/listar")
    public List<OperadorDTO> listarPedidos(@RequestHeader("Authorization") String auth) {
        String token = auth.replace("Bearer ", "");
        return service.listarPedidos(token, null);
    }

}
