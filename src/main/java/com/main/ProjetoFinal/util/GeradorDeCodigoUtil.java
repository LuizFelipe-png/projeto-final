/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author Aluno
 */
public class GeradorDeCodigoUtil {

    private static final SecureRandom random = new SecureRandom();

    public static String geradorCodigo() {
        StringBuilder codigoRastreio = new StringBuilder();
        String caracteresValidos = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        for (int i = 0; i < 5; i++) {
            int indiceSorteado = random.nextInt(caracteresValidos.length());
            char caracterSorteado = caracteresValidos.charAt(indiceSorteado);
            codigoRastreio.append(caracterSorteado);
        }
        return codigoRastreio.toString();
    }
}
