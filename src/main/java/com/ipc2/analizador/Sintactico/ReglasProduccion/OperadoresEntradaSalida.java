/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.analizador.Sintactico.ReglasProduccion;

import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoOtro;
import static com.ipc2.analizador.Lexico.Token.TipoTokens.TipoOtro.PAROPN;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoPalabraRes;
import static com.ipc2.analizador.Sintactico.ReglasProduccion.Identificador.EXPRESION;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author carlos117
 */
public class OperadoresEntradaSalida {
    private List<Object> lista;
    private final List<List<Object>> listaTotal;
    private String Bloque;

    public OperadoresEntradaSalida() {
        lista = new ArrayList<>();
        listaTotal = new ArrayList<>();
        Bloque = "";
    }

    public List<List<Object>> Entrada(Stack pila) {
        Bloque = "";
        lista = new ArrayList<>();
        //listaTotal.clear();
        List<Object> valor = (List<Object>) pila.pop();

        if (valor.get(0) == TipoPalabraRes.PRINT) {
            System.out.println(valor.get(0));

            switch ((TipoPalabraRes) valor.get(0)) {
                case PRINT:
                    System.out.println("print");
                    System.out.println(valor);
                    Bloque +=  valor.get(1) + " ";
                    
                    if(((List<Object>)pila.peek()).get(0) == TipoOtro.PAROPN){
                        lista.add(valor);
                        paretesisAbierto(pila);
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

    public List<Object> paretesisAbierto(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(0) == TipoOtro.PAROPN) {

            System.out.println(valor);

            switch ((TipoOtro) valor.get(0)) {
                case PAROPN:
                    System.out.println("Parentesis Abierto");
                    System.out.println(valor);
                    Bloque += valor.get(1) + " ";
                    //listaTotal.add(valor);
                    if(((List<Object>)pila.peek()).get(1) == Identificador.EXPRESION){
                        lista.add(valor);
                        Expresion(pila);
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
    
    public List<Object> Expresion(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(1) == Identificador.EXPRESION) {

            System.out.println(valor);

            switch ((Identificador) valor.get(1)) {
                case EXPRESION:
                    System.out.println("Expresion");
                    System.out.println(valor);
                    Bloque += valor.get(3) + " ";
                    if(((List<Object>)pila.peek()).get(0) == TipoOtro.PARCLS){
                        lista.add(valor);
                        parentesisCerrado(pila);
                    }else if(((List<Object>)pila.peek()).get(0) == TipoOtro.COMA){
                        lista.add(valor);
                        coma(pila);
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
    
    public List<Object> coma(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(0) == TipoOtro.COMA) {

            System.out.println(valor);

            switch ((TipoOtro) valor.get(0)) {
                case COMA:
                    System.out.println("Coma");
                    System.out.println(valor);
                    Bloque += valor.get(1) + " ";
                    //listaTotal.add(valor);
                    if(((List<Object>)pila.peek()).get(1) == Identificador.EXPRESION){
                        lista.add(valor);
                        Expresion(pila);
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
    
    public List<List<Object>> parentesisCerrado(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();

        if (valor.get(0) == TipoOtro.PARCLS) {
            System.out.println(valor);

            switch ((TipoOtro) valor.get(0)) {
                case PARCLS :
                    System.out.println("Parentesis Cerrado");
                    Bloque += valor.get(3) + " ";
                    lista.add(valor);

                    lista = new ArrayList<>(lista);
                    List<Object> listaenlista = new ArrayList<>();
                    listaenlista.add(lista);
                    
                    System.out.println("lista en lista : " + listaenlista);
                    //lista.add(Identificador.EXPRESION);
                    lista = Arrays.asList( lista, Identificador.OPENTSAL, "Operador Entrada y Salida",Bloque);
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
