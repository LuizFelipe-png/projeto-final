package com.main.ProjetoFinal.controller;

import com.main.ProjetoFinal.model.HistoricoDTO;
import com.main.ProjetoFinal.model.OperadorDTO;
import com.main.ProjetoFinal.model.UsuarioDTO;
import com.main.ProjetoFinal.service.EntregadorService;
import com.main.ProjetoFinal.service.TokenService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
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
        String tokenHeader = auth.replace("Bearer ", "");
        tokenService.extrairClaims(tokenHeader);

        // Validação preventiva: evita enviar dados nulos ou vazios
        if (pedido == null || pedido.getToken() == null || pedido.getToken().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token de confirmação não informado.");
        }

        // Passa o token sem espaços no início/fim (.trim())
        boolean sucesso = service.confirmarEntrega(pedido.getId_pedido(), pedido.getToken().trim());

        if (!sucesso) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token inválido.");
        }
    }

    @PostMapping("/checkpoint")
    public void registrarCheckpoint(@RequestHeader("Authorization") String auth, @RequestBody HistoricoDTO checkpoint) {
        String tokenHeader = auth.replace("Bearer ", "");
        tokenService.extrairClaims(tokenHeader);

        if (checkpoint == null || checkpoint.getDescricao() == null || checkpoint.getDescricao().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Descrição do checkpoint é obrigatória.");
        }

        boolean sucesso = service.registrarCheckpoint(checkpoint.getId_pedido(), checkpoint.getDescricao().trim());

        if (!sucesso) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao registrar checkpoint.");
        }
    }

    @GetMapping("/historico/{idPedido}")
    public List<HistoricoDTO> listarHistorico(@RequestHeader("Authorization") String auth, @PathVariable int idPedido) {
        String token = auth.replace("Bearer ", "");
        tokenService.extrairClaims(token);
        return service.listarHistorico(idPedido);
    }

    @GetMapping("/listar-entregadores")
    public List<UsuarioDTO> listarEntregadores(@RequestHeader("Authorization") String auth) {
        String token = auth.replace("Bearer ", "");
        tokenService.extrairClaims(token);
        return service.listarEntregadores(token);
    }

    @GetMapping("/historico-publico/{idPedido}")
    public List<HistoricoDTO> historicoPublico(@PathVariable int idPedido) {
        return service.listarHistorico(idPedido);
    }

    @GetMapping("/historico-geral")
    public List<HistoricoDTO> historicoGeral() {
        return service.listarTodoHistorico();
    }
}
