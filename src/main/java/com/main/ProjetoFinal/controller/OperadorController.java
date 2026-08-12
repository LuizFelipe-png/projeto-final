package com.main.ProjetoFinal.controller;

import com.main.ProjetoFinal.model.AtribuirEntregadorRequestDTO;
import com.main.ProjetoFinal.model.OperadorDTO;
import com.main.ProjetoFinal.service.EntregadorService;
import com.main.ProjetoFinal.service.OperadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/industria")
public class OperadorController {

    @Autowired
    private OperadorService service;

    @Autowired
    private EntregadorService entregadorService;

    @GetMapping("/enviar")
    public String carregarTelaEnviar(Model model, @RequestHeader("Authorization") String auth) {
        return "enviar-entregas";
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<OperadorDTO> listarPedidos(@RequestHeader(value = "Authorization", required = false) String auth) {
        if (auth != null && auth.startsWith("Bearer ")) {
            String token = auth.replace("Bearer ", "");
            return service.listarPedidos(token);
        }
        return service.listarPedidosPendentes();
    }

    @PostMapping("/pedidos")
    @ResponseBody
    public void cadastrarLote(@RequestHeader("Authorization") String auth, @RequestBody OperadorDTO operador) {
        String token = auth.replace("Bearer ", "");
        service.cadastrarLote(token, operador);
    }

    @PutMapping("/atribuir/entregador")
    @ResponseBody
    public OperadorDTO atribuirEntregador(@RequestBody AtribuirEntregadorRequestDTO dados) {
        return service.atribuirEntregador(dados.getIdEncomenda(), dados.getIdEntregador());
    }

    @GetMapping("/pendentes")
    @ResponseBody
    public List<OperadorDTO> listarPedidosPendentes() {
        return service.listarPedidosPendentes();
    }

    @PostMapping("/status/atualizar")
    @ResponseBody
    public ResponseEntity<String> atualizarStatus(@RequestHeader("Authorization") String auth,
            @RequestParam(name = "id_pedido") int idPedido, @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "localizacao", required = false) String localizacao) {

        String token = auth.replace("Bearer ", "");
        service.atualizarStatus(token, idPedido, status, localizacao);

        return ResponseEntity.ok("Status e localização atualizados com sucesso!");
    }

    @GetMapping("/rastrear/{codigo}")
    @ResponseBody
    public OperadorDTO rastrear(@PathVariable String codigo) {
        return service.buscarPorCodigo(codigo);
    }
}
