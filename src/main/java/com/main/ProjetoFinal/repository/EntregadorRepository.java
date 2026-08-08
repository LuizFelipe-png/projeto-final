package com.main.ProjetoFinal.repository;

import com.main.ProjetoFinal.model.HistoricoDTO;
import com.main.ProjetoFinal.model.OperadorDTO;
import com.main.ProjetoFinal.model.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class EntregadorRepository {

    public List<OperadorDTO> listarPedidosPorEntregador(Integer idEntregador) {
        List<OperadorDTO> listar = new ArrayList<OperadorDTO>();
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.nome AS nome_cliente, c.email AS email_cliente FROM pedidos p LEFT JOIN cliente c ON p.id_cliente = c.id_cliente WHERE p.id_entregador = ?");
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
                pedido.setEmail_cliente(rs.getString("email_cliente"));

                listar.add(pedido);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listar;
    }

    public boolean confirmarEntrega(int idPedido, String tokenDigitado) {
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("SELECT token FROM pedidos WHERE id_pedido = ? AND status = 'Em Rota'");
            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String tokenReal = rs.getString("token");
                if (tokenReal != null && tokenReal.equals(tokenDigitado)) {
                    PreparedStatement update = conn.prepareStatement("UPDATE pedidos SET status = 'Entregue' WHERE id_pedido = ? AND status = 'Em Rota'");
                    update.setInt(1, idPedido);
                    boolean sucesso = update.executeUpdate() > 0;
                    update.close();

                    if (sucesso) {
                        registrarHistorico(conn, idPedido, "Entrega confirmada pelo entregador.");
                    }
                    rs.close();
                    stmt.close();
                    conn.close();
                    return sucesso;
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean registrarCheckpoint(int idPedido, String descricao) {
        try {
            Connection conn = Conexao.conectar();
            registrarHistorico(conn, idPedido, descricao);
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void registrarHistorico(Connection conn, int idPedido, String descricao) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO historico_pedido (id_pedido, descricao, data_hora) VALUES (?, ?, ?)");
        stmt.setInt(1, idPedido);
        stmt.setString(2, descricao);
        stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
        stmt.executeUpdate();
        stmt.close();
    }

    public List<HistoricoDTO> listarHistorico(int idPedido) {
        List<HistoricoDTO> lista = new ArrayList<HistoricoDTO>();
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM historico_pedido WHERE id_pedido = ? ORDER BY data_hora ASC");
            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                HistoricoDTO h = new HistoricoDTO();
                h.setId_historico(rs.getInt("id_historico"));
                h.setId_pedido(rs.getInt("id_pedido"));
                h.setDescricao(rs.getString("descricao"));
                h.setData_hora(rs.getTimestamp("data_hora").toLocalDateTime());
                lista.add(h);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<UsuarioDTO> listarEntregadores() {
        List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuario WHERE UPPER(role) = 'ENTREGADOR'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UsuarioDTO user = new UsuarioDTO();
                user.setId(rs.getLong("id_usuario"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setTelefone(rs.getString("telefone"));
                user.setSenha(rs.getString("senha"));
                user.setRole(rs.getString("role"));
                lista.add(user);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public UsuarioDTO buscarEntregadorPorId(Integer id_Usuario) {
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuario WHERE id_usuario = ?");
            stmt.setInt(1, id_Usuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                UsuarioDTO user = new UsuarioDTO();
                user.setId(rs.getLong("id_usuario"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setTelefone(rs.getString("telefone"));
                user.setSenha(rs.getString("senha"));
                user.setRole(rs.getString("role"));

                rs.close();
                stmt.close();
                conn.close();

                return user;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Usuário não encontrado!");
    }
}