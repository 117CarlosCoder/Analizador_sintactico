/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.analizador.Sintactico;

import com.ipc2.analizador.Lexico.LexicoPrueba;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoEspacio;
import com.ipc2.analizador.Lexico.Token.TokenPrueba;
import com.ipc2.analizador.Sintactico.ReglasProduccion.Asignacion;
import com.ipc2.analizador.Sintactico.ReglasProduccion.AsignacionComparacion;
import com.ipc2.analizador.Sintactico.ReglasProduccion.AsignacionOperador;
import com.ipc2.analizador.Sintactico.ReglasProduccion.Expresiones;
import com.ipc2.analizador.Sintactico.ReglasProduccion.OperacionesAritmeticas;
import com.ipc2.analizador.Sintactico.ReglasProduccion.OperacionesComparacion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author carlos117
 */
public class Sintactico {
    private List<List<Object>> listaToken;

    
    public Sintactico() {
        listaToken = new ArrayList<>();
    }
    
    public List<List<Object>> valorSintactico (String input){
   
        listaToken.clear();
        
        LexicoPrueba lexicon = new LexicoPrueba(input);
        TokenPrueba tokenn;
        System.out.println(input);
        
        do {
            tokenn = lexicon.nextToken();
            if (tokenn.type != TipoEspacio.SPACE) {
                List<Object> listaT = Arrays.asList(tokenn.type,tokenn.value,tokenn.fila,tokenn.columna);
                listaToken.add(listaT);
                System.out.println("tokens agregados inicio : " + tokenn.type);
                System.out.println("tokens agregados inicio : " + listaToken);
            }

        } while (tokenn.type != TipoEspacio.EOF);
        
        Stack pila = new Stack<>();
        System.out.println(listaToken.size());
        int valor = listaToken.size();
        OperacionesAritmeticas nuvOp = new OperacionesAritmeticas();
        Expresiones nuvEx = new Expresiones();
        Asignacion nuvAsig = new Asignacion();
        AsignacionOperador nuvAsigComp = new AsignacionOperador();
        OperacionesComparacion nuvOpLog = new OperacionesComparacion();
        AsignacionComparacion nuvOpComp = new AsignacionComparacion();
        
        for (int index = valor-1; index >= 0; index--) {
            System.out.println(index);
            pila.push(listaToken.get(index));  
        }
        
        System.out.println(pila);
        System.out.println("**** Operaciones Aritmeticas *****");
        while(!pila.empty()){
            System.out.println(pila);
            //listaToken = nuvEx.Entrada(pila);
            listaToken = nuvOp.Entrada(pila);
        }
        
        valor = listaToken.size();
        
        System.out.println("valor devuelto : " + listaToken);
        
        
        for (int index = valor-1; index >= 0; index--) {
            System.out.println(index);
            pila.push(listaToken.get(index));  
      
        }
        
        System.out.println("**** Operaciones Logicas *****");
        while(!pila.empty()){
            System.out.println(pila);
            //listaToken = nuvEx.Entrada(pila);
            listaToken = nuvOpLog.Entrada(pila);
        }
        
        valor = listaToken.size();
        
        System.out.println("valor devuelto : " + listaToken);
        
        
        for (int index = valor-1; index >= 0; index--) {
            System.out.println(index);
            pila.push(listaToken.get(index));  
      
        }
        
        System.out.println("**** Expresiones *****");
        while(!pila.empty()){
            System.out.println(pila);
            //listaToken = nuvOp.Entrada(pila);
            listaToken = nuvEx.Entrada(pila);
        }
        
        valor = listaToken.size();
        
        System.out.println("valor devuelto : " + listaToken);
        
        for (int index = valor-1; index >= 0; index--) {
            System.out.println(index);
            pila.push(listaToken.get(index));  
      
        }
        
        System.out.println("**** Asignacion Operador Comparador *****");
        
        while(!pila.empty()){
            System.out.println(pila);
            //listaToken = nuvOp.Entrada(pila);
            listaToken = nuvOpComp.Entrada(pila);
        }
        
        valor = listaToken.size();
        
        System.out.println("valor devuelto : " + listaToken);
        
        for (int index = valor-1; index >= 0; index--) {
            System.out.println(index);
            pila.push(listaToken.get(index));  
      
        }
        
        System.out.println("**** Asignacion *****");
        
        while(!pila.empty()){
            System.out.println(pila);
            //listaToken = nuvOp.Entrada(pila);
            listaToken = nuvAsig.Entrada(pila);
        }
        
        valor = listaToken.size();
        
        System.out.println("valor devuelto : " + listaToken);
        
        for (int index = valor-1; index >= 0; index--) {
            System.out.println(index);
            pila.push(listaToken.get(index));  
      
        }
        
        
        
        System.out.println("**** Operador Asignacion *****");
        
        while(!pila.empty()){
            System.out.println(pila);
            //listaToken = nuvOp.Entrada(pila);
            listaToken = nuvAsigComp.Entrada(pila);
        }
        
        System.out.println("valor devuelto : " + listaToken);
        
        return listaToken;
    }
}
