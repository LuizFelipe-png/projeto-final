package com.main.ProjetoFinal.service;

import com.main.ProjetoFinal.model.HistoricoDTO;
import com.main.ProjetoFinal.model.OperadorDTO;
import com.main.ProjetoFinal.model.UsuarioDTO;
import com.main.ProjetoFinal.repository.EntregadorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

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

    public boolean registrarCheckpoint(int idPedido, String descricao) {
        return repository.registrarCheckpoint(idPedido, descricao);
    }

    public List<HistoricoDTO> listarHistorico(int idPedido) {
        return repository.listarHistorico(idPedido);
    }

    public List<UsuarioDTO> listarEntregadores(@RequestHeader("Authorization") String auth) {
        return repository.listarEntregadores();
    }

    public List<HistoricoDTO> listarTodoHistorico() {
        return repository.listarTodoHistorico();
    }
}
