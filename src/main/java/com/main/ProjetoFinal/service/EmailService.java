/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aluno
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmailSmtp(String para, String assunto, String conteudoHtml) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        
        helper.setFrom("luizfelipemurarolli@gmail.com");
        helper.setTo(para);
        helper.setSubject(assunto);
        helper.setText(conteudoHtml, true);

        mailSender.send(message);
    }
}
