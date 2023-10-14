/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.analizador.Sintactico.ReglasProduccion;

import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoIdentificador;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoIdentificador.ID;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoOperadorAsignacion;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoOperadorAsignacion.DOBLEMULTII;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoOperadorAsignacion.MULTII;
import static com.ipc2.analizador.Sintactico.ReglasProduccion.Identificador.EXPRESION;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;



/**
 *
 * @author carlos117
 */
public class AsignacionOperador {

 private List<Object> lista;
    private final List<List<Object>> listaTotal;
    private String Bloque;

    public AsignacionOperador() {
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
                    if(((List<Object>)pila.peek()).get(0) instanceof TipoOperadorAsignacion){
                        System.out.println("Asignacion de Operador valor siguiente  :  " + pila.peek());
                        lista.add(valor);
                        AsignadorOperador(pila);
                    }else{
                        System.out.println("valor siguiente  :  " + pila.peek());
                        listaTotal.add(valor);
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

    public List<Object> AsignadorOperador(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(0) instanceof TipoOperadorAsignacion) {

            System.out.println(valor);

            switch ((TipoOperadorAsignacion) valor.get(0)) {
                case SUMAI:
                    System.out.println("Suma igual");
                    System.out.println(valor);
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Valor(pila);
                    return lista;
                case RESTAI:
                    System.out.println("Resta igual");
                    System.out.println(valor);
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Valor(pila);
                    return lista;
                case DIVI:
                    System.out.println("Division igual");
                    System.out.println(valor);
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Valor(pila);
                    return lista;
                case MULTII:
                    System.out.println("Multiplicacion Igual");
                    System.out.println(valor);
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Valor(pila);
                    return lista;
                case DOBLEMULTII:
                    System.out.println("Multiplicacion doble Igual");
                    System.out.println(valor);
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Valor(pila);
                    return lista;
                case MODULOI:
                    System.out.println("Modulo Igual");
                    System.out.println(valor);
                    Bloque += valor.get(1) + " ";
                    lista.add(valor);
                    Valor(pila);
                    return lista;
                default:
                    System.out.println("Error");
                    listaTotal.add(valor);
                    return null;
            }
        }
        else{
            listaTotal.add(valor);
        }
        return null;

    }

    private List<List<Object>> Valor(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();

        if (valor.get(1) == Identificador.EXPRESION) {
            System.out.println(valor);

            switch ((Identificador) valor.get(1)) {
                case EXPRESION:
                    System.out.println("Expresion n");
                    Bloque += valor.get(3) + " ";
                    lista.add(valor);

                    lista = new ArrayList<>(lista);
                    List<Object> listaenlista = new ArrayList<>();
                    listaenlista.add(lista);
                    
                    System.out.println("lista en lista : " + listaenlista);
                    //lista.add(Identificador.EXPRESION);
                    lista = Arrays.asList( lista, Identificador.ASIGCOP, "Asignacion Operador",Bloque);
                    listaTotal.add(lista);
                    System.out.println(listaTotal);
                    

                    return listaTotal;
                    
                default:
                    System.out.println("Error se esperaba una expresion");
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
