/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.analizador.Sintactico.ReglasProduccion;


import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoLogico;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoLogico.NOT;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoOtro;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoOtro.COMA;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoOtro.COROPN;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoPalabraRes;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoPalabraRes.IN;
import static com.ipc2.analizador.Sintactico.ReglasProduccion.Identificador.EXPRESION;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author carlos117
 * 
 */

public class OperacionesPertenencia {
    private List<Object> lista;
    private final List<List<Object>> listaTotal;
    private String Bloque;

    public OperacionesPertenencia() {
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
                    //listaTotal.add(valor);
                    lista.add(valor);
                    System.out.println("Lista : " + lista);
                    if (((List<Object>) pila.peek()).get(0) == TipoPalabraRes.IN) {
                        //lista.add(valor);
                        Operador(pila);
                    }else if (((List<Object>) pila.peek()).get(0) == TipoLogico.NOT) {
                        //lista.add(valor);
                        OperadorLogico(pila);
                    } else {
                        System.out.println("valor siguiente  :  " + pila.peek());
                        listaTotal.add(valor);
                    }
                   
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

    public List<Object> OperadorLogico(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(0) == TipoLogico.NOT) {

            System.out.println(valor);

            switch ((TipoLogico) valor.get(0)) {
                case NOT:
                    System.out.println("Operador Logico");
                    Bloque += valor.get(1) + " ";
                    //listaTotal.add(valor);
                    lista.add(valor);
                    System.out.println("Lista : " + lista);
                    if (((List<Object>) pila.peek()).get(0) == TipoPalabraRes.IN) {
                        //lista.add(valor);
                        Operador(pila);
                    } else {
                        System.out.println("valor siguiente  :  " + pila.peek());
                        //listaTotal.add(valor);
                    }
                    
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
    
    public List<Object> Operador(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(0) == TipoPalabraRes.IN) {

            System.out.println(valor);

            switch ((TipoPalabraRes) valor.get(0)) {
                case IN:
                    System.out.println("Operador");
                    Bloque += valor.get(1) + " ";
                    //listaTotal.add(valor);
                    lista.add(valor);
                    System.out.println("Lista : " + lista);
                    if (((List<Object>) pila.peek()).get(0) == TipoOtro.COROPN) {
                        //lista.add(valor);
                        ArrayElemntos(pila);
                    } else {
                        System.out.println("valor siguiente  :  " + pila.peek());
                        //listaTotal.add(valor);
                    }
                    
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
    
    public List<Object> ArrayElemntos(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(0) == TipoOtro.COROPN) {

            System.out.println(valor);

            switch ((TipoOtro) valor.get(0)) {
                case COROPN:
                    System.out.println("Corchete Abierto");
                    Bloque += valor.get(1) + " ";
                    //listaTotal.add(valor);
                    lista.add(valor);
                    System.out.println("Lista : " + lista);
                    if (((List<Object>) pila.peek()).get(1) == Identificador.EXPRESION) {
                        //lista.add(valor);
                        Expresion(pila);
                    } else {
                        System.out.println("valor siguiente  :  " + pila.peek());
                        //listaTotal.add(valor);
                    }
                    
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
    

    
    public List<List<Object>> Expresion(Stack pila) {
        
        List<Object> valor = (List<Object>) pila.pop();

        if (valor.get(1) == Identificador.EXPRESION) {
            System.out.println(valor.get(1));

            switch ((Identificador) valor.get(1)) {
                case EXPRESION:
                    System.out.println("Expresion");
                    System.out.println(valor);
                    Bloque += " " + valor.get(1) + " ";
                    //listaTotal.add(valor);
                    lista.add(valor);
                    System.out.println("Lista : " + lista);
                    if (((List<Object>) pila.peek()).get(0) == TipoOtro.CORCLS) {
                        //lista.add(valor);
                        Cierre(pila);
                    }else if (((List<Object>) pila.peek()).get(0) == TipoOtro.COMA) {
                        lista.add(valor);
                        Coma(pila);
                    }  else {
                        System.out.println("valor siguiente  :  " + pila.peek());
                        //listaTotal.add(valor);
                    }
                   
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
    
    public List<Object> Coma(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(0) ==  TipoOtro.COMA) {
            System.out.println(valor.get(0));

            switch (( TipoOtro) valor.get(0)) {
                case COMA:
                    System.out.println("Coma");
                    System.out.println(valor);
                    Bloque += " " + valor.get(1) + " ";
                    //listaTotal.add(valor);
                    lista.add(valor);
                    System.out.println("Lista : " + lista);
                    if (((List<Object>) pila.peek()).get(1) == Identificador.EXPRESION) {
                        //lista.add(valor);
                        Expresion(pila);
                    }   else {
                        System.out.println("valor siguiente  :  " + pila.peek());
                        //listaTotal.add(valor);
                    }
                   
                    return lista;

                default:
                    listaTotal.add(valor);
                    break;
            }

        } else {
            System.out.println("valor de inicio : " + valor);
            listaTotal.add(valor);
            System.out.println("valor de inicio listatotal: " + listaTotal);
        }
        return null;

    }
    
    private List<List<Object>> Cierre(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        List<Object> valorn = null;

        if (valor.get(0) == TipoOtro.CORCLS) {
            System.out.println(valor);

            switch ((TipoOtro) valor.get(0)) {
                case CORCLS:
                    System.out.println("Cierre ");
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    System.out.println("Lista : " + lista);
                    lista = new ArrayList<>(lista);
                    List<Object> listaenlista = new ArrayList<>();
                    listaenlista.add(lista);
                    System.out.println("lista en lista : " + listaenlista);
                    //lista.add(Identificador.EXPRESION);
                    lista = Arrays.asList(lista, Identificador.OPIPERT, "Operador pertenencia", Bloque);
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
        }else{
            listaTotal.add(valor);
        }

        return null;

    }
}
