/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.analizador.Sintactico.ReglasProduccion;

import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoOtro;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoOtro.PUNTDOB;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoPalabraRes;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoPalabraRes.ELIF;
import static com.ipc2.analizador.Sintactico.ReglasProduccion.Identificador.ASIG;
import static com.ipc2.analizador.Sintactico.ReglasProduccion.Identificador.ASIGCOP;
import static com.ipc2.analizador.Sintactico.ReglasProduccion.Identificador.ASIGOPBITS;
import static com.ipc2.analizador.Sintactico.ReglasProduccion.Identificador.ASIGOPCOMP;
import static com.ipc2.analizador.Sintactico.ReglasProduccion.Identificador.ASIGOPIDNT;
import static com.ipc2.analizador.Sintactico.ReglasProduccion.Identificador.ASIGOPLOGIC;
import static com.ipc2.analizador.Sintactico.ReglasProduccion.Identificador.ASIGOPPERT;
import static com.ipc2.analizador.Sintactico.ReglasProduccion.Identificador.EXPRESION;
import static com.ipc2.analizador.Sintactico.ReglasProduccion.Identificador.OPCOMP;
import static com.ipc2.analizador.Sintactico.ReglasProduccion.Identificador.OPENTSAL;
import static com.ipc2.analizador.Sintactico.ReglasProduccion.Identificador.OPTERNARIO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author carlos117
 */
public class CondicionElif {
  
    private List<Object> lista;
    private List<List<Object>> listaTotal;
    private String Bloque;
    private CondicionElse Else;
 
    public CondicionElif(List<Object> lista, List<List<Object>> listaTotal) {
        lista = new ArrayList<>();
        listaTotal = this.listaTotal;
        Bloque = "";
        
    }

    
    
     public List<List<Object>> EntradaElif(Stack pila) {

        Bloque = "";
        lista = new ArrayList<>();
        //listaTotal.clear();
         
        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(0) == TipoPalabraRes.ELIF ) {

            System.out.println(valor);

            switch ((TipoPalabraRes) valor.get(0)) {
                case ELIF:
                    System.out.println("Palabra res Elif");
                    System.out.println(valor);
                    Bloque += valor.get(1) + " ";
                    //listaTotal.add(valor);
                    if(((List<Object>)pila.peek()).get(1) == Identificador.OPCOMP){
                        lista.add(valor);
                        ComparadorElif(pila);
                    }else{
                        System.out.println("valor siguiente  :  " + pila.peek());
                        listaTotal.add(valor);
                    }

                  
                    return listaTotal;
                default:
                    System.out.println("Error falta Asignacion =");
                    listaTotal.add(valor);
                    return null;
            }
        }
        else{
            listaTotal.add(valor);
        }
        return null;

    }
    
    public List<Object> ComparadorElif(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(1) == Identificador.OPCOMP) {

            System.out.println(valor);

            switch ((Identificador) valor.get(1)) {
                case OPCOMP:
                    System.out.println("Comparador");
                    System.out.println(valor);
                    Bloque += valor.get(1) + " ";
                    //listaTotal.add(valor);
                    if(((List<Object>)pila.peek()).get(0) == TipoOtro.PUNTDOB){
                        lista.add(valor);
                        doblePuntoElif(pila);
                    }else{
                        System.out.println("valor siguiente  :  " + pila.peek());
                        listaTotal.add(valor);
                    }

                  
                    return lista;
                default:
                    System.out.println("Error falta Asignacion =");
                    listaTotal.add(valor);
                    return null;
            }
        }
        else{
            listaTotal.add(valor);
        }
        return null;

    }
    
    public List<Object> doblePuntoElif(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(0) == TipoOtro.PUNTDOB) {

            System.out.println(valor);

            switch ((TipoOtro) valor.get(0)) {
                case PUNTDOB:
                    System.out.println("Doble punto");
                    System.out.println(valor);
                    Bloque += valor.get(3) + " ";
                    if(((List<Object>)pila.peek()).get(1) == Identificador.EXPRESION){
                        lista.add(valor);
                        bloqueElif(pila);
                    }else if(((List<Object>)pila.peek()).get(1) == Identificador.ASIG){
                        lista.add(valor);
                        bloqueElif(pila);
                    }else if(((List<Object>)pila.peek()).get(1) == Identificador.ASIGCOP){
                        lista.add(valor);
                        bloqueElif(pila);
                    }else if(((List<Object>)pila.peek()).get(1) == Identificador.ASIGOPBITS){
                        lista.add(valor);
                        bloqueElif(pila);
                    }else if(((List<Object>)pila.peek()).get(1) == Identificador.ASIGOPCOMP){
                        lista.add(valor);
                        bloqueElif(pila);
                    }else if(((List<Object>)pila.peek()).get(1) == Identificador.ASIGOPIDNT){
                        lista.add(valor);
                        bloqueElif(pila);
                    }else if(((List<Object>)pila.peek()).get(1) == Identificador.ASIGOPLOGIC){
                        lista.add(valor);
                        bloqueElif(pila);
                    }else if(((List<Object>)pila.peek()).get(1) == Identificador.ASIGOPPERT){
                        lista.add(valor);
                        bloqueElif(pila);
                    }else if(((List<Object>)pila.peek()).get(1) == Identificador.OPTERNARIO){
                        lista.add(valor);
                        bloqueElif(pila);
                    }else if(((List<Object>)pila.peek()).get(1) == Identificador.OPENTSAL){
                        lista.add(valor);
                        bloqueElif(pila);
                    }else{
                        System.out.println("valor siguiente  :  " + pila.peek());
                        listaTotal.add(valor);
                    }
                    
                    return lista;
                default:
                    System.out.println("Error falta Asignacion =");
                    listaTotal.add(valor);
                    return null;
            }
        }
        else{
            listaTotal.add(valor);
        }
        return null;

    }
    
    public List<List<Object>> bloqueElif(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        Else = new CondicionElse(lista, listaTotal);
        if (valor.get(1) instanceof Identificador) {
            System.out.println(valor);

            switch ((Identificador) valor.get(1)) {
                case EXPRESION :
                    System.out.println("Expresion");
                    Bloque += valor.get(3) + " ";
                    lista.add(valor);
                    if(((List<Object>)pila.peek()).get(1) == Identificador.EXPRESION){
                        bloqueElif(pila);
                    }if(((List<Object>)pila.peek()).get(0) == TipoPalabraRes.ELSE){
                        
                        return Else.EntradaElse(pila);
                    }else{
                        lista = new ArrayList<>(lista);
                        List<Object> listaenlista = new ArrayList<>();
                        listaenlista.add(lista);

                        System.out.println("lista en lista : " + listaenlista);
                        //lista.add(Identificador.EXPRESION);
                        lista = Arrays.asList( lista, Identificador.BLOQUEELIF, "Bloque ELIF",Bloque);
                        listaTotal.add(lista);
                        System.out.println(listaTotal);
                    }
                   
                    

                    return listaTotal;
                case ASIG :
                    System.out.println("Asignacion");
                    Bloque += valor.get(3) + " ";
                    lista.add(valor);
                    if(((List<Object>)pila.peek()).get(1) == Identificador.ASIG){
                        bloqueElif(pila);
                    }if(((List<Object>)pila.peek()).get(0) == TipoPalabraRes.ELSE){
                        return Else.EntradaElse(pila);
                    }else{
                        lista = new ArrayList<>(lista);
                        List<Object> listaenlista = new ArrayList<>();
                        listaenlista.add(lista);

                        System.out.println("lista en lista : " + listaenlista);
                        //lista.add(Identificador.EXPRESION);
                        lista = Arrays.asList( lista, Identificador.BLOQUEELIF, "Bloque ELIF",Bloque);
                        listaTotal.add(lista);
                        System.out.println(listaTotal);
                    }
                    

                    return listaTotal;
                case ASIGCOP :
                    System.out.println("Asignacion Operadores");
                    Bloque += valor.get(3) + " ";
                    lista.add(valor);
                    if(((List<Object>)pila.peek()).get(1) == Identificador.ASIGCOP){
                        bloqueElif(pila);
                    }if(((List<Object>)pila.peek()).get(0) == TipoPalabraRes.ELSE){
                        return Else.EntradaElse(pila);
                    }else{
                        lista = new ArrayList<>(lista);
                        List<Object> listaenlista = new ArrayList<>();
                        listaenlista.add(lista);

                        System.out.println("lista en lista : " + listaenlista);
                        //lista.add(Identificador.EXPRESION);
                        lista = Arrays.asList( lista, Identificador.BLOQUEELIF, "Bloque ELIF",Bloque);
                        listaTotal.add(lista);
                        System.out.println(listaTotal);
                    }
                    
                    

                    return listaTotal;
                case ASIGOPBITS :
                    System.out.println("Asignacion de Bits");
                    Bloque += valor.get(3) + " ";
                    lista.add(valor);
                    if(((List<Object>)pila.peek()).get(1) == Identificador.ASIGOPBITS){
                        bloqueElif(pila);
                    }if(((List<Object>)pila.peek()).get(0) == TipoPalabraRes.ELSE){
                        return Else.EntradaElse(pila);
                    }else{
                        lista = new ArrayList<>(lista);
                        List<Object> listaenlista = new ArrayList<>();
                        listaenlista.add(lista);

                        System.out.println("lista en lista : " + listaenlista);
                        //lista.add(Identificador.EXPRESION);
                        lista = Arrays.asList( lista, Identificador.BLOQUEELIF, "Bloque ELIF",Bloque);
                        listaTotal.add(lista);
                        System.out.println(listaTotal);
                    }

                    
                    

                    return listaTotal;
                case ASIGOPCOMP :
                    System.out.println("Asignacion Comparacion");
                    Bloque += valor.get(3) + " ";
                    lista.add(valor);
                    if(((List<Object>)pila.peek()).get(1) == Identificador.ASIGOPCOMP){
                        bloqueElif(pila);
                    }if(((List<Object>)pila.peek()).get(0) == TipoPalabraRes.ELSE){
                        return Else.EntradaElse(pila);
                    }else{
                        lista = new ArrayList<>(lista);
                        List<Object> listaenlista = new ArrayList<>();
                        listaenlista.add(lista);

                        System.out.println("lista en lista : " + listaenlista);
                        //lista.add(Identificador.EXPRESION);
                        lista = Arrays.asList( lista, Identificador.BLOQUEELIF, "Bloque ELIF",Bloque);
                        listaTotal.add(lista);
                        System.out.println(listaTotal);
                    }
                    

                    

                    return listaTotal;
                case ASIGOPIDNT :
                    System.out.println("Asignacion Identidad");
                    Bloque += valor.get(3) + " ";
                    lista.add(valor);
                    if(((List<Object>)pila.peek()).get(1) == Identificador.ASIGOPIDNT){
                        bloqueElif(pila);
                    }if(((List<Object>)pila.peek()).get(0) == TipoPalabraRes.ELSE){
                        return Else.EntradaElse(pila);
                    }else{
                        lista = new ArrayList<>(lista);
                        List<Object> listaenlista = new ArrayList<>();
                        listaenlista.add(lista);

                        System.out.println("lista en lista : " + listaenlista);
                        //lista.add(Identificador.EXPRESION);
                        lista = Arrays.asList( lista, Identificador.BLOQUEELIF, "Bloque ELIF",Bloque);
                        listaTotal.add(lista);
                        System.out.println(listaTotal);
                    }

                    

                    return listaTotal;
                case ASIGOPLOGIC :
                    System.out.println("Asignacion Logica");
                    Bloque += valor.get(3) + " ";
                    lista.add(valor);
                    if(((List<Object>)pila.peek()).get(1) == Identificador.ASIGOPLOGIC){
                        bloqueElif(pila);
                    }if(((List<Object>)pila.peek()).get(0) == TipoPalabraRes.ELSE){
                        return Else.EntradaElse(pila);
                    }else{
                        lista = new ArrayList<>(lista);
                        List<Object> listaenlista = new ArrayList<>();
                        listaenlista.add(lista);

                        System.out.println("lista en lista : " + listaenlista);
                        //lista.add(Identificador.EXPRESION);
                        lista = Arrays.asList( lista, Identificador.BLOQUEELIF, "Bloque ELIF",Bloque);
                        listaTotal.add(lista);
                        System.out.println(listaTotal);
                    }

               
                    

                    return listaTotal;
                case ASIGOPPERT :
                   System.out.println("Asignacion Pertenencia");
                    Bloque += valor.get(3) + " ";
                    lista.add(valor);
                    if(((List<Object>)pila.peek()).get(1) == Identificador.ASIGOPPERT){
                        bloqueElif(pila);
                    }if(((List<Object>)pila.peek()).get(0) == TipoPalabraRes.ELSE){
                        return Else.EntradaElse(pila);
                    }else{
                        lista = new ArrayList<>(lista);
                        List<Object> listaenlista = new ArrayList<>();
                        listaenlista.add(lista);

                        System.out.println("lista en lista : " + listaenlista);
                        //lista.add(Identificador.EXPRESION);
                        lista = Arrays.asList( lista, Identificador.BLOQUEELIF, "Bloque ELIF",Bloque);
                        listaTotal.add(lista);
                        System.out.println(listaTotal);
                    }


                    return listaTotal;
                case OPTERNARIO :
                    System.out.println("Operador Ternario");
                    Bloque += valor.get(3) + " ";
                    lista.add(valor);
                    if(((List<Object>)pila.peek()).get(1) == Identificador.OPTERNARIO){
                        bloqueElif(pila);
                    }if(((List<Object>)pila.peek()).get(0) == TipoPalabraRes.ELSE){
                        return Else.EntradaElse(pila);
                    }else{
                        lista = new ArrayList<>(lista);
                        List<Object> listaenlista = new ArrayList<>();
                        listaenlista.add(lista);

                        System.out.println("lista en lista : " + listaenlista);
                        //lista.add(Identificador.EXPRESION);
                        lista = Arrays.asList( lista, Identificador.BLOQUEELIF, "Operador Ternario",Bloque);
                        listaTotal.add(lista);
                        System.out.println(listaTotal);
                    }

                   
                    

                    return listaTotal;
                case OPENTSAL :
                    System.out.println("Operador Entrada Salida");
                    Bloque += valor.get(3) + " ";
                    lista.add(valor);
                    if(((List<Object>)pila.peek()).get(1) == Identificador.OPENTSAL){
                        bloqueElif(pila);
                    }if(((List<Object>)pila.peek()).get(0) == TipoPalabraRes.ELSE){
                        return Else.EntradaElse(pila);
                    }else{
                        lista = new ArrayList<>(lista);
                        List<Object> listaenlista = new ArrayList<>();
                        listaenlista.add(lista);

                        System.out.println("lista en lista : " + listaenlista);
                        System.out.println(" lista : " + lista);
                        System.out.println(" listaTotal : " + listaTotal );
                        //lista.add(Identificador.EXPRESION);
                        lista = Arrays.asList( lista, Identificador.BLOQUEELIF, "Operador Entrada y Salida",Bloque);
                        listaTotal.add(lista);
                        System.out.println(listaTotal);
                    }
                    

                    return listaTotal;
                    
                default:
                    System.out.println("Error se esperaba una expresion");
                    Bloque = "";
                    lista.clear();
                    if (!pila.empty()) {
                        EntradaElif(pila);
                    }
                    break;
            }
        }else{
            listaTotal.add(valor);
        }
        return null;

    }
    
}
