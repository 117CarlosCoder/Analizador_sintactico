/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.analizador.Sintactico;

import com.ipc2.analizador.Lexico.LexicoPrueba;
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
import com.ipc2.analizador.Lexico.Token.TokenPrueba;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author carlos117
 */
/**
 * Analizador sintáctico simple.
 */
public class AnalizadorSintactico {

    private final LexicoPrueba lexico;
    private TokenPrueba token;
    private final Stack<Object> pila;
    private final List<List<TokenPrueba>> tokens;
    private final List<Object> tokensFiltrados;
    private boolean Busqueda = true;

    // Define las reglas de producción como una matriz de tokens
    private final Object[][] reglasDeProduccion = {
        {TipoPalabraRes.VAR, TipoIdentificador.ID, TipoAsignacion.ASIG, TipoConstante.CADENA},
        {TipoPalabraRes.VAR, TipoIdentificador.ID, TipoAsignacion.ASIG, TipoConstante.INTEGER},
        {TipoIdentificador.ID, TipoAsignacion.ASIG, TipoConstante.CADENA},
        {TipoIdentificador.ID, TipoAsignacion.ASIG, TipoConstante.INTEGER}
    };

    public AnalizadorSintactico(String input, List<List<TokenPrueba>> tokens) {
        this.lexico = new LexicoPrueba(input);
        this.pila = new Stack<>();
        this.tokens = tokens;
        this.tokensFiltrados = new ArrayList<>();

    }

    public boolean analizar() {

        int posicionReglas = 0; // Variable para rastrear la posición en las reglas de producción 
        int posicionRegla = 0; // Variable para rastrear la posición en las reglas de producción
        Object[] reglaActual;

        System.out.println("tokkss : " + tokens);
        System.out.println("tamano : "+ tokens.size());
        System.out.println("Busqueda : " + Busqueda);
        if (Busqueda) {
            for (List<TokenPrueba> tokenvalor : tokens) {
                for (TokenPrueba tokObject : tokenvalor) {
                    if (tokObject.type != TipoEspacio.SPACE) {
                        //tokensFiltrados.add(tokObject);
                        System.out.println("Token agregado: " + tokObject.type);
                    }
                }
            }
            Busqueda = false;System.out.println("tokkss : " + tokens);
        }
        System.out.println("Tamano del tokenfiltradop : " + tokensFiltrados.size());

        for (int i = 1; i <= tokensFiltrados.size(); i++) {
            token = (TokenPrueba) tokensFiltrados.get(i);
            while (token.type != TipoEspacio.EOF) {

                reglaActual = reglasDeProduccion[posicionReglas];

                for (Object simbolo : reglaActual) {

                    System.out.println("valor del token fuera: " + token.type);

                    System.out.println("valor del simbolo : " + simbolo);
                    System.out.println("valor boleano si es intancia de tipoasig : " + (simbolo instanceof TipoAsignacion));
                    System.out.println("valor booleano final " + ((simbolo instanceof TipoAsignacion) || (simbolo instanceof TipoAritmetico) || (simbolo instanceof TipoComentario) || (simbolo instanceof TipoComparacion) || (simbolo instanceof TipoConstante) || (simbolo instanceof TipoEspacio && simbolo != TipoEspacio.SPACE) || (simbolo instanceof TipoIdentificador) || (simbolo instanceof TipoLogico) || (simbolo instanceof TipoOtro) || (simbolo instanceof TipoPalabraRes)));

                    if ((simbolo instanceof TipoAsignacion) || (simbolo instanceof TipoAritmetico) || (simbolo instanceof TipoComentario) || (simbolo instanceof TipoComparacion) || (simbolo instanceof TipoConstante) || (simbolo instanceof TipoEspacio && simbolo != TipoEspacio.SPACE) || (simbolo instanceof TipoIdentificador) || (simbolo instanceof TipoLogico) || (simbolo instanceof TipoOtro) || (simbolo instanceof TipoPalabraRes)) { // Si el símbolo es un tipo de token

                        System.out.println("Valor simbolo : " + (token.type == simbolo) + " token : " + token.type + " simbolo : " + simbolo);

                        if (token.type == simbolo) {
                            if (token.type != null && token.type != TipoEspacio.EOF && token.type != TipoEspacio.SALTO && token.type != TipoEspacio.SPACE) {
                                try {
                                    System.out.println("Token : " + token.type);
                                } catch (Exception e) {
                                    System.out.println("error : " + e);
                                }
                            }
                            posicionRegla++;
                            System.out.println(" Coincide con el token actual");
                            if (posicionRegla == reglaActual.length) {
                                return true;
                            }
                            token = (TokenPrueba) tokensFiltrados.get(i + 1);
                            i++;

                        } else {
                            if (posicionRegla == reglasDeProduccion.length) {
                                System.out.println(" No Coincide con ninguna regla");
                                return false;
                            }
                            reglaActual = reglasDeProduccion[posicionReglas++];
                            analizar();
                        }
                    } else if (simbolo instanceof String) { // Si el símbolo es una cadena
                        // Compara el símbolo con el valor del token actual
                        if (token.type.equals(simbolo)) {
                            token = (TokenPrueba) tokensFiltrados.get(i + 1);
                            i++;// Coincide con el token actual
                        } else {
                            // Error de sintaxis
                            return false;
                        }
                    }
                }

                // Si llegamos al final de la regla actual, avanzamos a la siguiente regla
                posicionReglas++;

                // Si hemos llegado al final de todas las reglas, la entrada es válida
                if (posicionRegla == reglaActual.length) {
                    return true;
                }
            }

        }

        return false;

        /*token = lexico.nextToken();
        TokenPrueba tokenIncio= null;
        if (token.type != null && token.type != TipoEspacio.EOF && token.type != TipoEspacio.SALTO && token.type != TipoEspacio.SPACE) {
           try{
               System.out.println("Token : " + token.type);
               tokenIncio = token;
           } catch(Exception e){
               System.out.println("error : " + e);
           }
        }
        System.out.println("tokens de lista : " + tokens);
        int posicionReglas = 0; // Variable para rastrear la posición en las reglas de producción 
        int posicionRegla = 0; // Variable para rastrear la posición en las reglas de producción
        Object[] reglaActual;

        while (token.type != TipoEspacio.EOF ) {
            

           reglaActual = reglasDeProduccion[posicionReglas];
            

            for (Object simbolo : reglaActual) {
               
                System.out.println("valor del token fuera: " + token.type);  
                while (token.type == TipoEspacio.SPACE) {
                      token = lexico.nextToken(); // Ignorar los espacios en blanco y avanzar al siguiente token
                      System.out.println("valor del token : " + token.type);  
                }
                
                    System.out.println("valor del simbolo : " + simbolo);
                    System.out.println("valor boleano si es intancia de tipoasig : " + (simbolo instanceof TipoAsignacion));
                    System.out.println("valor booleano final " + ((simbolo instanceof TipoAsignacion )|| (simbolo instanceof TipoAritmetico) || (simbolo instanceof TipoComentario )|| (simbolo instanceof TipoComparacion) || (simbolo instanceof TipoConstante )|| (simbolo instanceof TipoEspacio && simbolo != TipoEspacio.SPACE) || (simbolo instanceof TipoIdentificador )|| (simbolo instanceof TipoLogico) || (simbolo instanceof TipoOtro) || (simbolo instanceof TipoPalabraRes)) );

                if ((simbolo instanceof TipoAsignacion )|| (simbolo instanceof TipoAritmetico) || (simbolo instanceof TipoComentario )|| (simbolo instanceof TipoComparacion) || (simbolo instanceof TipoConstante )|| (simbolo instanceof TipoEspacio && simbolo != TipoEspacio.SPACE) || (simbolo instanceof TipoIdentificador )|| (simbolo instanceof TipoLogico) || (simbolo instanceof TipoOtro) || (simbolo instanceof TipoPalabraRes)) { // Si el símbolo es un tipo de token
                   
                    System.out.println("Valor simbolo : " +(token.type == simbolo) +" token : "+ token.type + " simbolo : " + simbolo);
                    
                    if (token.type == simbolo) {
                        if (token.type != null && token.type != TipoEspacio.EOF && token.type != TipoEspacio.SALTO && token.type != TipoEspacio.SPACE) {
                        try{
                            System.out.println("Token : " + token.type);
                        } catch(Exception e){
                            System.out.println("error : " + e);
                        }
                    }
                                posicionRegla++;
                        System.out.println(" Coincide con el token actual");
                        if (posicionRegla == reglaActual.length) {
                            return true;
                        }       
                        token = lexico.nextToken(); 
                        
                    } else{
                        if (posicionRegla == reglasDeProduccion.length) {
                            System.out.println(" No Coincide con ninguna regla");
                            return false;
                        }
                        reglaActual = reglasDeProduccion[posicionReglas++];
                        analizar();
                    } 
                } else if (simbolo instanceof String) { // Si el símbolo es una cadena
                    // Compara el símbolo con el valor del token actual
                    if (token.type.equals(simbolo)) {
                        token = lexico.nextToken(); // Coincide con el token actual
                    } else {
                        // Error de sintaxis
                        return false;
                    }
                }
            }

            // Si llegamos al final de la regla actual, avanzamos a la siguiente regla
            posicionReglas++;

            // Si hemos llegado al final de todas las reglas, la entrada es válida
            if (posicionRegla == reglaActual.length) {
                return true;
            }
        }
        
        // Si no hemos llegado al final de las reglas, hay un error de sintaxis
        return false;*/
    }

    public static void main(String[] args) {

        List<List<TokenPrueba>> listaToken = new ArrayList<>();
        List<TokenPrueba> listaT = new ArrayList<>();
        String input = "var nombre = 'John'\n"
                + "nombre = 'Juan'";

        LexicoPrueba lexicon = new LexicoPrueba(input);
        TokenPrueba tokenn = null;
        do {
            tokenn = lexicon.nextToken();
            listaT.add(lexicon.nextToken());
            listaToken.add(listaT);
            System.out.println("tokens agregados inicio : " + tokenn.type);

        } while (tokenn.type != TipoEspacio.EOF);

        System.out.println("tokens : " + listaToken);
        System.out.println("tamano : "+ listaToken.size());
        System.out.println("tamano : "+ listaT.size());

        AnalizadorSintactico parser = new AnalizadorSintactico(input, listaToken);

        if (parser.analizar()) {
            System.out.println("La asignación es válida.");
            if (parser.token.type != TipoEspacio.EOF) {
                System.out.println("Buscando mas.");
                parser.analizar();
            }
            System.out.println("Analisis finalizado");
        } else {
            System.out.println("Error de sintaxis.");
        }
    }
}
