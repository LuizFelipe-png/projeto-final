/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.repository;

import com.main.ProjetoFinal.model.IncidentesDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Win
 */
@Repository
public class IncidenteRepository {

    public int cadastrarIncidente(IncidentesDTO incidente) {
        try {
            Connection conn = Conexao.conectar();

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO incidentes (id_pedido, tipo, descricao, acao_tomada, data_ocorrencia) VALUES (?,?,?,?,?)");
            stmt.setInt(1, incidente.getId_pedido());
            stmt.setString(2, incidente.getTipo());
            stmt.setString(3, incidente.getDescricao());
            stmt.setString(4, incidente.getAcao_tomada());
            stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            int resultado = stmt.executeUpdate();
            System.out.println("aqui");
            System.out.println(resultado);
            stmt.close();

            String novoStatus = incidente.getTipo().equals("Problema no Caminhão") ? "Incidente de Transporte" : "Avaria na Carga";
            PreparedStatement stmtStatus = conn.prepareStatement("UPDATE pedidos SET status = ? WHERE id_pedido = ?");
            stmtStatus.setString(1, novoStatus);
            stmtStatus.setInt(2, incidente.getId_pedido());
            stmtStatus.executeUpdate();
            stmtStatus.close();

            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<IncidentesDTO> listarIncidentes() {
        List<IncidentesDTO> listar = new ArrayList<IncidentesDTO>();
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("SELECT i.*, p.codigo FROM incidentes i, pedidos p WHERE i.id_pedido = p.id_pedido ORDER BY i.data_ocorrencia DESC");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                IncidentesDTO incidente = new IncidentesDTO();
                incidente.setId_incidente(rs.getInt("id_incidente"));
                incidente.setId_pedido(rs.getInt("id_pedido"));
                incidente.setTipo(rs.getString("tipo"));
                incidente.setDescricao(rs.getString("descricao"));
                incidente.setAcao_tomada(rs.getString("acao_tomada"));
                incidente.setData_ocorrencia(rs.getTimestamp("data_ocorrencia").toLocalDateTime());

                listar.add(incidente);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listar;
    }
}
