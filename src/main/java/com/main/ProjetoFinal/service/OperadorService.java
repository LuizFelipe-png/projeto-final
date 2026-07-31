package com.main.ProjetoFinal.service;

import com.main.ProjetoFinal.model.OperadorDTO;
import com.main.ProjetoFinal.repository.OperadorRepository;
import com.main.ProjetoFinal.util.GeradorDeCodigoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperadorService {

    @Autowired
    private OperadorRepository dao;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;

    /**
     * Cadastra um novo lote no banco e envia o e-mail de confirmação de recebimento para o cliente.
     */
    public void cadastrarLote(String token, OperadorDTO operador) {
        tokenService.extrairClaims(token);

        int linhas = dao.cadastrarLote(operador, operador.getEmail_cliente());
        if (linhas == 0) {
            throw new RuntimeException("Erro ao cadastrar pedido.");
        }

        if (operador.getEmail_cliente() != null && !operador.getEmail_cliente().isBlank()) {
            try {
                String htmlCadastro = "<h2>Olá, " + operador.getNome_cliente() + "!</h2>"
                        + "<p>Seu pedido de <b>" + operador.getNome_pedido() + "</b> foi recebido pela nossa indústria.</p>"
                        + "<p>Código do lote: <b>" + operador.getCodigo() + "</b></p>";

                emailService.enviarEmailSmtp(
                        operador.getEmail_cliente(), 
                        "Pedido Recebido — Lote " + operador.getCodigo(), 
                        htmlCadastro
                );
            } catch (Exception e) {
                System.err.println("Erro ao enviar e-mail de cadastro: " + e.getMessage());
            }
        }
    }

    /**
     * Lista todos os pedidos cadastrados na base de dados.
     */
    public List<OperadorDTO> listarPedidos(String token) {
        tokenService.extrairClaims(token);
        return dao.listarPedidos();
    }

    /**
     * Despacha o lote para o entregador, gera o token de confirmação e avisa o cliente por e-mail.
     */
    public void despacharLote(String token, int idPedido, Long idEntregador) {
        tokenService.extrairClaims(token);

        // Gera token de segurança de 6 caracteres
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
                        + "<p>Seu pedido de código <b>" + pedido.getCodigo() + "</b> saiu para entrega.</p>"
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
}