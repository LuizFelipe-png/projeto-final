/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.repository;

import com.main.ProjetoFinal.model.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */
@Repository
public class UsuarioRepository {

        public int cadastrar(UsuarioDTO cliente) {
            try {
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = null;

                stmt = conn.prepareStatement("INSERT INTO cliente (nome, nome_usuario, email, telefone, senha, role) VALUES (?,?,?,?,?,?)");

                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getNome_usuario());
                stmt.setString(3, cliente.getEmail());
                stmt.setString(4, cliente.getTelefone());
                stmt.setString(5, cliente.getSenha());
                stmt.setString(6, cliente.getRole());

                return stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }

        public UsuarioDTO login(String email, String senha) {
            UsuarioDTO cliente = new UsuarioDTO();

            try {
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = null;
                ResultSet rs = null;

                stmt = conn.prepareStatement("SELECT * FROM cliente WHERE email = ? and senha = ? and role = ?");

                stmt.setString(1, email);
                stmt.setString(2, senha);
                
                rs = stmt.executeQuery();

                if (rs.next()) {
                    cliente.setEmail(rs.getString("email"));
                    cliente.setSenha(rs.getString("senha"));
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }
            return cliente;

        }
    }

