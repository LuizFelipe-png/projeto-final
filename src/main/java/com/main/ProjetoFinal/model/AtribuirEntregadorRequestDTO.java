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
    
    private int idEntregador;
    private int idEncomenda;

    public AtribuirEntregadorRequestDTO() {
    }

    public AtribuirEntregadorRequestDTO(int idEntregador, int idEncomenda) {
        this.idEntregador = idEntregador;
        this.idEncomenda = idEncomenda;
    }

    public int getIdEntregador() {
        return idEntregador;
    }

    public void setIdEntregador(int idEntregador) {
        this.idEntregador = idEntregador;
    }

    public int getIdEncomenda() {
        return idEncomenda;
    }

    public void setIdEncomenda(int idEncomenda) {
        this.idEncomenda = idEncomenda;
    }
    
    
}
