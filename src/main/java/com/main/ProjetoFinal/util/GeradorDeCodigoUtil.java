/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.ProjetoFinal.util;

import java.util.Random;

/**
 *
 * @author Aluno
 */
public class GeradorDeCodigoUtil {
    
    public static String geradorCodigo(){
        StringBuilder codigo = new StringBuilder();
        Random random = new Random();
        String caracteresValidos = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        
        for(int i = 0; i < 5; i++){
            int indiceSorteado = random.nextInt(caracteresValidos.length());
            char caracteresSorteado = caracteresValidos.charAt(indiceSorteado);
            codigo.append(caracteresSorteado);
        }
        String codigoFeito = codigo.toString();
        return codigoFeito;
    }
}
