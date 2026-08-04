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
    private String nome_cliente;
    private String email_cliente;
    private Long id_entregador;
    private String token;
    private String emailCliente;
    private String codigo_pin;
    private Integer tentativas_pin;

    public OperadorDTO() {
    }

    public OperadorDTO(int id_pedido, String nome_pedido, float peso, int quantidade, String status, String codigo, int id_cliente, String nome_cliente, String email_cliente, Long id_entregador, String token, String emailCliente, String codigo_pin, Integer tentativas_pin) {
        this.id_pedido = id_pedido;
        this.nome_pedido = nome_pedido;
        this.peso = peso;
        this.quantidade = quantidade;
        this.status = status;
        this.codigo = codigo;
        this.id_cliente = id_cliente;
        this.nome_cliente = nome_cliente;
        this.email_cliente = email_cliente;
        this.id_entregador = id_entregador;
        this.token = token;
        this.emailCliente = emailCliente;
        this.codigo_pin = codigo_pin;
        this.tentativas_pin = tentativas_pin;
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

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public Long getId_entregador() {
        return id_entregador;
    }

    public void setId_entregador(Long id_entregador) {
        this.id_entregador = id_entregador;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getCodigo_pin() {
        return codigo_pin;
    }

    public void setCodigo_pin(String codigo_pin) {
        this.codigo_pin = codigo_pin;
    }

    public Integer getTentativas_pin() {
        return tentativas_pin;
    }

    public void setTentativas_pin(Integer tentativas_pin) {
        this.tentativas_pin = tentativas_pin;
    }

    
}
