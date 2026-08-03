/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.model;

import java.time.LocalDateTime;

/**
 *
 * @author Win
 */
public class HistoricoDTO {
    
    private int id_historico;
    private int id_pedido;
    private String descricao;
    private LocalDateTime data_hora;

    public HistoricoDTO() {
    }

    public HistoricoDTO(int id_historico, int id_pedido, String descricao, LocalDateTime data_hora) {
        this.id_historico = id_historico;
        this.id_pedido = id_pedido;
        this.descricao = descricao;
        this.data_hora = data_hora;
    }

    public int getId_historico() {
        return id_historico;
    }

    public void setId_historico(int id_historico) {
        this.id_historico = id_historico;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }
    
    
}
