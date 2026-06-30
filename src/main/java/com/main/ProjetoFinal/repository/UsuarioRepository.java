/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.repository;

import com.main.ProjetoFinal.model.ClienteDTO;
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
public class ClienteRepository {
    
     public int cadastrar(ClienteDTO cliente) {
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            stmt = conn.prepareStatement("INSERT INTO cliente (nome, nome_usuario, email, telefone, senha) cliente VALUES (?,?,?,?,?)");
            
            rs = stmt.executeQuery();

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getUsuario());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getSenha());
                
                return stmt.executeUpdate();          
            
        }catch(SQLException e){
            e.printStackTrace();
        } 
        return 0;
    }
     
     public ClienteDTO logar(String email, String senha) {
        ClienteDTO cliente = new ClienteDTO();

        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conn.prepareStatement("SELECT * FROM cliente WHERE email = ? and senha = ?");

            stmt.setString(1, email);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();

            if (rs.next()) {
                cliente.setId(rs.getLong("id"));
                cliente.setEmail(rs.getString("email"));
                cliente.setNome(rs.getString("nome"));

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return cliente;

    }
}
