/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.service;

import com.main.ProjetoFinal.model.OperadorDTO;
import com.main.ProjetoFinal.repository.EntregadorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aluno
 */
@Service
public class EntregadorService {

    @Autowired
    private EntregadorRepository repository;

    public List<OperadorDTO> listarPedidosPorEntregador(Integer idEntregador) {
        return repository.listarPedidosPorEntregador(idEntregador);
    }

    public boolean confirmarEntrega(int idPedido, String token) {
        return repository.confirmarEntrega(idPedido, token);
    }
}
