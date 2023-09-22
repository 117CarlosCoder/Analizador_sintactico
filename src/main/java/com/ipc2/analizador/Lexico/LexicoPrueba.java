package com.ipc2.analizador.Lexico;

import com.ipc2.analizador.Lexico.Token.TipoTokens.*;
import com.ipc2.analizador.Lexico.Token.TokenPrueba;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author calin
 */

public class LexicoPrueba {
    private final String input;
    private int position;
    private int fila = 1;
    private int columna = 0;
    private final ArrayList<Object> palabrasG;
    private static final Pattern tokenPattern = Pattern.compile(
        "(\\b[a-zA-Z_]\\w*\\b)|" +  // Identificador o palabra reservada
        "((\\d+\\.\\d+)|(\\.\\d+))|" +  // Número decimal
        "(\\d+)|" +  // Número entero
        "(\"[^\"]*\"|'[^']*'|\"[^\n]*|'[^\\n]*)|" + 
        "(\"[^\n]*$|'[^\\n]*$)|"+// Cadena entre comillas dobles
        "('[^']*')|" +  // Cadena entre comillas simples
        "(==|!=|>=|<=|>|<)|" +  // Operadores de comparación
        "(=)|" +  // Operador de asignación
        "(\\()|" + 
        "(\\))|" +// Paréntesis y llaves
        "(,)|" +  // Coma
        "(;)|" +  // Punto y coma
        "(:)|" +  // Dos puntos
        "(\\+|\\-|\\*|/|\\*\\*)|" +  // Operadores aritméticos
        "(//)|" +  // Operador de división
        "(#[^\n\r]*)|"+
        "([ \\t]+)"
            
    );
    
    //private final Matcher matcher;

    public LexicoPrueba(String input) {
        this.palabrasG = new ArrayList<>();
        this.input = input;
        this.position = 0;
        //this.matcher = tokenPattern.matcher(input); 
    }

    public TokenPrueba nextToken() {
        
        
        
        /*
        System.out.println(matcher.regionStart() + "  " + matcher.regionEnd());
        
        try{
            matcher.region(position, input.length()); // Establece la región de búsqueda
        }catch(Exception e){
            System.out.println("error : " + e );
        }
        


        if (matcher.find()) {
            // Encuentra el siguiente token
            String tokenText = matcher.group();
            
            actualizarPosicion(tokenText);
            
            // Verifica si el token es un salto de línea
            if ("\n".equals(tokenText)||"\n".contains(tokenText)) {
                fila++;
                columna = 1;
            } else {
                TokenPrueba token = createToken(tokenText);

                // Actualiza la posición para el siguiente token
               

                return token;
            }
        }        
            // Si no se encuentra ningún token válido, devuelve un token EOF
            System.out.println("tipo: " + TipoEspacio.EOF + " token :" + "");
            return new TokenPrueba(TipoEspacio.EOF, "", fila, columna);
        */
        
       

        // Si se encontró un salto de línea, incrementa la fila y reinicia la columna
        
        
        
        
       /* while ( fila > 1 && position < input.length() && input.charAt(position) != '\n') {
            System.out.println("while " + input.charAt(position));
            position++;
            
        }*/
        
        if (position < input.length() && input.charAt(position) == '\n') {
            System.out.println(input.charAt(position));
            fila++;
            columna = 0;
        }
        
        
        
        Matcher matcher = tokenPattern.matcher(input); 
    
        
        if (matcher.find(position)) {
            
            position = matcher.end();
            for (int i = 1; i <= matcher.groupCount(); i++) {
                if (matcher.group(i) != null) {
                    String tokenText = matcher.group(i);
                    
                    actualizarPosicion(tokenText);
                    return createToken(tokenText);
           
             }
            }
        } 
        
        
        
        /*Matcher matcher = tokenPattern.matcher(input);
        matcher.region(position, input.length()); // Establece la región de búsqueda\\
        
        

        if (matcher.find()) {
            // Encuentra el siguiente token
            String tokenText = matcher.group();
            
            //actualizarPosicion(tokenText);
            
            TokenPrueba token = createToken(tokenText);

            // Actualiza la posición para el siguiente token
            

            return token;
        }*/
        
        
        System.out.println("tipo: " + TipoEspacio.EOF + " token :" + "");
        return new TokenPrueba(TipoEspacio.EOF, "",fila,columna);
    }
    
    public TokenPrueba pruebast(){
        Matcher matcher3 = tokenPattern.matcher(input);
        
        
        while (matcher3.find(position)) {
            position = matcher3.end();
            for (int i = 1; i <= matcher3.groupCount(); i++) {
                if (matcher3.group(i) != null) {
                    String tokenText = matcher3.group(i);
                    
                    return createToken(tokenText);
                }
            }
        }
        
        System.out.println("tipo: " + TipoEspacio.EOF + " token :" + "");
        return new TokenPrueba(TipoEspacio.EOF, "",fila,columna);
        
    }

    private TokenPrueba createToken(String tokenText) {
        Map<String, TipoPalabraRes> palabraResMap = new HashMap<>();
        palabraResMap.put("as", TipoPalabraRes.AS);
        palabraResMap.put("assert", TipoPalabraRes.ASSERT);
        palabraResMap.put("break", TipoPalabraRes.BREAK);
        palabraResMap.put("class", TipoPalabraRes.CLASS);
        palabraResMap.put("continue", TipoPalabraRes.CONTINUE);
        palabraResMap.put("def", TipoPalabraRes.DEF);
        palabraResMap.put("del", TipoPalabraRes.DEL);
        palabraResMap.put("for", TipoPalabraRes.FOR);
        palabraResMap.put("elif", TipoPalabraRes.ELIF);
        palabraResMap.put("else", TipoPalabraRes.ELSE);
        palabraResMap.put("except", TipoPalabraRes.EXCEPT);
        palabraResMap.put("finally", TipoPalabraRes.FINALLY);
        palabraResMap.put("from", TipoPalabraRes.FROM);
        palabraResMap.put("global", TipoPalabraRes.GLOBAL);
        palabraResMap.put("import", TipoPalabraRes.IMPORT);
        palabraResMap.put("in", TipoPalabraRes.IN);
        palabraResMap.put("is", TipoPalabraRes.IS);
        palabraResMap.put("if", TipoPalabraRes.IF);
        palabraResMap.put("lambda", TipoPalabraRes.LAMBDA);
        palabraResMap.put("None", TipoPalabraRes.NONE);
        palabraResMap.put("nonlocal", TipoPalabraRes.NONLOCAL);
        palabraResMap.put("pass", TipoPalabraRes.PASS);
        palabraResMap.put("raise", TipoPalabraRes.RAISE);
        palabraResMap.put("return", TipoPalabraRes.RETURN);
        palabraResMap.put("try", TipoPalabraRes.TRY);
        palabraResMap.put("while", TipoPalabraRes.WHILE);
        palabraResMap.put("with", TipoPalabraRes.WITH);
        palabraResMap.put("yield", TipoPalabraRes.YIELD);
        
        
       
        if (palabraResMap.containsKey(tokenText)) {
            TipoPalabraRes tipo = palabraResMap.get(tokenText);
            System.out.println("tipo: " + tipo + " token :" + tokenText);
            return new TokenPrueba(tipo, tokenText,fila,columna);
        
        } else if (tokenText.matches("\\+|\\-|\\*|/|\\*\\*")) {
        TipoAritmetico tipoAritmetico = null;
        switch (tokenText) {
            case "+":
                tipoAritmetico = TipoAritmetico.SUMA;
                break;
            case "-":
                tipoAritmetico = TipoAritmetico.RES;
                break;
            case "*":
                tipoAritmetico = TipoAritmetico.MULTI;
                break;
            case "/":
                tipoAritmetico = TipoAritmetico.DIVISION;
                break;
        }
        
            System.out.println("tipo: " + tipoAritmetico + " token :" + tokenText);
            return new TokenPrueba(tipoAritmetico, tokenText,fila,columna);
        
        } else if (tokenText.matches("//")) {
            System.out.println("tipo: " + TipoAritmetico.DIVISION + " token :" + tokenText);
                return new TokenPrueba(TipoAritmetico.DIVISION, tokenText,fila,columna);
        } else if (tokenText.matches("(\\d+\\.\\d+)|(\\.\\d+)")) {
            System.out.println("tipo: " + TipoConstante.DECIMAL + " token :" + tokenText);
                return new TokenPrueba(TipoConstante.DECIMAL, tokenText,fila,columna);
        } else if (tokenText.matches("\\d+")) {
            System.out.println("tipo: " + TipoConstante.INTEGER + " token :" + tokenText);
                return new TokenPrueba(TipoConstante.INTEGER, tokenText,fila,columna);
        } else if (tokenText.matches("#[^\n]*")) {
            System.out.println("tipo: " + TipoComentario.COMENT + " token :" + tokenText);
                return new TokenPrueba(TipoComentario.COMENT, tokenText,fila,columna);
        } else if (tokenText.matches("\\w+")) {
            System.out.println("tipo: " + TipoIdentificador.ID + " token :" + tokenText);
                return new TokenPrueba(TipoIdentificador.ID, tokenText,fila,columna);
        } else if (tokenText.matches("\"[^\"]*\"|'[^']*'")) {
            System.out.println("tipo: " + TipoConstante.CADENA + " token :" + tokenText);
                return new TokenPrueba(TipoConstante.CADENA, tokenText,fila,columna);
        } else if (tokenText.matches("\"[^\n]*$|'[^\\n]*$")) {
            System.out.println("tipo: " + TipoOtro.ERROR + " token :" + tokenText);
            return new TokenPrueba(TipoOtro.ERROR, "Cadena no cerrada: " + tokenText, fila, columna);
        }
         else if (tokenText.equals("[")) {
            System.out.println("tipo: " + TipoOtro.COROPN + " token :" + tokenText);
                return new TokenPrueba(TipoOtro.COROPN, tokenText,fila,columna);
        } else if (tokenText.equals("(")) {
            System.out.println("tipo: " + TipoOtro.PAROPN + " token :" + tokenText);
                return new TokenPrueba(TipoOtro.PAROPN, tokenText,fila,columna);
        } else if (tokenText.equals(")")) {
            System.out.println("tipo: " + TipoOtro.PARCLS + " token :" + tokenText);
                return new TokenPrueba(TipoOtro.PARCLS, tokenText,fila,columna);
        } else if (tokenText.equals("]")) {
            System.out.println("tipo: " + TipoOtro.CORCLS + " token :" + tokenText);
                return new TokenPrueba(TipoOtro.CORCLS, tokenText,fila,columna);
        } else if (tokenText.equals(",")) {
            System.out.println("tipo: " + TipoOtro.COMA + " token :" + tokenText);
                return new TokenPrueba(TipoOtro.COMA, tokenText,fila,columna);
        } else if (tokenText.equals(":")) {
            System.out.println("tipo: " + TipoOtro.PUNTDOB + " token :" + tokenText);
                return new TokenPrueba(TipoOtro.PUNTDOB, tokenText,fila,columna);
        } else if (tokenText.equals(";")) {
            System.out.println("tipo: " + TipoOtro.PUNTCOMA + " token :" + tokenText);
                return new TokenPrueba(TipoOtro.PUNTCOMA, tokenText,fila,columna);
        } else if (tokenText.equals("=")) {
            System.out.println("tipo: " + TipoAsignacion.ASIG + " token :" + tokenText);
                return new TokenPrueba(TipoAsignacion.ASIG, tokenText,fila,columna);
        } else if (tokenText.equals("==")) {
            System.out.println("tipo: " + TipoComparacion.IGUAL + " token :" + tokenText);
                return new TokenPrueba(TipoComparacion.IGUAL, tokenText,fila,columna);
        } else if (tokenText.equals("!=")) {
            System.out.println("tipo: " + TipoComparacion.DIFERENTE + " token :" + tokenText);
                return new TokenPrueba(TipoComparacion.DIFERENTE, tokenText,fila,columna);
        } else if (tokenText.equals("<=")) {
            System.out.println("tipo: " + TipoComparacion.OPRMNI + " token :" + tokenText);
                return new TokenPrueba(TipoComparacion.OPRMNI, tokenText,fila,columna);
        } else if (tokenText.equals(">=")) {
            System.out.println("tipo: " + TipoComparacion.OPRMYI + " token :" + tokenText);
                return new TokenPrueba(TipoComparacion.OPRMYI, tokenText,fila,columna);
        } else if (tokenText.equals("<")) {
            System.out.println("tipo: " + TipoComparacion.OPRMNQ + " token :" + tokenText);
                return new TokenPrueba(TipoComparacion.OPRMNQ, tokenText,fila,columna);
        } else if (tokenText.equals(">")) {
            System.out.println("tipo: " + TipoComparacion.OPRMYQ + " token :" + tokenText);
                return new TokenPrueba(TipoComparacion.OPRMYQ, tokenText,fila,columna);
        } else if (tokenText.matches("[ \\t]+")) {
           
            System.out.println("tipo: " + TipoEspacio.SPACE + " token :" + " ");
            return new TokenPrueba(TipoEspacio.SPACE, " ",fila,columna);
        }
        else{
            return new TokenPrueba(TipoOtro.ERROR, "Caracter Desconocido",fila,columna);
        }
        
    }
    
    private void actualizarPosicion(String tokenText) {
       System.out.println("texto " + tokenText);
        for (char c : tokenText.toCharArray()) {
              columna++;
         }
       
    
       
    }
    
    private void actualizarPosicions(String tokenText) {
        // Actualiza la posición según la longitud del token encontrado
        position += tokenText.length();

        // Cuenta la cantidad de saltos de línea en el token y actualiza la fila en consecuencia
        int saltosDeLinea = tokenText.split("\n", -1).length - 1;
        fila += saltosDeLinea;

        // La columna se reinicia en 1 después de un salto de línea
        if (saltosDeLinea > 0) {
            columna = 1;
        } else {
            columna += tokenText.length();
        }
    }
}
