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
            "(\\b[a-zA-Z_]\\w*\\b)|"
            + "((\\d+\\.\\d+)|(\\.\\d+))|"
            + "(\\d+)|"
            + "(\"[^\"]*\"|'[^']*'|\"[^\n]*|'[^\\n]*)|"
            + "(\"[^\n]*$|'[^\\n]*$)|"
            + "('[^']*')|"
            + "(==|!=|>=|<=|>|<)|"
            + "(<<|>>|&|(\\|)|(\\^))|"
            + "(\\+=|-=|\\*=|\\/=|\\**=|\\%=)|"
            + "(=)|"
            + "(\\[)|"
            + "(\\])|"
            + "(\\()|"
            + "(\\))|"
            + "(,)|"
            + "(;)|"
            + "(:)|"
            + "(\\+|\\-|\\*|/|%|\\*\\*)|"
            + "(//)|"
            + "(#[^\n\r]*)|"
            + "([ \\t]+)"
            //+ "(\n)"
    );

    public LexicoPrueba(String input) {
        this.palabrasG = new ArrayList<>();
        this.input = input;
        this.position = 0;
    }

    public TokenPrueba nextToken() {

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

        System.out.println("tipo: " + TipoEspacio.EOF + " token :" + "");
        return new TokenPrueba(TipoEspacio.EOF, "", fila, columna);
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
        palabraResMap.put("print", TipoPalabraRes.PRINT);
        palabraResMap.put("raise", TipoPalabraRes.RAISE);
        palabraResMap.put("return", TipoPalabraRes.RETURN);
        palabraResMap.put("try", TipoPalabraRes.TRY);
        palabraResMap.put("while", TipoPalabraRes.WHILE);
        palabraResMap.put("with", TipoPalabraRes.WITH);
        palabraResMap.put("yield", TipoPalabraRes.YIELD);
        palabraResMap.put("var", TipoPalabraRes.VAR);

        if (palabraResMap.containsKey(tokenText)) {
            TipoPalabraRes tipo = palabraResMap.get(tokenText);
            System.out.println("tipo: " + tipo + " token :" + tokenText);
            return new TokenPrueba(tipo, tokenText, fila, columna);

        } else if (tokenText.matches("\\+|\\-|\\*|/|%|\\*\\*")) {
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
                case "%":
                    tipoAritmetico = TipoAritmetico.MODULO;
                    break;
            }

            System.out.println("tipo: " + tipoAritmetico + " token :" + tokenText);
            return new TokenPrueba(tipoAritmetico, tokenText, fila, columna);

        } else if (tokenText.matches("and")) {
            System.out.println("tipo: " + TipoLogico.AND + " token :" + tokenText);
            return new TokenPrueba(TipoLogico.AND, tokenText, fila, columna);
        } else if (tokenText.matches("or")) {
            System.out.println("tipo: " + TipoLogico.OR + " token :" + tokenText);
            return new TokenPrueba(TipoLogico.OR, tokenText, fila, columna);
        } else if (tokenText.matches("not")) {
            System.out.println("tipo: " + TipoLogico.NOT + " token :" + tokenText);
            return new TokenPrueba(TipoLogico.NOT, tokenText, fila, columna);
        } else if (tokenText.matches("True|False")) {
            System.out.println("tipo: " + TipoConstante.BOOLEAN + " token :" + tokenText);
            return new TokenPrueba(TipoConstante.BOOLEAN, tokenText, fila, columna);
        }else if (tokenText.matches("//")) {
            System.out.println("tipo: " + TipoAritmetico.DIVISION + " token :" + tokenText);
            return new TokenPrueba(TipoAritmetico.DIVISION, tokenText, fila, columna);
        } else if (tokenText.matches("(\\d+\\.\\d+)|(\\.\\d+)")) {
            System.out.println("tipo: " + TipoConstante.DECIMAL + " token :" + tokenText);
            return new TokenPrueba(TipoConstante.DECIMAL, tokenText, fila, columna);
        } else if (tokenText.matches("\\d+")) {
            System.out.println("tipo: " + TipoConstante.INTEGER + " token :" + tokenText);
            return new TokenPrueba(TipoConstante.INTEGER, tokenText, fila, columna);
        } else if (tokenText.matches("#[^\n]*")) {
            System.out.println("tipo: " + TipoComentario.COMENT + " token :" + tokenText);
            return new TokenPrueba(TipoComentario.COMENT, tokenText, fila, columna);
        } else if (tokenText.matches("\\w+")) {
            System.out.println("tipo: " + TipoIdentificador.ID + " token :" + tokenText);
            return new TokenPrueba(TipoIdentificador.ID, tokenText, fila, columna);
        } else if (tokenText.matches("\"[^\"]*\"|'[^']*'")) {
            System.out.println("tipo: " + TipoConstante.CADENA + " token :" + tokenText);
            return new TokenPrueba(TipoConstante.CADENA, tokenText, fila, columna);
        } else if (tokenText.matches("\"[^\n]*$|'[^\\n]*$")) {
            System.out.println("tipo: " + TipoOtro.ERROR + " token :" + tokenText);
            return new TokenPrueba(TipoOtro.ERROR, "Cadena no cerrada: " + tokenText, fila, columna);
        } else if (tokenText.equals("[")) {
            System.out.println("tipo: " + TipoOtro.COROPN + " token :" + tokenText);
            return new TokenPrueba(TipoOtro.COROPN, tokenText, fila, columna);
        } else if (tokenText.equals("(")) {
            System.out.println("tipo: " + TipoOtro.PAROPN + " token :" + tokenText);
            return new TokenPrueba(TipoOtro.PAROPN, tokenText, fila, columna);
        } else if (tokenText.equals(")")) {
            System.out.println("tipo: " + TipoOtro.PARCLS + " token :" + tokenText);
            return new TokenPrueba(TipoOtro.PARCLS, tokenText, fila, columna);
        } else if (tokenText.equals("]")) {
            System.out.println("tipo: " + TipoOtro.CORCLS + " token :" + tokenText);
            return new TokenPrueba(TipoOtro.CORCLS, tokenText, fila, columna);
        } else if (tokenText.equals(",")) {
            System.out.println("tipo: " + TipoOtro.COMA + " token :" + tokenText);
            return new TokenPrueba(TipoOtro.COMA, tokenText, fila, columna);
        } else if (tokenText.equals(":")) {
            System.out.println("tipo: " + TipoOtro.PUNTDOB + " token :" + tokenText);
            return new TokenPrueba(TipoOtro.PUNTDOB, tokenText, fila, columna);
        } else if (tokenText.equals(";")) {
            System.out.println("tipo: " + TipoOtro.PUNTCOMA + " token :" + tokenText);
            return new TokenPrueba(TipoOtro.PUNTCOMA, tokenText, fila, columna);
        } else if (tokenText.equals("=")) {
            System.out.println("tipo: " + TipoAsignacion.ASIG + " token :" + tokenText);
            return new TokenPrueba(TipoAsignacion.ASIG, tokenText, fila, columna);
        } else if (tokenText.equals("==")) {
            System.out.println("tipo: " + TipoComparacion.IGUAL + " token :" + tokenText);
            return new TokenPrueba(TipoComparacion.IGUAL, tokenText, fila, columna);
        } else if (tokenText.equals("!=")) {
            System.out.println("tipo: " + TipoComparacion.DIFERENTE + " token :" + tokenText);
            return new TokenPrueba(TipoComparacion.DIFERENTE, tokenText, fila, columna);
        } else if (tokenText.equals("<=")) {
            System.out.println("tipo: " + TipoComparacion.OPRMNI + " token :" + tokenText);
            return new TokenPrueba(TipoComparacion.OPRMNI, tokenText, fila, columna);
        } else if (tokenText.equals(">=")) {
            System.out.println("tipo: " + TipoComparacion.OPRMYI + " token :" + tokenText);
            return new TokenPrueba(TipoComparacion.OPRMYI, tokenText, fila, columna);
        } else if (tokenText.equals("<")) {
            System.out.println("tipo: " + TipoComparacion.OPRMNQ + " token :" + tokenText);
            return new TokenPrueba(TipoComparacion.OPRMNQ, tokenText, fila, columna);
        } else if (tokenText.equals(">")) {
            System.out.println("tipo: " + TipoComparacion.OPRMYQ + " token :" + tokenText);
            return new TokenPrueba(TipoComparacion.OPRMYQ, tokenText, fila, columna);
        } else if (tokenText.equals("+=")) {
            System.out.println("tipo: " + TipoOperadorAsignacion.SUMAI + " token :" + tokenText);
            return new TokenPrueba(TipoOperadorAsignacion.SUMAI, tokenText, fila, columna);
        } else if (tokenText.equals("-=")) {
            System.out.println("tipo: " + TipoOperadorAsignacion.RESTAI + " token :" + tokenText);
            return new TokenPrueba(TipoOperadorAsignacion.RESTAI, tokenText, fila, columna);
        } else if (tokenText.equals("*=")) {
            System.out.println("tipo: " + TipoOperadorAsignacion.MULTII+ " token :" + tokenText);
            return new TokenPrueba(TipoOperadorAsignacion.MULTII, tokenText, fila, columna);
        } else if (tokenText.equals("/=")) {
            System.out.println("tipo: " + TipoOperadorAsignacion.DIVI+ " token :" + tokenText);
            return new TokenPrueba(TipoOperadorAsignacion.DIVI, tokenText, fila, columna);
        } else if (tokenText.equals("**=")) {
            System.out.println("tipo: " + TipoOperadorAsignacion.DOBLEMULTII+ " token :" + tokenText);
            return new TokenPrueba(TipoOperadorAsignacion.DOBLEMULTII, tokenText, fila, columna);
        } else if (tokenText.equals("%=")) {
            System.out.println("tipo: " + TipoOperadorAsignacion.MODULOI + " token :" + tokenText);
            return new TokenPrueba(TipoOperadorAsignacion.MODULOI, tokenText, fila, columna);
        } else if (tokenText.equals("<<")) {
            System.out.println("tipo: " + TipoBits.DEZIZQ + " token :" + tokenText);
            return new TokenPrueba(TipoBits.DEZIZQ , tokenText, fila, columna);
        } else if (tokenText.equals(">>")) {
            System.out.println("tipo: " + TipoBits.DEZDER  + " token :" + tokenText);
            return new TokenPrueba(TipoBits.DEZDER , tokenText, fila, columna);
        } else if (tokenText.equals("&")) {
            System.out.println("tipo: " + TipoBits.AND  + " token :" + tokenText);
            return new TokenPrueba(TipoBits.AND , tokenText, fila, columna);
        } else if (tokenText.equals("|")) {
            System.out.println("tipo: " + TipoBits.OR + " token :" + tokenText);
            return new TokenPrueba(TipoBits.OR, tokenText, fila, columna);
        } else if (tokenText.equals("^")) {
            System.out.println("tipo: " + TipoBits.XOR + " token :" + tokenText);
            return new TokenPrueba(TipoBits.XOR, tokenText, fila, columna);
        }/* else if (tokenText.equals("\n")) {
            System.out.println("tipo: " +TipoEspacio.SALTO + " token :" + tokenText);
            return new TokenPrueba(TipoEspacio.SALTO, tokenText, fila, columna);
        }*/ else if (tokenText.matches("[ \\t]+")) {
            System.out.println("tipo: " + TipoEspacio.SPACE + " token :" + " ");
            return new TokenPrueba(TipoEspacio.SPACE, " ", fila, columna);
        } else {
            return new TokenPrueba(TipoOtro.ERROR, "Caracter Desconocido", fila, columna);
        }

    }

    private void actualizarPosicion(String tokenText) {
        System.out.println("texto " + tokenText);
        for (char c : tokenText.toCharArray()) {
            columna++;
        }
    }

}
