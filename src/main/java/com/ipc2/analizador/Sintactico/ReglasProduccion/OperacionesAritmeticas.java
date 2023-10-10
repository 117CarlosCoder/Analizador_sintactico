/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.analizador.Sintactico.ReglasProduccion;

import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoAritmetico;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoAritmetico.EXP;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoAritmetico.MODULO;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoAritmetico.RES;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoAritmetico.SUMA;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoConstante;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoConstante.INTEGER;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author carlos117
 */
public class OperacionesAritmeticas {

    private List<Object> lista;
    private final List<List<Object>> listaTotal;
    private String Bloque;

    public OperacionesAritmeticas() {
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
                    Bloque += "\n " + valor.get(1) + " ";
                    lista.add(valor);
                    Operador(pila);
                    return listaTotal;
                case DECIMAL:
                    System.out.println("Numero decimal");
                    System.out.println(valor);
                    Bloque += "\n " + valor.get(1) + " ";
                    lista.add(valor);
                    Operador(pila);
                    return listaTotal;
                default:
                    listaTotal.add(valor);
                    break;
            }

        } else {
            System.out.println("valor de inicio : " + valor);
            listaTotal.add(valor);
        }

        return listaTotal;

    }

    public List<Object> Operador(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(0) instanceof TipoAritmetico) {

            System.out.println(valor);

            switch ((TipoAritmetico) valor.get(0)) {
                case SUMA:
                    System.out.println("Operador");
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Numero(pila);
                    return lista;

                case RES:
                    System.out.println("Operador");
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Numero(pila);
                    return lista;

                case DIVISION:
                    System.out.println("Operador");
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Numero(pila);
                    return lista;
                case EXP:
                    System.out.println("Operador");
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Numero(pila);
                    return lista;
                case MODULO:
                    System.out.println("Operador");
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Numero(pila);
                    return lista;
                case MULTI:
                    System.out.println("Operador");
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Numero(pila);
                    return lista;
                default:
                    System.out.println("Error falta operador");

                    return null;
            }
        } else {
            lista = new ArrayList<>(lista);
            List<Object> listaenlista = new ArrayList<>();
            listaenlista.add(lista);
            System.out.println("lista en lista : " + listaenlista);
            //lista.add(Identificador.EXPRESION);
            lista = Arrays.asList(lista, Identificador.EXPRESION, "Declaracion y asignacion de operaciones Aritmmeticas", Bloque);
            listaTotal.add(lista);
            System.out.println(listaTotal);
        }
        return null;

    }

    private List<List<Object>> Numero(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        List<Object> valorn = null;

        if (valor.get(0) instanceof TipoConstante) {
            System.out.println(valor);

            switch ((TipoConstante) valor.get(0)) {
                case INTEGER:
                    System.out.println("Numero n Entero");

                    if (!pila.empty()) {
                        valorn = (List<Object>) pila.peek();
                        System.out.println("Valor : " + valorn);
                    }

                    Bloque += valor.get(1) + " ";
                    lista.add(valor);

                    if (valorn != null) {
                        if (valorn.get(0) instanceof TipoAritmetico) {
                            Operador(pila);
                        } else {
                            lista = new ArrayList<>(lista);
                            List<Object> listaenlista = new ArrayList<>();
                            listaenlista.add(lista);
                            System.out.println("lista en lista : " + listaenlista);
                            //lista.add(Identificador.EXPRESION);
                            lista = Arrays.asList(lista, Identificador.EXPRESION, " Declaracion y asignacion de operaciones Aritmmeticas", Bloque);
                            listaTotal.add(lista);
                            System.out.println(listaTotal);
                        }
                    } else {
                        lista = new ArrayList<>(lista);
                        List<Object> listaenlista = new ArrayList<>();
                        listaenlista.add(lista);
                        System.out.println("lista en lista : " + listaenlista);
                        //lista.add(Identificador.EXPRESION);
                        lista = Arrays.asList(lista, Identificador.EXPRESION, "Declaracion y asignacion de operaciones Aritmmeticas", Bloque);
                        listaTotal.add(lista);
                        System.out.println(listaTotal);
                    }

                    return listaTotal;

                case DECIMAL:
                    System.out.println("Numero n Decimal");
                    if (!pila.empty()) {
                        valorn = (List<Object>) pila.peek();
                    }

                    Bloque += valor.get(1) + " ";
                    lista.add(valor);

                    if (valorn != null) {
                        if (valorn.get(0) instanceof TipoAritmetico) {
                            Operador(pila);
                        } else {
                            lista = new ArrayList<>(lista);
                            List<Object> listaenlista = new ArrayList<>();
                            listaenlista.add(lista);
                            System.out.println("lista en lista : " + listaenlista);
                            //lista.add(Identificador.EXPRESION);
                            lista = Arrays.asList(lista, Identificador.EXPRESION, " Declaracion y asignacion de operaciones Aritmmeticas", Bloque);
                            listaTotal.add(lista);
                            System.out.println("valor lista : " + listaTotal);
                        }
                    } else {

                        lista = new ArrayList<>(lista);
                        List<Object> listaenlista = new ArrayList<>();
                        listaenlista.add(lista);
                        System.out.println("lista en lista : " + listaenlista);
                        //lista.add(Identificador.EXPRESION);
                        lista = Arrays.asList(lista, Identificador.EXPRESION, " Declaracion y asignacion de operaciones Aritmmeticas", Bloque);
                        listaTotal.add(lista);
                        System.out.println("valor lista : " + listaTotal);
                    }

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
