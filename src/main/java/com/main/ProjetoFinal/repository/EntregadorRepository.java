/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.repository;

import com.main.ProjetoFinal.model.OperadorDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Win
 */
@Repository
public class EntregadorRepository {
    
    public List<OperadorDTO> listarPedidosPorEntregador(Integer idEntregador) {
        List<OperadorDTO> listar = new ArrayList<>();
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pedidos WHERE id_entregador = ?");
            stmt.setInt(1, idEntregador);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OperadorDTO pedido = new OperadorDTO();
                pedido.setId_pedido(rs.getInt("id_pedido"));
                pedido.setNome_pedido(rs.getString("nome_pedido"));
                pedido.setPeso(rs.getFloat("peso"));
                pedido.setQuantidade(rs.getInt("quantidade"));
                pedido.setStatus(rs.getString("status"));
                pedido.setCodigo(rs.getString("codigo"));
                pedido.setNome_cliente(rs.getString("nome_cliente"));
                //pedido.setEmail_cliente(rs.getString("email_cliente"));
                //pedido.setId_entregador(rs.getLong("id_entregador"));
                //pedido.setToken(rs.getString("token"));
                listar.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listar;
    }

    public boolean confirmarEntrega(int idPedido, String tokenDigitado) {
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("SELECT token FROM pedidos WHERE id_pedido = ?");
            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String tokenReal = rs.getString("token");
                if (tokenReal != null && tokenReal.equals(tokenDigitado)) {
                    PreparedStatement update = conn.prepareStatement("UPDATE pedidos SET status = 'Entregue' WHERE id_pedido = ?");
                    update.setInt(1, idPedido);
                    update.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
