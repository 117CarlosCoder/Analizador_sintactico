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
import com.ipc2.analizador.Lexico.Token.TokenPrueba;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PDA_1 {

    private final List<Object> lista;
    private final List<List<Object>> listatokens;
    private int indexGuard;
    private int actual;
    private int indexRegla;
    private boolean valorB;

    public PDA_1() {
        actual = 0;
        indexGuard = 0;
        listatokens = new ArrayList<>();
        lista = new ArrayList<>();
        indexRegla = 0;
        valorB = false;
    }

    private final Object[][] reglasDeProduccion = {
        {TipoIdentificador.ID, TipoAsignacion.ASIG, TipoConstante.CADENA},
        {TipoIdentificador.ID, TipoAsignacion.ASIG, TipoConstante.INTEGER}
    };
    
    private final Object[][] idetificacionProduccuion = {
        {"Declaracion y Asignacion de cadena" ,"Falta el signo = ", "Falta valor a asignar"},
        {"Declaracion y Asignacion de entero" ,"Falta el signo = ", "Falta valor a asignar"}
    };

   
    private List<List<Object>> coincideConReglaDeProduccion(List<List<Object>> tokensValidos) {
        for ( actual = 0; actual < reglasDeProduccion.length; actual++ ) {
            if(valorB){
                actual = 0;
                valorB = false;
            }
            
           
            System.err.println("\n");
            System.out.println("varlos i : " + actual);
            indexRegla  = actual;
            Object tokens = coincideConRegla(tokensValidos, reglasDeProduccion[indexRegla], actual);
            if(tokens != null){
                listatokens.add(Arrays.asList(((List)tokens).get(0),Arrays.asList(reglasDeProduccion[indexRegla]),((List)tokens).get(2)," "," ",((List)tokens).get(3),(((List)tokens).get(4))," "));
            }
            
            lista.clear();
            
            if (!valorB && actual == reglasDeProduccion.length-1 ) {
                actual = -1;
                //indexGuard++;
            }
           
            if(tokensValidos.size()-1 == indexGuard){
                actual = reglasDeProduccion.length;
            }
            
        }
        
        System.out.println("Token Total " + listatokens);
        
        return listatokens;
    }

    public Object coincideConRegla(List<List<Object>> tokensValidos, Object[] regla, int index) {
        
        System.out.println("valor guard : " + indexGuard);
        System.out.println("tokensValidos : " + tokensValidos);
        System.out.println("regla : " + Arrays.toString(regla));
        String bloque = "";
       
        for (int i = 0 ; i < regla.length; i++) {
            List<Object> token = tokensValidos.get(indexGuard);
            if(token.get(0) == TipoEspacio.EOF){
                
                return null;
            }
            
            indexGuard++;
            Object tipoToken = token.get(0);
            lista.add(tipoToken);
            System.out.println("Token : " + token);
            System.out.println("Lista Token : " + lista);
            
             if (index == (reglasDeProduccion.length - 1) && (!tipoToken.equals(regla[i]))){
                    indexGuard++;
            }
            
            
            if (tipoToken.equals(regla[i])) {
                
                bloque += token.get(1).toString();
                
                System.out.println("Lista Token : " + lista + " regla produc : " + Arrays.toString(regla));
                if(Arrays.equals(lista.toArray(), regla)){
                   
                    //indexGuard = i;
                    System.out.println("valor guard2 : " + indexGuard);
                    System.out.println("ident regla : " + Arrays.toString(idetificacionProduccuion[indexRegla]));
                    System.out.println("ident regla : " + idetificacionProduccuion[indexRegla][0]);
                    valorB = true;
                    actual = -1;
                    //valorfijo += i;
                    //indexGuard -= 2;
                    return Arrays.asList(idetificacionProduccuion[indexRegla][0],tipoToken,bloque, token.get(2), token.get(3));
                }
                
            }
            
            if (!tipoToken.equals(regla[i])) {
                               
                indexGuard -= i;
                indexGuard--;
                System.out.println("sizee list : " + lista.size() );
                if (lista.size() > 1 && indexRegla == 2) {
                    indexGuard--;
                    System.out.println("cambiar valor: " + indexGuard);
                }
                lista.clear(); 
                i = regla.length;
                bloque = "";
                
            }
            
           
            
        }
        return null;
    }



    public List<List<Object>> validarTok(String input) {
        
        indexGuard = 0;
        listatokens.clear();
        lista.clear();
        indexRegla = 0;
        valorB = false;
      
        List<List<Object>> listaToken = new ArrayList<>();
    

        LexicoPrueba lexicon = new LexicoPrueba(input);
        TokenPrueba tokenn;
        System.out.println(input);
        int i = 0;
        do {
            tokenn = lexicon.nextToken();
            if (tokenn.type != TipoEspacio.SPACE) {
                List<Object> listaT = Arrays.asList(tokenn.type,tokenn.value,tokenn.fila,tokenn.columna);
                listaToken.add(listaT);
                System.out.println("tokens agregados inicio : " + tokenn.type);
                System.out.println("tokens agregados inicio : " + listaToken);
            }

        } while (tokenn.type != TipoEspacio.EOF);

        
        return coincideConReglaDeProduccion(listaToken);        
        
    }
    
    /*public static void main(String[] args) {
        String input  = 
                  "asdasd \n"
                + "dfsfdsdf\n"
                + "asdsad = 7 \n"
                + "midsd = 'asdas'\n"
                + "sdad\n"
                + "asdas = 3\n"
                + "asd = 'asda'";
        PDA_1 PDA = new PDA_1();
        PDA.validarTok(input);
    }*/

    

}
