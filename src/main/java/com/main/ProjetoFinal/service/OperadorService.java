package com.main.ProjetoFinal.service;

import com.main.ProjetoFinal.model.OperadorDTO;
import com.main.ProjetoFinal.repository.OperadorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OperadorService {

    @Autowired
    private OperadorRepository dao;

    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private EmailService emailService;

    public void cadastrarLote(String token, OperadorDTO operador) {
        tokenService.extrairClaims(token);

        int linhas = dao.cadastrarLote(operador, operador.getEmail_cliente());
        if (linhas == 0) {
            throw new RuntimeException("Erro ao cadastrar pedido.");
        }

        try {
            String htmlCadastro = "<h2>Olá, " + operador.getNome_cliente() + "!</h2>"
                    + "<p>Seu pedido de <b>" + operador.getNome_pedido() + "</b> foi recebido pela nossa indústria.</p>"
                    + "<p>Código do lote: <b>" + operador.getCodigo() + "</b></p>";

            emailService.enviarEmailSmtp(operador.getEmail_cliente(), "Pedido Recebido — Lote " + operador.getCodigo(), htmlCadastro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<OperadorDTO> listarPedidos(String token, Object ignorado) {
        tokenService.extrairClaims(token);
        return dao.listarPedidos();
    }

    public void despacharLote(String token, int idPedido, Long idEntregador) {
        tokenService.extrairClaims(token);

        String tokenEntrega = com.main.ProjetoFinal.util.GeradorDeCodigoUtil.geradorCodigo();

        int lines = dao.despacharLote(idPedido, idEntregador, tokenEntrega);
        if (lines == 0) {
            throw new RuntimeException("Erro ao despachar lote.");
        }

        OperadorDTO pedido = dao.buscarPorId(idPedido);
        if (pedido != null && pedido.getEmail_cliente() != null) {
            try {
                String htmlDespacho = "<h2>Seu pedido está a caminho!</h2>"
                        + "<p>Olá, " + pedido.getNome_cliente() + "!</p>"
                        + "<p>Seu pedido de código <b>" + pedido.getCodigo() + "</b> saiu para entrega.</p>"
                        + "<p>Quando o entregador chegar, informe este token de segurança para validar o recebimento: <strong>" + tokenEntrega + "</strong></p>";

                emailService.enviarEmailSmtp(pedido.getEmail_cliente(), "Seu pedido saiu para entrega!", htmlDespacho);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
