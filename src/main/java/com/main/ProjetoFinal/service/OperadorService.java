package com.main.ProjetoFinal.service;

import com.main.ProjetoFinal.model.OperadorDTO;
import com.main.ProjetoFinal.model.UsuarioDTO;
import com.main.ProjetoFinal.repository.OperadorRepository;
import com.main.ProjetoFinal.repository.UsuarioRepository;
import com.main.ProjetoFinal.util.GeradorDeCodigoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OperadorService {

    @Autowired
    private OperadorRepository dao;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailServicee emailService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void cadastrarLote(String token, OperadorDTO operador) {
        tokenService.extrairClaims(token);

        String codigoGerado = GeradorDeCodigoUtil.geradorCodigo();
        operador.setCodigo(codigoGerado);

        int linhas = dao.cadastrarLote(operador, operador.getEmail_cliente());
        if (linhas == 0) {
            throw new RuntimeException("Erro ao cadastrar pedido.");
        }
        if (operador.getEmail_cliente() != null && !operador.getEmail_cliente().isBlank()) {
            try {
                String htmlCadastro = "<h2>Olá, " + operador.getNome_cliente() + "!</h2>"
                        + "<p>Seu pedido de <b>" + operador.getNome_pedido() + "</b> foi recebido pela nossa indústria.</p>"
                        + "<p>Código de rastreamento: <b>" + codigoGerado + "</b></p>";
                emailService.enviarEmailSmtp(
                        operador.getEmail_cliente(),
                        "Pedido Recebido — Lote " + codigoGerado,
                        htmlCadastro
                );
            } catch (Exception e) {
                System.err.println("Erro ao enviar e-mail de cadastro: " + e.getMessage());
            }
        }
    }

    public List<OperadorDTO> listarPedidos(String token) {
        tokenService.extrairClaims(token);
        return dao.listarPedidos();
    }

    public void despacharLote(String token, int idPedido, Long idEntregador) {
        tokenService.extrairClaims(token);

        String tokenEntrega = GeradorDeCodigoUtil.geradorCodigo();

        int lines = dao.despacharLote(idPedido, idEntregador, tokenEntrega);
        if (lines == 0) {
            throw new RuntimeException("Erro ao despachar lote. ID de pedido não encontrado.");
        }

        OperadorDTO pedido = dao.buscarPorId(idPedido);
        if (pedido != null && pedido.getEmail_cliente() != null && !pedido.getEmail_cliente().isBlank()) {
            try {
                String htmlDespacho = "<h2>Seu pedido está a caminho!</h2>"
                        + "<p>Olá, " + pedido.getNome_cliente() + "!</p>"
                        + "</b> o seu pedido saiu para entrega</p>"
                        + "<p>Quando o entregador chegar, informe este token de segurança para validar o recebimento: <strong>"
                        + tokenEntrega + "</strong></p>";

                emailService.enviarEmailSmtp(
                        pedido.getEmail_cliente(),
                        "Seu pedido saiu para entrega!",
                        htmlDespacho
                );
            } catch (Exception e) {
                System.err.println("Erro ao enviar e-mail de despacho: " + e.getMessage());
            }
        }
    }

    public OperadorDTO atribuirEntregador(Integer idEncomenda, Integer idEntregador) {
        if (idEncomenda == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Encomenda inválida, tente novamente!");
        }
        if (idEntregador == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Entregador inválido, tente novamente!");
        }
        UsuarioDTO entregador = usuarioRepository.buscarIdUsuario(idEntregador);
        if (!entregador.getRole().equals("Entregador")) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Esse id não é de um entregador, tente novamente!");
        }

        String tokenEntrega = GeradorDeCodigoUtil.geradorCodigo();
        OperadorDTO pedido = dao.atribuirEncomenda(idEncomenda, idEntregador, tokenEntrega);

        if (pedido != null && pedido.getEmail_cliente() != null && !pedido.getEmail_cliente().isBlank()) {
            emailService.enviarTokenEntrega(pedido.getEmail_cliente(), pedido.getCodigo(), tokenEntrega);
        }

        return pedido;
    }

    public List<OperadorDTO> buscarEncomendaPorEntregador(Integer idEntregador) {
        if (idEntregador == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Entregador inválido, tente novamente!");
        }
        UsuarioDTO entregador = usuarioRepository.buscarIdUsuario(idEntregador);
        if (!entregador.getRole().equals("Entregador")) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Esse id não é de um entregador, tente novamente!");
        }

        return dao.buscarPedidosPorEntregador(idEntregador);
    }

    public List<OperadorDTO> listarPedidosPendentes() {
        return dao.listarPedidosPendentes();
    }

    public boolean vincularEntregador(int idPedido, int idEntregador) {
        boolean resultado = dao.vincularEntregador(idPedido, idEntregador);
        return resultado;
    }

    public void atualizarStatus(String token, int idPedido, String novoStatus, String localizacao) {
        tokenService.extrairClaims(token);

        int linhas = dao.atualizarStatus(idPedido, novoStatus, localizacao);

        if (linhas == 0) {
            throw new RuntimeException("Erro ao atualizar status.");
        }
    }

    public OperadorDTO buscarPorCodigo(String codigo) {
        return dao.buscarPorCodigo(codigo);
    }
}
