/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.controller;

import com.main.ProjetoFinal.service.EmailServicee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */
@RestController
@RequestMapping("/teste")
public class TesteEmailController {

    @Autowired
    private EmailServicee emailService;

    @GetMapping("/email")
    public String testarEmail() {
        emailService.enviarTokenEntrega("luizfelipemurarolli@gmail.com", "ABC123", "XYZ99");
        return "E-mail disparado, confira a caixa de entrada.";
    }
}