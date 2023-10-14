/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.analizador.Sintactico.ReglasProduccion;

import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoAsignacion;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoAsignacion.ASIG;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoIdentificador;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoIdentificador.ID;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoPalabraRes;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoPalabraRes.IF;
import static com.ipc2.analizador.Sintactico.ReglasProduccion.Identificador.EXPRESION;
import static com.ipc2.analizador.Sintactico.ReglasProduccion.Identificador.OPCOMP;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author carlos117
 */
public class OperadoresTernarios {
    private List<Object> lista;
    private final List<List<Object>> listaTotal;
    private String Bloque;

    public OperadoresTernarios() {
        lista = new ArrayList<>();
        listaTotal = new ArrayList<>();
        Bloque = "";
    }

    public List<List<Object>> Entrada(Stack pila) {
        Bloque = "";
        lista = new ArrayList<>();
        //listaTotal.clear();
        List<Object> valor = (List<Object>) pila.pop();

        if (valor.get(0) == TipoIdentificador.ID) {
            System.out.println(valor.get(0));

            switch ((TipoIdentificador) valor.get(0)) {
                case ID:
                    System.out.println("ID");
                    System.out.println(valor);
                    Bloque +=  valor.get(1) + " ";
                    listaTotal.add(valor);
                    if(((List<Object>)pila.peek()).get(0) instanceof TipoAsignacion){
                        lista.add(valor);
                        Asignador(pila);
                    }else{
                        System.out.println("valor siguiente  :  " + pila.peek());
                        //listaTotal.add(valor);
                    }
                   
                    return listaTotal;
                default:
                    listaTotal.add(valor);
                    break;
            }

        }
        else{
            System.out.println("valor de inicio : "+valor);
            listaTotal.add(valor);
        }
        
        return listaTotal;

    }

    public List<Object> Asignador(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(0) instanceof TipoAsignacion) {

            System.out.println(valor);

            switch ((TipoAsignacion) valor.get(0)) {
                case ASIG:
                    System.out.println("Asignador");
                    System.out.println(valor);
                    Bloque += valor.get(1) + " ";
                    listaTotal.add(valor);
                    if(((List<Object>)pila.peek()).get(1) == Identificador.EXPRESION){
                        lista.add(valor);
                        Expresion(pila);
                    }else{
                        System.out.println("valor siguiente  :  " + pila.peek());
                        //listaTotal.add(valor);
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
    
    public List<Object> Expresion(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(1) == Identificador.EXPRESION) {

            System.out.println(valor);

            switch ((Identificador) valor.get(1)) {
                case EXPRESION:
                    System.out.println("Expresion");
                    System.out.println(valor);
                    Bloque += valor.get(3) + " ";
                    if(((List<Object>)pila.peek()).get(0) == TipoPalabraRes.IF){
                        lista.add(valor);
                        resLogic(pila);
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
    
    public List<Object> resLogic(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(0) == TipoPalabraRes.IF) {

            System.out.println(valor);

            switch ((TipoPalabraRes) valor.get(0)) {
                case IF:
                    System.out.println("Palabra res IF");
                    System.out.println(valor);
                    Bloque += valor.get(1) + " ";
                    if(((List<Object>)pila.peek()).get(1) == Identificador.OPCOMP){
                        lista.add(valor);
                        Comparardor(pila);
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
    
     public List<Object> Comparardor(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(1) == Identificador.OPCOMP) {

            System.out.println(valor);

            switch ((Identificador) valor.get(1)) {
                case OPCOMP:
                    System.out.println("Comparador");
                    System.out.println(valor);
                    Bloque += valor.get(3) + " ";
                    if(((List<Object>)pila.peek()).get(0) == TipoPalabraRes.ELSE){
                        lista.add(valor);
                        resLogicElse(pila);
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
     
     public List<Object> resLogicElse(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(0) == TipoPalabraRes.ELSE) {

            System.out.println(valor);

            switch ((TipoPalabraRes) valor.get(0)) {
                case ELSE:
                    System.out.println("Palabra res ELSE");
                    System.out.println(valor);
                    Bloque += valor.get(1) + " ";
                    if(((List<Object>)pila.peek()).get(1) == Identificador.EXPRESION){
                        lista.add(valor);
                        expresionSalida(pila);
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

    private List<List<Object>> expresionSalida(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();

        if (valor.get(1) == Identificador.EXPRESION) {
            System.out.println(valor);

            switch ((Identificador) valor.get(1)) {
                case EXPRESION :
                    System.out.println("Expresion n");
                    Bloque += valor.get(3) + " ";
                    lista.add(valor);

                    lista = new ArrayList<>(lista);
                    List<Object> listaenlista = new ArrayList<>();
                    listaenlista.add(lista);
                    
                    System.out.println("lista en lista : " + listaenlista);
                    //lista.add(Identificador.EXPRESION);
                    lista = Arrays.asList( lista, Identificador.OPTERNARIO, "Operador Ternario",Bloque);
                    listaTotal.add(lista);
                    System.out.println(listaTotal);
                    

                    return listaTotal;
                    
                default:
                    System.out.println("Error se esperaba un operador comparacion");
                    Bloque = "";
                    lista.clear();
                    if (!pila.empty()) {
                        Entrada(pila);
                    }
                    break;
            }
        }else{
            listaTotal.add(valor);
        }
        
        return null;
    }
}
