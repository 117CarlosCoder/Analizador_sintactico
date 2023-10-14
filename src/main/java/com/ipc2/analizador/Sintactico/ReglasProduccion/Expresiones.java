/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.analizador.Sintactico.ReglasProduccion;

import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoAritmetico;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoAritmetico.SUMA;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoConstante;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoConstante.CADENA;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author carlos117
 */
public class Expresiones {

    private List<Object> lista;
    private final List<List<Object>> listaTotal;
    private String Bloque;

    public Expresiones() {
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
                    /*if(!pila.empty()){
                        if (pila.peek() instanceof TipoAritmetico) {
                            return null;
                        }   
                    }*/
                    System.out.println("Numero entero");
                    System.out.println(valor);
                    Bloque += " " + valor.get(1) + " ";
                    lista.add(valor);
                    lista = new ArrayList<>(lista);
                    List<Object> listaenlista = new ArrayList<>();
                    listaenlista.add(lista);
                    System.out.println("lista en lista : " + listaenlista);
                    lista = Arrays.asList(lista, Identificador.EXPRESION, " Expresion Numero Entero", Bloque);
                    listaTotal.add(lista);
                    System.out.println(listaTotal);
                    return listaTotal;
                case CADENA:
                    System.out.println("Cadena");
                    System.out.println(valor);
                    lista.add(valor);
                    if (!pila.empty()) {
                        Bloque += " " + valor.get(1) + " ";
                        if (((List<Object>) pila.peek()).get(0) == TipoAritmetico.SUMA) {
                            Operador(pila);
                        } else {
                            lista = new ArrayList<>(lista);
                            List<Object> listaenlista2 = new ArrayList<>();
                            listaenlista2.add(lista);
                            System.out.println("lista en lista : " + listaenlista2);
                            lista = Arrays.asList(lista, Identificador.EXPRESION, " Expresion Cadena", Bloque);
                            listaTotal.add(lista);
                            System.out.println(listaTotal);
                        }
                    }

                    return listaTotal;
                case BOOLEAN:
                    System.out.println("Boolean");
                    System.out.println(valor);

                    lista.add(valor);
                    if (!pila.empty()) {
                        Bloque += " " + valor.get(1) + " ";
                        if (((List<Object>) pila.peek()).get(0) == TipoAritmetico.SUMA) {
                            Operador(pila);
                        } else {
                            lista = new ArrayList<>(lista);
                            List<Object> listaenlista3 = new ArrayList<>();
                            listaenlista3.add(lista);
                            System.out.println("lista en lista : " + listaenlista3);
                            lista = Arrays.asList(lista, Identificador.EXPRESION, " Expresion Booleana", Bloque);
                            listaTotal.add(lista);
                            System.out.println(listaTotal);
                        }
                    }

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
                    Valor(pila);
                    return lista;
                default:
                    System.out.println("Error falta operador");

                    return null;
            }
        } else {
            listaTotal.add(valor);
        }
        return null;

    }

    private List<List<Object>> Valor(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        List<Object> valorn = null;

        if (valor.get(0) instanceof TipoConstante) {
            System.out.println(valor);

            switch ((TipoConstante) valor.get(0)) {
                case CADENA:
                    System.out.println("Cadena n");

                    if (!pila.empty()) {
                        valorn = (List<Object>) pila.peek();
                        System.out.println("Valor : " + valorn);
                    }

                    lista.add(valor);

                    if (valorn != null) {
                        Bloque += valor.get(1) + " ";
                        if (valorn.get(0) instanceof TipoAritmetico) {
                            Operador(pila);
                        } else {
                            lista = new ArrayList<>(lista);
                            List<Object> listaenlista = new ArrayList<>();
                            listaenlista.add(lista);
                            System.out.println("lista en lista : " + listaenlista);
                            //lista.add(Identificador.EXPRESION);
                            lista = Arrays.asList(lista, Identificador.EXPRESION, " Expresion Cadena", Bloque);
                            listaTotal.add(lista);
                            System.out.println(listaTotal);
                        }
                    } else {
                        lista = new ArrayList<>(lista);
                        List<Object> listaenlista = new ArrayList<>();
                        listaenlista.add(lista);
                        System.out.println("lista en lista : " + listaenlista);
                        //lista.add(Identificador.EXPRESION);
                        lista = Arrays.asList(lista, Identificador.EXPRESION, "Expresion Cadena", Bloque);
                        listaTotal.add(lista);
                        System.out.println(listaTotal);
                    }

                    return listaTotal;

                case BOOLEAN:
                    System.out.println("Boolean n");

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
                            lista = Arrays.asList(lista, Identificador.EXPRESION, "Expresion Booleana", Bloque);
                            listaTotal.add(lista);
                            System.out.println(listaTotal);
                        }
                    } else {
                        lista = new ArrayList<>(lista);
                        List<Object> listaenlista = new ArrayList<>();
                        listaenlista.add(lista);
                        System.out.println("lista en lista : " + listaenlista);
                        //lista.add(Identificador.EXPRESION);
                        lista = Arrays.asList(lista, Identificador.EXPRESION, "Expresion Booleana", Bloque);
                        listaTotal.add(lista);
                        System.out.println(listaTotal);
                    }

                    return listaTotal;

                default:
                    System.out.println("Error se esperaba valor tipo boolean o cadena");
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
