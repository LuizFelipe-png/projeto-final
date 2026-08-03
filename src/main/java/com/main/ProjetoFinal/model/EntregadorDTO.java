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
    
    private int id_entregador;
    private String nome;
    private String veiculo;
    private String placa;

    public EntregadorDTO() {
    }

    public EntregadorDTO(int id_entregador, String nome, String veiculo, String placa) {
        this.id_entregador = id_entregador;
        this.nome = nome;
        this.veiculo = veiculo;
        this.placa = placa;
    }

    public int getId_entregador() {
        return id_entregador;
    }

    public void setId_entregador(int id_entregador) {
        this.id_entregador = id_entregador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
