/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.analizador.Lexico.Token;

/**
 *
 * @author carlos117
 */
public class TokenPrueba {
    public Object type;
    public String value;
    public int fila;
    public int columna;

    public TokenPrueba(Object type, String value,int fila, int columna) {
        this.type = type;
        this.value = value;
        this.fila = fila;
        this.columna = columna;
    }
}
