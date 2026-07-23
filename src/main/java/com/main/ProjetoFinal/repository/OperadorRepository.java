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
public class OperadorRepository {
    
   public List<OperadorDTO> listarPedidos(){
       List<OperadorDTO> listar = new ArrayList<OperadorDTO>();
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conn.prepareStatement("SELECT * FROM pedidos");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                OperadorDTO pedidos = new OperadorDTO();
                pedidos.setId_pedido(rs.getInt("id_pedido"));
                pedidos.setNome_pedido(rs.getString("nome_pedido"));
                pedidos.setPeso(rs.getFloat("peso"));
                pedidos.setQuantidade(rs.getInt("quantidade"));
                pedidos.setStatus(rs.getString("status"));
                pedidos.setCodigo(rs.getString("codigo"));
                pedidos.setId_cliente(rs.getInt("id_cliente"));
                
                listar.add(pedidos); 
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return listar;
    }

   public int cadastrarLote(OperadorDTO operador) {
        try {
            Connection conn = Conexao.conectar();

            int id_cliente = 0;
            PreparedStatement stmt1 = conn.prepareStatement("SELECT id_cliente FROM cliente WHERE email = ?");
            stmt1.setString(1, operador.getEmail_cliente());
            ResultSet rs = stmt1.executeQuery();
            if (rs.next()) {
                id_cliente = rs.getInt("id_cliente");
            } else {
                PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO cliente (nome, email) VALUES (?,?)");
                stmt2.setString(1, operador.getNome_cliente());
                stmt2.setString(2, operador.getEmail_cliente());
                stmt2.executeUpdate();

                PreparedStatement stmt3 = conn.prepareStatement("SELECT id_cliente FROM cliente WHERE email = ?");
                stmt3.setString(1, operador.getEmail_cliente());
                ResultSet rs2 = stmt3.executeQuery();
                if (rs2.next()) {
                    id_cliente = rs2.getInt("id_cliente");
                }
            }

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO pedidos (nome_pedido, peso, quantidade, status, codigo, id_cliente) VALUES (?,?,?,?,?,?)");
            stmt.setString(1, operador.getNome_pedido());
            stmt.setFloat(2, operador.getPeso());
            stmt.setInt(3, operador.getQuantidade());
            stmt.setString(4, operador.getStatus());
            stmt.setString(5, operador.getCodigo());
            stmt.setInt(6, id_cliente);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}