/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.analizador.Sintactico.ReglasProduccion;

import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoBits;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author carlos117
 */
public class OperacionesBits {
    private List<Object> lista;
    private final List<List<Object>> listaTotal;
    private String Bloque;

    public OperacionesBits() {
        lista = new ArrayList<>();
        listaTotal = new ArrayList<>();
        Bloque = "";
    }

    public List<List<Object>> Entrada(Stack pila) {
        Bloque = "";
        lista = new ArrayList<>();
        //listaTotal.clear();
        List<Object> valor = (List<Object>) pila.pop();

        if (valor.get(1) == Identificador.EXPRESION) {
            System.out.println(valor.get(1));

            switch ((Identificador) valor.get(1)) {
                case EXPRESION:
                    System.out.println("Expresion");
                    System.out.println(valor);
                    Bloque += " " + valor.get(1) + " ";
                    if (((List<Object>) pila.peek()).get(0) instanceof TipoBits) {
                        lista.add(valor);
                    } else {
                        System.out.println("valor siguiente  :  " + pila.peek());
                        listaTotal.add(valor);
                    }
                    Operador(pila);
                    return listaTotal;
                   
                default:
                
                    listaTotal.add(valor);
                    break;
            }

        } else {
            System.out.println("valor de inicio : " + valor);
            listaTotal.add(valor);
            System.out.println("valor de inicio listatotal: " + listaTotal);
        }

        return listaTotal;

    }

    public List<Object> Operador(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(0) instanceof TipoBits) {

            System.out.println(valor);

            switch ((TipoBits) valor.get(0)) {
                case AND:
                    System.out.println("Operador");
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Expresion(pila);
                    return lista;

                case OR:
                    System.out.println("Operador");
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Expresion(pila);
                    return lista;

                case XOR:
                    System.out.println("Operador");
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Expresion(pila);
                    return lista;
                case DEZIZQ:
                    System.out.println("Operador");
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Expresion(pila);
                    return lista;
                case DEZDER:
                    System.out.println("Operador");
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Expresion(pila);
                    return lista;
                default:
                    System.out.println("Error falta operador");
                    listaTotal.add(lista);
                    return null;
            }
        } else {
            System.out.println("Valor en el operador : " + valor);
            listaTotal.add(valor);
            System.out.println("Valor lsita total : " + listaTotal);
            return null;
        }

    }

    private List<List<Object>> Expresion(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        List<Object> valorn = null;

        if (valor.get(1) == Identificador.EXPRESION) {
            System.out.println(valor);

            switch ((Identificador) valor.get(1)) {
                case EXPRESION:
                    System.out.println("Expresion n ");

                 
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    lista = new ArrayList<>(lista);
                    List<Object> listaenlista = new ArrayList<>();
                    listaenlista.add(lista);
                    System.out.println("lista en lista : " + listaenlista);
                    //lista.add(Identificador.EXPRESION);
                    lista = Arrays.asList(lista, Identificador.OPBITS, "Operaciones de Bits", Bloque);
                    listaTotal.add(lista);
                    System.out.println(listaTotal);
                    

                    return listaTotal;
                default:
                    System.out.println("Error se esperaba numero n");
                    Bloque = "";
                    lista.clear();
                    if (!pila.empty()) {
                        Entrada(pila);
                    }
                    break;
            }
        }

        return null;

    }
}
