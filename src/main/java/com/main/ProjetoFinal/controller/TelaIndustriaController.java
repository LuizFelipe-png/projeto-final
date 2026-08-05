/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.controller;

import com.main.ProjetoFinal.model.OperadorDTO;
import com.main.ProjetoFinal.model.UsuarioDTO;
import com.main.ProjetoFinal.service.EntregadorService;
import com.main.ProjetoFinal.service.OperadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class TelaIndustriaController {

    @Autowired
    private EntregadorService entregadorService;

    @Autowired
    private OperadorService operadorService;

    @GetMapping("/industria/enviar-tela")
    public String abrirTelaDeEnvio(Model model) {
        
        List<UsuarioDTO> entregadores = entregadorService.listarEntregadores();
        
        List<OperadorDTO> pedidos = operadorService.listarPedidosPendentes(); 

        model.addAttribute("listaEntregadores", entregadores);
        model.addAttribute("listaPedidos", pedidos);

        return "enviar-entregas"; 
    }
}
