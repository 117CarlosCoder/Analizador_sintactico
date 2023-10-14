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
public class CondicionElse {
    
    private List<Object> lista;
        private List<List<Object>> listaTotal;
        private String Bloque;

    public CondicionElse(List<Object> lista, List<List<Object>> listaTotal) {
        lista = new ArrayList<>();
        listaTotal = new ArrayList<>();
        Bloque = "";
    }
    
    public List<List<Object>> EntradaElse(Stack pila) {

         Bloque = "";
        lista = new ArrayList<>();
        //listaTotal.clear();
         
        List<Object> valor = (List<Object>) pila.pop();
        if (valor.get(0) == TipoPalabraRes.ELSE ) {

            System.out.println(valor);

            switch ((TipoPalabraRes) valor.get(0)) {
                case ELSE:
                    System.out.println("Palabra res Else");
                    System.out.println(valor);
                    Bloque += valor.get(1) + " ";
                    //listaTotal.add(valor);
                    if(((List<Object>)pila.peek()).get(0) == TipoOtro.PUNTDOB){
                        lista.add(valor);
                        doblePuntoElse(pila);
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
    
    
    public List<Object> doblePuntoElse(Stack pila) {

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
                        bloqueElse(pila);
                    }else if(((List<Object>)pila.peek()).get(1) == Identificador.ASIG){
                        lista.add(valor);
                        bloqueElse(pila);
                    }else if(((List<Object>)pila.peek()).get(1) == Identificador.ASIGCOP){
                        lista.add(valor);
                        bloqueElse(pila);
                    }else if(((List<Object>)pila.peek()).get(1) == Identificador.ASIGOPBITS){
                        lista.add(valor);
                        bloqueElse(pila);
                    }else if(((List<Object>)pila.peek()).get(1) == Identificador.ASIGOPCOMP){
                        lista.add(valor);
                        bloqueElse(pila);
                    }else if(((List<Object>)pila.peek()).get(1) == Identificador.ASIGOPIDNT){
                        lista.add(valor);
                        bloqueElse(pila);
                    }else if(((List<Object>)pila.peek()).get(1) == Identificador.ASIGOPLOGIC){
                        lista.add(valor);
                        bloqueElse(pila);
                    }else if(((List<Object>)pila.peek()).get(1) == Identificador.ASIGOPPERT){
                        lista.add(valor);
                        bloqueElse(pila);
                    }else if(((List<Object>)pila.peek()).get(1) == Identificador.OPTERNARIO){
                        lista.add(valor);
                        bloqueElse(pila);
                    }else if(((List<Object>)pila.peek()).get(1) == Identificador.OPENTSAL){
                        lista.add(valor);
                        bloqueElse(pila);
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
    
    public List<List<Object>> bloqueElse(Stack pila) {

        List<Object> valor = (List<Object>) pila.pop();

        if (valor.get(1) instanceof Identificador) {
            System.out.println(valor);

            switch ((Identificador) valor.get(1)) {
                case EXPRESION :
                    System.out.println("Expresion");
                    Bloque += valor.get(3) + " ";
                    lista.add(valor);
                    if(((List<Object>)pila.peek()).get(1) == Identificador.EXPRESION){
                        bloqueElse(pila);
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
                        bloqueElse(pila);
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
                        bloqueElse(pila);
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
                        bloqueElse(pila);
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
                        bloqueElse(pila);
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
                        bloqueElse(pila);
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
                        bloqueElse(pila);
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
                        bloqueElse(pila);
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
                        bloqueElse(pila);
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
                        bloqueElse(pila);
                    }else{
                        lista = new ArrayList<>(lista);
                        List<Object> listaenlista = new ArrayList<>();
                        listaenlista.add(lista);

                        System.out.println("lista en lista : " + listaenlista);
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
                        EntradaElse(pila);
                    }
                    break;
            }
        }else{
            listaTotal.add(valor);
        }
        return null;

    }
}
