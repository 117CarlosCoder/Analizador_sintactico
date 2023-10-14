/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.analizador.Sintactico.ReglasProduccion;

import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoComparacion;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoConstante;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoConstante.DECIMAL;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoConstante.INTEGER;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author carlos117
 */
public class OperacionesComparacion {
    
    private List<Object> lista;
    private final List<List<Object>> listaTotal;
    private String Bloque;

    public OperacionesComparacion() {
        lista = new ArrayList<>();
        listaTotal = new ArrayList<>();
        Bloque = "";
    }

    public List<List<Object>> Entrada(Stack pila) {
        Bloque = "";
        lista = new ArrayList<>();
        //listaTotal.clear();
        List<Object> valor = (List<Object>) pila.pop();

        if (valor.get(0) instanceof TipoConstante) {
            System.out.println(valor.get(0));

            switch ((TipoConstante) valor.get(0)) {
                case INTEGER:
                    System.out.println("Numero entero");
                    System.out.println(valor);
                    Bloque += " " + valor.get(1) + " ";
                    if (((List<Object>) pila.peek()).get(0) instanceof TipoComparacion) {
                        lista.add(valor);
                    } else {
                        System.out.println("valor siguiente  :  " + pila.peek());
                        listaTotal.add(valor);
                    }
                    Operador(pila);
                    return listaTotal;
                case DECIMAL:
                    System.out.println("Numero decimal");
                    System.out.println(valor);
                    Bloque += " " + valor.get(1) + " ";
                    if (((List<Object>) pila.peek()).get(0) instanceof TipoComparacion) {
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
        if (valor.get(0) instanceof TipoComparacion) {

            System.out.println(valor);

            switch ((TipoComparacion) valor.get(0)) {
                case IGUAL:
                    System.out.println("Operador");
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Expresion(pila);
                    return lista;

                case DIFERENTE:
                    System.out.println("Operador");
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Expresion(pila);
                    return lista;

                case OPRMNI:
                    System.out.println("Operador");
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Expresion(pila);
                    return lista;
                case OPRMYI:
                    System.out.println("Operador");
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Expresion(pila);
                    return lista;
                case OPRMNQ:
                    System.out.println("Operador");
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Expresion(pila);
                    return lista;
                case OPRMYQ:
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

        if (valor.get(0) instanceof TipoConstante) {
            System.out.println(valor);

            switch ((TipoConstante) valor.get(0)) {
                case INTEGER:
                    System.out.println("Numero n Entero");

                 
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    lista = new ArrayList<>(lista);
                    List<Object> listaenlista = new ArrayList<>();
                    listaenlista.add(lista);
                    System.out.println("lista en lista : " + listaenlista);
                    //lista.add(Identificador.EXPRESION);
                    lista = Arrays.asList(lista, Identificador.OPCOMP, "Operaciones de Comparacion", Bloque);
                    listaTotal.add(lista);
                    System.out.println(listaTotal);
                    

                    return listaTotal;

                case DECIMAL:
                    System.out.println("Numero n Decimal");
                    
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);


                        lista = new ArrayList<>(lista);
                        List<Object> listaenlista2 = new ArrayList<>();
                        listaenlista2.add(lista);
                        System.out.println("lista en lista : " + listaenlista2);
                        //lista.add(Identificador.EXPRESION);
                        lista = Arrays.asList(lista, Identificador.OPCOMP, "Operaciones de Comparacion", Bloque);
                        listaTotal.add(lista);
                        System.out.println("valor lista : " + listaTotal);
                    

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
