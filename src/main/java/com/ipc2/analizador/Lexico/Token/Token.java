/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.analizador.Lexico.Token;

/**
 *
 * @author carlos117
 */
public class Token {
    public Object type;
    public String value;

    public Token(Object type, String value) {
        this.type = type;
        this.value = value;
    }
}
