/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.analizador.Sintactico;

import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoEspacio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlos117
 */
public class Sintactico {
    private final List<List<Object>> maptoken ;
    private List<List<Object>> tokensFiltrados ;

    
    public Sintactico(List<List<Object>>  maptoken) {
        this.maptoken = maptoken;
    }
    
    public void valorSintactico (){
        System.out.println("Lista de listas Tokens : " + maptoken);
        String[] valor = {};
        //TokenPrueba[] asigString = [TipoIdentificador.ID,TipoIdentificador.ID,TipoAsignacion.ASIG,TipoConstante.CADENA];
        for (List<Object> token : maptoken) {
            if (!token.get(1).equals("SPACE")) {
                tokensFiltrados.add(token);
            }
        }
        System.out.println("Lista de listas Tokens Filtrado: " + maptoken);
    }
}
