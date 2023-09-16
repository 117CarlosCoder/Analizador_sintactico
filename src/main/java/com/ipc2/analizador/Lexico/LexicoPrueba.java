/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.analizador.Lexico;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author calin
 */
public class LexicoPrueba {
     public  void lexico(String sourceCode) {

        // Definir patrones regex para identificar tokens
        Pattern keywordPattern = Pattern.compile("\\b(as|then|print|assert|break|class|continue|def|del|end|elif|else|except|false|finally|from|global|import|in|is|lambda|none|nonlocal|pass|raise|return|true|try|with|yield|while|for|if)\\b");
        Pattern logicPattern = Pattern.compile("\\b(AND|OR|NOT)\\b");
        Pattern otherPattern = Pattern.compile("\\(|\\)|\\{|\\}|\\[|\\]|,|;|:");
        Pattern comparPattern = Pattern.compile("\\s*(=|<|>|<=|>=|!=)\\s*");
        Pattern identifierPattern = Pattern.compile("\\b[a-zA-Z_]\\w*\\b");
        Pattern operatorPattern = Pattern.compile("[+\\-*/]");
        Pattern integerPattern = Pattern.compile("\\b\\d+\\b");
        Pattern decimalPattern = Pattern.compile("\\b\\d+\\.\\d+\\b");
        Pattern stringPattern = Pattern.compile("\"[^\n\"]*\"");
        Pattern booleanPattern = Pattern.compile("\\b(true|false)\\b");
        Pattern commentPattern = Pattern.compile("#[^\n]*");
        Pattern unclosedStringPattern = Pattern.compile("\"[^\n\"]*");
        Pattern unknownPattern = Pattern.compile("\\S"); 


        Matcher matcher = Pattern.compile(
            "(" + keywordPattern.pattern() + ")"
            + "|(" + logicPattern.pattern() + ")"
            + "|(" + otherPattern.pattern() + ")"
            + "|(" + comparPattern.pattern() + ")"
            + "|(" + identifierPattern.pattern() + ")"
            + "|(" + operatorPattern.pattern() + ")"
            + "|(" + integerPattern.pattern() + ")"
            + "|(" + decimalPattern.pattern() + ")"
            + "|(" + stringPattern.pattern() + ")"
            + "|(" + booleanPattern.pattern() + ")"
            + "|(" + commentPattern.pattern() + ")"
            + "|(" + unclosedStringPattern.pattern() + ")"
            + "|(" + unknownPattern.pattern() + ")"
            
        ).matcher(sourceCode);

        while (matcher.find()) {
            String token = matcher.group();
            if (keywordPattern.matcher(token).matches()) {
                System.out.println("Palabra clave: " + token);
            } else if (comparPattern.matcher(token).matches()) {
                System.out.println("Comparacion: " + token);
            } else if (logicPattern.matcher(token).matches()) {
                System.out.println("Logico: " + token);
            } else if (otherPattern.matcher(token).matches()) {
                System.out.println("Otro: " + token);
            } else if (comparPattern.matcher(token).matches()) {
                System.out.println("Caparador: " + token);
            } else if (identifierPattern.matcher(token).matches()) {
                System.out.println("Identificador: " + token);
            } else if (operatorPattern.matcher(token).matches()) {
                System.out.println("Operador: " + token);
            } else if (integerPattern.matcher(token).matches()) {
                System.out.println("Constante Entero: " + token);
            } else if (decimalPattern.matcher(token).matches()) {
                System.out.println("Constante Decimal: " + token);
            } else if (stringPattern.matcher(token).matches()) {
                System.out.println("Constante Cadena: " + token);
            } else if (booleanPattern.matcher(token).matches()) {
                System.out.println("Constante Boleana: " + token);
            } else if (commentPattern.matcher(token).matches()) {
                System.out.println("Comentario: " + token);
            } else if (unclosedStringPattern.matcher(token).matches()) {
                System.out.println("Error Cadena: " + token);
            } else if (unknownPattern.matcher(token).matches()) {
                System.out.println("Error léxico: Token no reconocido en la posición : " + token);
                // Puedes tomar otras acciones, como lanzar una excepción o detener el análisis léxico.
            }  
            

        }
    }
}
