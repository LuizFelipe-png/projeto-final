/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.model;

/**
 *
 * @author Aluno
 */
public class EmailRequestDTO {
    
    private String para;
    private String assunto;
    private String conteudoHtml;

    public EmailRequestDTO() {
    }

    public EmailRequestDTO(String para, String assunto, String conteudoHtml) {
        this.para = para;
        this.assunto = assunto;
        this.conteudoHtml = conteudoHtml;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getConteudoHtml() {
        return conteudoHtml;
    }

    public void setConteudoHtml(String conteudoHtml) {
        this.conteudoHtml = conteudoHtml;
    }

    
}
