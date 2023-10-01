/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.analizador.Sintactico;

/**
 *
 * @author carlos117
 */
import com.ipc2.analizador.Lexico.LexicoPrueba;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoAsignacion;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoConstante;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoEspacio;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoIdentificador;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoPalabraRes;
import com.ipc2.analizador.Lexico.Token.TokenPrueba;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PDA {

    private final Stack<Object> stack;
    private State currentState;
    private final List<List<Object>> listatokens;

    public PDA() {
        stack = new Stack<>();
        currentState = State.START;
        listatokens = new ArrayList<>();
    }

    private final Object[][] reglasDeProduccion = {
        {TipoPalabraRes.VAR, TipoIdentificador.ID, TipoAsignacion.ASIG, TipoConstante.CADENA},
        {TipoPalabraRes.VAR, TipoIdentificador.ID, TipoAsignacion.ASIG, TipoConstante.INTEGER},
        {TipoIdentificador.ID, TipoAsignacion.ASIG, TipoConstante.CADENA},
        {TipoIdentificador.ID, TipoAsignacion.ASIG, TipoConstante.INTEGER}
    };

    public boolean procesarEntrada(List<List<Object>> entradaTokens) {
        System.out.println("Cargando");
        currentState = State.START;
        for (List<Object> token : entradaTokens) {
            Object tipoToken = token.get(0);
            Object valorToken = token.get(1);
            System.out.println("valores : " + tipoToken + "    " + valorToken);
            
            transition(tipoToken, valorToken);
            if (currentState == State.REJECT) {
                System.out.println("retorno falso");
                return false;
            }
        }

        // Al finalizar, verifica que la pila esté vacía (se han emparejado todas las producciones).
        return stack.isEmpty();
    }

// Implementa la función de transición según las reglas de tu gramática.
    private void transition(Object tipoToken, Object valorToken) {
        System.out.println("Cagando transiciones");
        switch (currentState) {
            case START:
                if (tipoToken == TipoPalabraRes.VAR) {
                    System.out.println(currentState);
                    stack.push(TipoPalabraRes.VAR);
                    System.out.println("Valor: "+Arrays.toString(isTokenValido(tipoToken, valorToken, currentState)));
                    currentState = State.EXPECT_ID;
                    
                    
                } else {
                    System.out.println(currentState);
                    currentState = State.REJECT;
                }
                break;
            case EXPECT_ID:
                if (tipoToken == TipoIdentificador.ID) {
                    System.out.println(currentState);
                    stack.push(TipoIdentificador.ID);
                    System.out.println("Valor: "+Arrays.toString(isTokenValido(tipoToken, valorToken, currentState)));
                    currentState = State.EXPECT_ASIG;
           
                } else {
                    System.out.println(currentState);
                    currentState = State.REJECT;
                }
                break;
            case EXPECT_ASIG:
                if (tipoToken == TipoAsignacion.ASIG) {
                    System.out.println(currentState);
                    stack.push(TipoAsignacion.ASIG);
                    System.out.println("Valor: "+Arrays.toString(isTokenValido(tipoToken, valorToken, currentState)));
                    currentState = State.EXPECT_VALOR;
                } else {
                    
                    System.out.println(currentState);
                    currentState = State.REJECT;
                }
                break;
            case EXPECT_VALOR:
                if (tipoToken == TipoConstante.CADENA || tipoToken == TipoConstante.INTEGER) {
                    System.out.println(currentState);
                    stack.pop(); // Pop de ASIG
                    stack.pop(); // Pop de ID
                    stack.pop(); // Pop de VAR
                    System.out.println("Valor: "+Arrays.toString(isTokenValido(tipoToken, valorToken, currentState)));// Vuelve al estado inicial para la próxima declaración
                    currentState = State.START;
                    
                    
                } else {
                    System.out.println(currentState);
                    currentState = State.REJECT;
                    System.out.println("Valor: "+Arrays.toString(isTokenValido(tipoToken, valorToken, currentState)));
                }
                break;
            case REJECT:
                // En el estado de rechazo, no hacemos nada más
                break;
        }
    }
    
    private Object[] isTokenValido(Object tipoToken, Object valorToken, State estadoActual) {
        // Verifica si el token actual coincide con alguna de las reglas de producción
        for (Object[] regla : reglasDeProduccion) {
            if (matchRegla(regla, tipoToken, valorToken, estadoActual)) {
                return regla;
            }
        }
        return null;
    }

    private boolean matchRegla(Object[] regla, Object tipoToken, Object valorToken, State estadoActual) {
        // Verifica si el token actual coincide con una regla específica
        for (int i = 0; i < regla.length; i++) {
            Object tokenEsperado = regla[i];

            // Compara el token esperado con el tipo del token actual
            if (tokenEsperado.equals(tipoToken)) {
                // Si es el último token en la regla, verifica si el estado actual es correcto
                if (i == regla.length - 1) {
                    // Si el estado actual es START, la regla es válida
                    if (estadoActual == State.START) {
                        return true;
                    }
                } else {
                    // Actualiza el estado actual para el siguiente token esperado
                    if (estadoActual == State.START) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        String input = "var nombre = 'John'\n"
                + "nombre = 'Juan'";
        List<List<Object>> listaToken = new ArrayList<>();

        LexicoPrueba lexicon = new LexicoPrueba(input);
        TokenPrueba tokenn;
        int i = 0;
        do {
            tokenn = lexicon.nextToken();
            if (tokenn.type != TipoEspacio.SPACE) {
                List<Object> listaT = Arrays.asList(tokenn.type, tokenn.value);
                listaToken.add(listaT);
                System.out.println("tokens agregados inicio : " + tokenn.type);
                System.out.println("tokens agregados inicio : " + listaToken);
            }

        } while (tokenn.type != TipoEspacio.EOF);

        PDA pda = new PDA();
        if(!pda.procesarEntrada(listaToken)){
            System.out.println("La entrada es válida: ");
        }
        
    }

    public enum State {
        START,
        EXPECT_VAR,
        EXPECT_ID,
        EXPECT_ASIG,
        EXPECT_VALOR,
        REJECT
    }

}
