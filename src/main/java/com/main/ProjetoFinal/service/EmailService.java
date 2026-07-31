package com.main.ProjetoFinal.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.mail.remetente}")
    private String remetente;

    public void enviarTokenEntrega(String emailDestinatario, String codigoLote, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(remetente);
            message.setTo(emailDestinatario);
            message.setSubject("Seu Token de Confirmação de Entrega - Lote #" + codigoLote);

            String corpoEmail = String.format(
                "Olá!\n\n" +
                "Seu pedido com o lote #%s está a caminho.\n\n" +
                "Apresente o token abaixo ao entregador para receber sua encomenda:\n\n" +
                "TOKEN: %s\n\n" +
                "Não compartilhe este código com terceiros por segurança.",
                codigoLote, token
            );

            message.setText(corpoEmail);
            mailSender.send(message);
            System.out.println("E-mail enviado com sucesso para: " + emailDestinatario);
        } catch (MailException e) {
            System.err.println("Erro ao enviar e-mail para " + emailDestinatario + ": " + e.getMessage());
        }
    }

    public void enviarEmailSmtp(String destinatario, String assunto, String conteudoHtml) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(remetente);
            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(conteudoHtml, true);
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Erro ao disparar SMTP: " + e.getMessage());
        }
    }
}