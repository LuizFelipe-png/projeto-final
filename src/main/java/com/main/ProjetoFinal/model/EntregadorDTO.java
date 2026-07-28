/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.model;

/**
 *
 * @author Aluno
 */
public class EntregadorDTO {
    
    private Long id_entregador;
    private String veiculo;
    private String placa;

    public EntregadorDTO() {
    }

    public EntregadorDTO(Long id_entregador, String veiculo, String placa) {
        this.id_entregador = id_entregador;
        this.veiculo = veiculo;
        this.placa = placa;
    }

    public Long getId_entregador() {
        return id_entregador;
    }

    public void setId_entregador(Long id_entregador) {
        this.id_entregador = id_entregador;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    
}
