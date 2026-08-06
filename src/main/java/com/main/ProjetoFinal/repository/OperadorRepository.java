package com.main.ProjetoFinal.repository;

import com.main.ProjetoFinal.model.OperadorDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class OperadorRepository {

    public List<OperadorDTO> listarPedidos() {
        List<OperadorDTO> listar = new ArrayList<>();
        String sql = "SELECT p.*, c.nome AS nome_cliente, c.email AS email_cliente "
                   + "FROM pedidos p LEFT JOIN cliente c ON p.id_cliente = c.id_cliente";

        try (Connection conn = Conexao.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                OperadorDTO pedidos = new OperadorDTO();
                pedidos.setId_pedido(rs.getInt("id_pedido"));
                pedidos.setNome_pedido(rs.getString("nome_pedido"));
                pedidos.setPeso(rs.getFloat("peso"));
                pedidos.setQuantidade(rs.getInt("quantidade"));
                pedidos.setStatus(rs.getString("status"));
                pedidos.setCodigo(rs.getString("codigo"));
                pedidos.setId_cliente(rs.getInt("id_cliente"));
                pedidos.setNome_cliente(rs.getString("nome_cliente"));
                pedidos.setEmail_cliente(rs.getString("email_cliente"));
                
                long idEntregador = rs.getLong("id_entregador");
                pedidos.setId_entregador(rs.wasNull() ? null : idEntregador);
                pedidos.setToken(rs.getString("token"));
                
                listar.add(pedidos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listar;
    }

    public int cadastrarLote(OperadorDTO operador, String emailCliente) {
        try (Connection conn = Conexao.conectar(); 
             PreparedStatement stmt1 = conn.prepareStatement("SELECT id_cliente FROM cliente WHERE email = ?")) {

            int id_cliente = 0;

            stmt1.setString(1, operador.getEmail_cliente());
            try (ResultSet rs = stmt1.executeQuery()) {
                if (rs.next()) {
                    id_cliente = rs.getInt("id_cliente");
                } else {
                    try (PreparedStatement stmt2 = conn.prepareStatement(
                            "INSERT INTO cliente (nome, email) VALUES (?,?)",
                            java.sql.Statement.RETURN_GENERATED_KEYS)) {
                        stmt2.setString(1, operador.getNome_cliente());
                        stmt2.setString(2, operador.getEmail_cliente());
                        stmt2.executeUpdate();

                        try (ResultSet generatedKeys = stmt2.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                id_cliente = generatedKeys.getInt(1);
                            }
                        }
                    }
                }
            }

            try (PreparedStatement stmt3 = conn.prepareStatement(
                    "INSERT INTO pedidos (nome_pedido, peso, quantidade, status, codigo, id_cliente) VALUES (?,?,?,?,?,?)")) {
                stmt3.setString(1, operador.getNome_pedido());
                stmt3.setFloat(2, operador.getPeso());
                stmt3.setInt(3, operador.getQuantidade());
                stmt3.setString(4, operador.getStatus());
                stmt3.setString(5, operador.getCodigo());
                stmt3.setInt(6, id_cliente);
                return stmt3.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int despacharLote(int idPedido, Long idEntregador, String token) {
        String sql = "UPDATE pedidos SET status = 'Em Rota', id_entregador = ?, token = ? WHERE id_pedido = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, idEntregador);
            stmt.setString(2, token);
            stmt.setInt(3, idPedido);
            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public OperadorDTO buscarPorId(int idPedido) {
        String sql = "SELECT p.*, c.nome AS nome_cliente, c.email AS email_cliente "
                   + "FROM pedidos p "
                   + "LEFT JOIN cliente c ON p.id_cliente = c.id_cliente "
                   + "WHERE p.id_pedido = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    OperadorDTO pedido = new OperadorDTO();
                    pedido.setId_pedido(rs.getInt("id_pedido"));
                    pedido.setNome_pedido(rs.getString("nome_pedido"));
                    pedido.setStatus(rs.getString("status"));
                    pedido.setCodigo(rs.getString("codigo"));
                    pedido.setNome_cliente(rs.getString("nome_cliente"));
                    pedido.setEmail_cliente(rs.getString("email_cliente"));
                    return pedido;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public OperadorDTO atribuirEncomenda(Integer Id_Pedido, Integer Id_Entregador){
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement("UPDATE pedidos SET id_entregador = ? WHERE id_pedido = ?")) {
            
            stmt.setInt(1, Id_Entregador);
            stmt.setInt(2, Id_Pedido);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao alterar o dado, tente novamente!");
            }
            return buscarPorId(Id_Pedido);
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<OperadorDTO> buscarPedidosPorEntregador(Integer idEntregador) {
        List<OperadorDTO> lista = new ArrayList<>();
        String sql = "SELECT p.*, c.email AS email_cliente "
                   + "FROM pedidos p "
                   + "LEFT JOIN cliente c ON p.id_cliente = c.id_cliente "
                   + "WHERE p.id_entregador = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEntregador);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OperadorDTO encomenda = new OperadorDTO();
                    encomenda.setId_pedido(rs.getInt("id_pedido"));
                    encomenda.setCodigo(rs.getString("codigo"));
                    encomenda.setStatus(rs.getString("status"));
                    encomenda.setEmail_cliente(rs.getString("email_cliente"));
                    
                    long idEnt = rs.getLong("id_entregador");
                    encomenda.setId_entregador(rs.wasNull() ? null : idEnt);
                    
                    lista.add(encomenda);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<OperadorDTO> listarPedidosPendentes() {
        List<OperadorDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos WHERE id_entregador is null";

        try (Connection conn = Conexao.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                OperadorDTO pedido = new OperadorDTO();
                pedido.setId_pedido(rs.getInt("id_pedido"));
                pedido.setNome_pedido(rs.getString("nome_pedido"));
                pedido.setPeso(rs.getFloat("peso"));
                pedido.setQuantidade(rs.getInt("quantidade"));
                pedido.setStatus(rs.getString("status"));
                pedido.setCodigo(rs.getString("codigo"));
                pedido.setId_cliente(rs.getInt("id_cliente"));
                pedido.setNome_cliente(rs.getString("nome_cliente"));
                pedido.setEmail_cliente(rs.getString("email_cliente"));
                
                lista.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean vincularEntregador(int idPedido, int idEntregador) {
        String sql = "UPDATE pedidos SET id_entregador = ?, status = 'Em Rota' WHERE id_pedido = ?";

        try (Connection conn = Conexao.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEntregador);
            stmt.setInt(2, idPedido);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}