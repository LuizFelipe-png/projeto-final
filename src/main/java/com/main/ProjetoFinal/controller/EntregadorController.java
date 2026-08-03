/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.controller;

import com.main.ProjetoFinal.model.EntregadorDTO;
import com.main.ProjetoFinal.model.HistoricoDTO;
import com.main.ProjetoFinal.model.OperadorDTO;
import com.main.ProjetoFinal.model.UsuarioDTO;
import com.main.ProjetoFinal.service.EntregadorService;
import com.main.ProjetoFinal.service.TokenService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/entregador")
public class EntregadorController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private EntregadorService service;

    @GetMapping("/pedidos")
    public List<OperadorDTO> listarPedidos(@RequestHeader("Authorization") String auth) {
        String token = auth.replace("Bearer ", "");
        UsuarioDTO usuario = tokenService.extrairClaims(token);
        return service.listarPedidosPorEntregador(usuario.getId().intValue());
    }

    @PostMapping("/confirmar")
    public void confirmarEntrega(@RequestHeader("Authorization") String auth, @RequestBody OperadorDTO pedido) {
        String token = auth.replace("Bearer ", "");
        tokenService.extrairClaims(token);
        boolean sucesso = service.confirmarEntrega(pedido.getId_pedido(), pedido.getToken());
        if (!sucesso) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Token inválido.");
        }
    }

    @PostMapping("/checkpoint")
    public void registrarCheckpoint(@RequestHeader("Authorization") String auth, @RequestBody HistoricoDTO checkpoint) {
        String token = auth.replace("Bearer ", "");
        tokenService.extrairClaims(token);
        boolean sucesso = service.registrarCheckpoint(checkpoint.getId_pedido(), checkpoint.getDescricao());
        if (!sucesso) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Erro ao registrar checkpoint.");
        }
    }

    @GetMapping("/historico/{idPedido}")
    public List<HistoricoDTO> listarHistorico(@RequestHeader("Authorization") String auth, @PathVariable int idPedido) {
        String token = auth.replace("Bearer ", "");
        tokenService.extrairClaims(token);
        return service.listarHistorico(idPedido);
    }

    @GetMapping("/listar")
    public List<EntregadorDTO> listarEntregadores(@RequestHeader("Authorization") String auth) {
        String token = auth.replace("Bearer ", "");
        tokenService.extrairClaims(token);
        return service.listarEntregadores();
    }
}