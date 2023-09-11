/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.analizador.Lexico;

import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoAritmetico;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoAsignacion;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoComentario;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoComparacion;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoConstante;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoEspacio;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoIdentificador;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoLogico;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoOtro;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoPalabraRes;
import com.ipc2.analizador.Lexico.Token.Token;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author carlos117
 */ 
public class Lexico {
    private final String input;
    public static int columna;
    public static int fila;
    private int position;
    private final ArrayList<Object> palabrasG;
    
    public Lexico(String input) {
        this.palabrasG = new ArrayList<>();
        this.input = input;
        this.position = 0;
        this.columna = 0;
        this.fila = 1;
    }

    private char currentChar() {
        if (position < input.length()) {
            return input.charAt(position);
        }
        return '\0'; // Carácter nulo para indicar fin de archivo
    }

    private void advance() {
        position++;
    }

    public Token nextToken() {
        while (currentChar() != '\0') {
            char current = currentChar();

            if (Character.isLetter(current))
            {
                return readLetter();
            }
            if (Character.isDigit(current)) {
                return readInteger();
            }
            if (current == '"') {
                 advance();
                
                columna++;
                return cadenaComDob(current);
            }
            if (current == '\'') {
                advance();    
                
                columna++;
                return cadena(current);
                
            }
            if (current == '{') {
                advance();
                
                columna++;
                return new Token(TipoOtro.COROPN, "{");
            }
            if (current == '(') {
                advance();
                
                columna++;
                return new Token(TipoOtro.PAROPN, "(");
            }
            if (current == ' ') {
                advance();
                
                columna++;
                return new Token(TipoEspacio.SPACE, " ");
            }
            if(Character.isWhitespace(current)){
                advance();
                
                fila++;
                columna = 0;
                return new Token(TipoEspacio.SALTO, "SALTO");
            }
            if (current == ')') {
                advance();
                
                columna++;
                return new Token(TipoOtro.PARCLS, ")");
            }
            if(current == '}'){
                advance();
                
                columna++;
                return new Token(TipoOtro.CORCLS, "}");
            }
            if (current == '=') {
                advance();
                current = currentChar();
                columna++;
                if (current == '=') {
                    advance();
                    
                    columna++;
                    return new Token(TipoComparacion.IGUAL, "==");
                }
                return new Token(TipoAsignacion.ASIG, "=");
            }
            if (current == '!') {
                advance();
                current = currentChar();
                columna++;
                if (current == '=') {
                    advance();
                    
                    columna++;
                    return new Token(TipoComparacion.DIFERENTE, "!=");
                }
                return new Token(TipoAsignacion.ASIG, "!");
            }
            if (current == '>') {
                advance();
                current = currentChar();
                columna++;
                if (current == '=') {
                    advance();
                    
                    columna++;
                    return new Token(TipoComparacion.OPRMYI, ">=");
                }
                
                return new Token(TipoComparacion.OPRMYQ, ">");
            }
            if (current == '<') {
                advance();
                current = currentChar();
                columna++;
                if (current == '=') {
                    advance();
                    
                    columna++;
                    return new Token(TipoComparacion.OPRMNI, "<=");
                }
                
                return new Token(TipoComparacion.OPRMNQ, "<");
            }
            if (current == ',') {
                advance();
                
                columna++;
                return new Token(TipoOtro.COMA, ",");
            }
            if (current == ';') {
                advance();
                
                columna++;
                return new Token(TipoOtro.PUNTCOMA, ":");
            }
            if (current == ':') {
                advance();
                
                columna++;
                return new Token(TipoOtro.PUNTDOB, ":");
            }
            if (current == '+') {
                advance();
                
                columna++;
                return new Token(TipoAritmetico.SUMA, "+");
            }  
            if (current == '-') {
                advance();
                
                columna++;
                return new Token(TipoAritmetico.RES, "-");
            } 
            if (current == '*') {
                advance();
                
                current = currentChar();
                columna++;
                if (current == '*') {
                    advance();
                    
                    columna++;
                    return new Token(TipoAritmetico.EXP, "**");
                }
                return new Token(TipoAritmetico.MULTI, "*");
            } 
            
            if (current == '/') {
                advance();
                
                current = currentChar();
                columna++;
                if (current == '/') {
                    advance();
                    
                    columna++;
                    return new Token(TipoAritmetico.DIVISION, "//");
                }
                return new Token(TipoAritmetico.DIVISION, "/");
            }
            if (current == '#') {
                advance();
                columna++;
                
                return comentario();
            }
             else {
                // Error: Carácter desconocido
                advance();
                
                return new Token(TipoOtro.ERROR,"Caracter Desconocido");
            }
        }
        
        fila++;
        columna = 0;
        System.out.println("Posicion columna : " + columna + " fila: " + fila );
        return new Token(TipoEspacio.EOF, "");
    }

    private Token readInteger() {
        StringBuilder value = new StringBuilder();

        while (Character.isDigit(currentChar())) {
            value.append(currentChar());
            advance();
            columna++;
        }
        
        if(currentChar() == '.'){
            value.append(currentChar());
            advance();
            columna++;
            while (Character.isDigit(currentChar())) {
                value.append(currentChar());
                advance();
                columna++;
            }
            return new Token(TipoConstante.DECIMAL, value.toString());         
        }
        //palabrasGuardadas(String.valueOf(value.toString()));
        return new Token(TipoConstante.INTEGER, value.toString());
    }

    private Token readLetter() {
        StringBuilder value = new StringBuilder();

        while (Character.isLetter(currentChar()) || currentChar() == '_' || Character.isDigit(currentChar())) {
            value.append(currentChar());
            advance();
            columna++;
        }
        
        String valuestring = value.toString();
        Map<String, TipoPalabraRes> palabraResMap = new HashMap<>();
        palabraResMap.put("as", TipoPalabraRes.AS);
        palabraResMap.put("assert", TipoPalabraRes.ASSERT);
        palabraResMap.put("break", TipoPalabraRes.BREAK);
        palabraResMap.put("class", TipoPalabraRes.CLASS);
        palabraResMap.put("continue", TipoPalabraRes.CONTINUE);
        palabraResMap.put("def", TipoPalabraRes.DEF);
        palabraResMap.put("del", TipoPalabraRes.DEL);
        palabraResMap.put("for", TipoPalabraRes.FOR);
        palabraResMap.put("elif", TipoPalabraRes.ELIF);
        palabraResMap.put("else", TipoPalabraRes.ELSE);
        palabraResMap.put("except", TipoPalabraRes.EXCEPT);
        palabraResMap.put("finally", TipoPalabraRes.FINALLY);
        palabraResMap.put("from", TipoPalabraRes.FROM);
        palabraResMap.put("global", TipoPalabraRes.GLOBAL);
        palabraResMap.put("import", TipoPalabraRes.IMPORT);
        palabraResMap.put("in", TipoPalabraRes.IN);
        palabraResMap.put("is", TipoPalabraRes.IS);
        palabraResMap.put("if", TipoPalabraRes.IF);
        palabraResMap.put("lambda", TipoPalabraRes.LAMBDA);
        palabraResMap.put("None", TipoPalabraRes.NONE);
        palabraResMap.put("nonlocal", TipoPalabraRes.NONLOCAL);
        palabraResMap.put("pass", TipoPalabraRes.PASS);
        palabraResMap.put("raise", TipoPalabraRes.RAISE);
        palabraResMap.put("return", TipoPalabraRes.RETURN);
        palabraResMap.put("try", TipoPalabraRes.TRY);
        palabraResMap.put("while", TipoPalabraRes.WHILE);
        palabraResMap.put("with", TipoPalabraRes.WITH);
        palabraResMap.put("yield", TipoPalabraRes.YIELD);
        
        
        if (palabraResMap.containsKey(valuestring)) {
            TipoPalabraRes tipoPalabraRes = palabraResMap.get(valuestring);
            System.out.println("Posicion columna : " + columna + " fila: " + fila );
            return new Token(tipoPalabraRes, valuestring);
        }
        
        if (value.toString().equals("and")){
            System.out.println("Posicion columna : " + columna + " fila: " + fila );
            return new Token(TipoLogico.AND, valuestring);
        }
        
        if (value.toString().equals("not")){
            System.out.println("Posicion columna : " + columna + " fila: " + fila );
            return new Token(TipoLogico.NOT, valuestring);
        }
        
        if (value.toString().equals("or")){
            System.out.println("Posicion columna : " + columna + " fila: " + fila );
            return new Token(TipoLogico.OR, valuestring);
        }
        if (value.toString().equals("True")){
            System.out.println("Posicion columna : " + columna + " fila: " + fila );
            return new Token(TipoConstante.BOOLEAN, valuestring);
        }
        if (value.toString().equals("False")){
            System.out.println("Posicion columna : " + columna + " fila: " + fila );
            return new Token(TipoConstante.BOOLEAN, valuestring);
        }
        
        System.out.println("Posicion columna : " + columna + " fila: " + fila );
        return new Token(TipoIdentificador.ID, valuestring);
    }
    
    public Token comentario(){
       
        StringBuilder value = new StringBuilder();
        
        while (Character.isLetter(currentChar()) || Character.isAlphabetic(currentChar()) || Character.isDigit(currentChar()) || Character.isWhitespace(currentChar()) && currentChar() != '\n') {
            value.append(currentChar());
            advance();
            System.out.println("Valor : " + currentChar());
            columna++;
        }
        
        System.out.println(currentChar());
        if(Character.isWhitespace(currentChar()) && currentChar() == '\n'){
            return new Token(TipoComentario.COMENT, "#" + value.toString());
        }
        
        
        return new Token(TipoOtro.ERROR, value.toString());

    }
    
    public Token cadena(char current){
        StringBuilder value = new StringBuilder();
        value.append(current);
        while (Character.isLetter(currentChar()) || Character.isAlphabetic(currentChar()) || Character.isDigit(currentChar()) || Character.isWhitespace(currentChar()) && currentChar() != '\n') {
            value.append(currentChar());
            advance();
            System.out.println("Valor : " + currentChar());
            columna++;
        }
        
        if (currentChar() == '\'') {
            value.append(currentChar());
            advance();
            System.out.println("Posicion columna : " + columna + " fila: " + fila );
            current++;
            return new Token(TipoConstante.CADENA, value.toString());            
        }

        return new Token(TipoOtro.ERROR, value.toString());        
    } 
    
    public Token cadenaComDob(char current){
        StringBuilder value = new StringBuilder();
        value.append(current);
        while (Character.isLetter(currentChar()) || Character.isAlphabetic(currentChar()) || Character.isDigit(currentChar()) || Character.isWhitespace(currentChar()) && currentChar() != '\n') {
            value.append(currentChar());
            advance();
            columna++;
            System.out.println("Valor : " + currentChar());
        }
        
        if (currentChar() == '"') {
            value.append(currentChar());
            advance();
            current++;
            System.out.println("Posicion columna : " + columna + " fila: " + fila );
            return new Token(TipoConstante.CADENA, value.toString());            
        }
       
        
        return new Token(TipoOtro.ERROR, value.toString());        
    } 
    
    public int fila(){
        return fila;
    }
    
    public int columna(){
        return columna;
    }
    
    
}
