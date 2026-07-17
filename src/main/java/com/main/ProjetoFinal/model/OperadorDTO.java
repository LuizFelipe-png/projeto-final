/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.model;

/**
 *
 * @author Win
 */
public class OperadorDTO {
    
    private int id_pedido;
    private String nome_pedido;
    private float peso;
    private int quantidade;
    private String status;
    private String codigo;
    private int id_cliente;

    public OperadorDTO() {
    }

    public OperadorDTO(int id_pedido, String nome_pedido, float peso, int quantidade, String status, String codigo, int id_cliente) {
        this.id_pedido = id_pedido;
        this.nome_pedido = nome_pedido;
        this.peso = peso;
        this.quantidade = quantidade;
        this.status = status;
        this.codigo = codigo;
        this.id_cliente = id_cliente;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getNome_pedido() {
        return nome_pedido;
    }

    public void setNome_pedido(String nome_pedido) {
        this.nome_pedido = nome_pedido;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    
}
