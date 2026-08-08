package com.main.ProjetoFinal.controller;

import com.main.ProjetoFinal.model.AtribuirEntregadorRequestDTO;
import com.main.ProjetoFinal.model.OperadorDTO;
import com.main.ProjetoFinal.service.EntregadorService;
import com.main.ProjetoFinal.service.OperadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/industria")
public class OperadorController {

    @Autowired
    private OperadorService service;

    @Autowired
    private EntregadorService entregadorService;

    @GetMapping("/enviar")
    public String carregarTelaEnviar(Model model) {
        model.addAttribute("listaPedidos", service.listarPedidosPendentes());
        model.addAttribute("entregadores", entregadorService.listarEntregadores());
        return "enviar-entregas";
    }

    @PostMapping("/vincular")
    public String vincularEntregador(@RequestParam("id_pedido") int idPedido,
            @RequestParam("id_entregador") int idEntregador,
            RedirectAttributes redirectAttributes) {

        // 🔍 LINHAS DE DIAGNÓSTICO (Adicione aqui):
        System.out.println("====================================");
        System.out.println(">>> ID PEDIDO RECEBIDO DA TELA: " + idPedido);
        System.out.println(">>> ID ENTREGADOR RECEBIDO DA TELA: " + idEntregador);
        System.out.println("====================================");

        boolean sucesso = service.vincularEntregador(idPedido, idEntregador);

        if (sucesso) {
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Entrega atribuída ao entregador com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("erroServidor", "Erro ao atribuir entregador.");
        }
        return "redirect:/industria/enviar";
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
}
