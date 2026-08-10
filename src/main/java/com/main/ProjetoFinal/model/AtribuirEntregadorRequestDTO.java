/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.model;

/**
 *
 * @author Aluno
 */
public class AtribuirEntregadorRequestDTO {
    
    private Integer idEntregador;
    private Integer idEncomenda;

    public AtribuirEntregadorRequestDTO() {
    }

    public AtribuirEntregadorRequestDTO(Integer idEntregador, Integer idEncomenda) {
        this.idEntregador = idEntregador;
        this.idEncomenda = idEncomenda;
    }

    public Integer getIdEntregador() {
        return idEntregador;
    }

    public void setIdEntregador(Integer idEntregador) {
        this.idEntregador = idEntregador;
    }

    public Integer getIdEncomenda() {
        return idEncomenda;
    }

    public void setIdEncomenda(Integer idEncomenda) {
        this.idEncomenda = idEncomenda;
    }

    
}
