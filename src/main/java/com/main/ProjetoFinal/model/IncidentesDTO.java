/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author Win
 */
public class IncidentesDTO {

    private int id_incidente;
    private Integer id_pedido;
    private String tipo;
    private String descricao;
    private String acao_tomada;
    private LocalDateTime data_ocorrencia;
    private String nome_entregador;

    public IncidentesDTO() {
    }

    public IncidentesDTO(int id_incidente, Integer id_pedido, String tipo, String descricao, String acao_tomada, LocalDateTime data_ocorrencia, String nome_entregador) {
        this.id_incidente = id_incidente;
        this.id_pedido = id_pedido;
        this.tipo = tipo;
        this.descricao = descricao;
        this.acao_tomada = acao_tomada;
        this.data_ocorrencia = data_ocorrencia;
        this.nome_entregador = nome_entregador;
    }

    public int getId_incidente() {
        return id_incidente;
    }

    public void setId_incidente(int id_incidente) {
        this.id_incidente = id_incidente;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAcao_tomada() {
        return acao_tomada;
    }

    public void setAcao_tomada(String acao_tomada) {
        this.acao_tomada = acao_tomada;
    }

    public LocalDateTime getData_ocorrencia() {
        return data_ocorrencia;
    }

    public void setData_ocorrencia(LocalDateTime data_ocorrencia) {
        this.data_ocorrencia = data_ocorrencia;
    }

    public String getNome_entregador() {
        return nome_entregador;
    }

    public void setNome_entregador(String nome_entregador) {
        this.nome_entregador = nome_entregador;
    }

    
}