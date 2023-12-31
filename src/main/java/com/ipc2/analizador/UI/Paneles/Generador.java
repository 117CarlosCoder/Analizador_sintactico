/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.ipc2.analizador.UI.Paneles;

import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoAritmetico;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoAsignacion;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoComentario;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoComparacion;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoConstante;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoIdentificador;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoLogico;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoOtro;
import com.ipc2.analizador.Lexico.Token.TipoTokens.TipoPalabraRes;
import com.ipc2.analizador.UI.Inicio;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Factory;
import static guru.nidi.graphviz.model.Factory.mutGraph;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author carlos117
 */
public class Generador extends javax.swing.JPanel {

    
    
    /**
     * Creates new form Generador
     */
    private final JPanel jPanel1;
    private JLabel label;
    private JPopupMenu popupMenu; 
    private final HashMap<Object,Object> mapaToltal;
    
    public Generador() {
        initComponents();
        jPanel1 = new JPanel();
        label = new JLabel();
        popupMenu = new JPopupMenu();
        mapaToltal = new HashMap<>();
        setLayout(new BorderLayout()); 
        add(jScrollPane1, BorderLayout.WEST); 
        add(jPanel1, BorderLayout.CENTER);  
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setPreferredSize(new java.awt.Dimension(600, 300));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setMaximumSize(new java.awt.Dimension(300, 200));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 300));

        jList1.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Identificadores", "Aritméticos", "Comparación", "Logicos", "Palabras Clave","Asignación", "Constante", "Comentario" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setMaximumSize(new java.awt.Dimension(600, 700));
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 270, 200));
    }// </editor-fold>//GEN-END:initComponents

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        if (!evt.getValueIsAdjusting()) {
            int selectedIndex = jList1.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedElement = jList1.getModel().getElementAt(selectedIndex);
                System.out.println("Selected: " + selectedElement);
                if("Identificadores".equals(selectedElement)){
                    jPanel1.removeAll();
                    jPanel1.repaint();
                    Identificador(Inicio.todoToken());
                    //showPopupMenu();
                    System.out.println("Aqui en el generador esta el hash map " + mapaToltal);
                    mapaToltal.clear();
                }
                if("Aritméticos".equals(selectedElement)){
                    jPanel1.removeAll();
                    jPanel1.repaint();
                    Arimetico(Inicio.todoToken());
                    //showPopupMenu();
                    System.out.println("Aqui en el generador esta el hash map " + mapaToltal );
                    mapaToltal.clear();
                }
                if("Comparación".equals(selectedElement)){
                    jPanel1.removeAll();
                    jPanel1.repaint();
                    Comparacion(Inicio.todoToken());
                    //showPopupMenu();
                    System.out.println("Aqui en el generador esta el hash map " + mapaToltal);
                    mapaToltal.clear();
                }
                if("Logicos".equals(selectedElement)){
                    jPanel1.removeAll();
                    jPanel1.repaint();
                    Logico(Inicio.todoToken());
                    //showPopupMenu();
                    System.out.println("Aqui en el generador esta el hash map " + mapaToltal);
                    mapaToltal.clear();
                }
                if("Palabras Clave".equals(selectedElement)){
                    jPanel1.removeAll();
                    jPanel1.repaint();
                    PalabrasRes(Inicio.todoToken());
                    //showPopupMenu();
                    System.out.println("Aqui en el generador esta el hash map " + mapaToltal);
                    mapaToltal.clear();
                }
                if("Constante".equals(selectedElement)){
                    jPanel1.removeAll();
                    jPanel1.repaint();
                    Constante(Inicio.todoToken());
                    //showPopupMenu();
                    System.out.println("Aqui en el generador esta el hash map " + mapaToltal);
                    mapaToltal.clear();
                }
                if("Asignación".equals(selectedElement)){
                    jPanel1.removeAll();
                    jPanel1.repaint();
                    Asignacion(Inicio.todoToken());
                    //showPopupMenu();
                    System.out.println("Aqui en el generador esta el hash map " + mapaToltal);
                    mapaToltal.clear();
                }
                if("Comentario".equals(selectedElement)){
                    jPanel1.removeAll();
                    jPanel1.repaint();
                    Comentario(Inicio.todoToken());
                    //showPopupMenu();
                    System.out.println("Aqui en el generador esta el hash map " + mapaToltal);
                    mapaToltal.clear();
                }
            }
        }
    }//GEN-LAST:event_jList1ValueChanged

    public void GenerarImagen(Object Token){
        String prueba = Token.toString();
        System.out.println("valos prueba en metodo repetir " +prueba);
        int cantidadletra = prueba.length();
        char charprueba;
        
        MutableNode[] nodesnew = new MutableNode[cantidadletra];
    
    for (int i = 0; i < cantidadletra; i++) {
        charprueba = prueba.charAt(i);
        
        if(i == cantidadletra-1){
            nodesnew[i] =  Factory.mutNode(String.valueOf(charprueba)).add(Shape.DOUBLE_CIRCLE);
        }
        else{
        nodesnew[i] =  Factory.mutNode(String.valueOf(charprueba)).add(Shape.CIRCLE);
        }
    }
    
    MutableNode previousNode = null;  // Para llevar un registro del nodo anterior

    MutableGraph gnew = mutGraph("diagrama").setDirected(true)
        .graphAttrs().add(Rank.dir(Rank.RankDir.LEFT_TO_RIGHT));
    
    for (int i = 0; i < cantidadletra; i++) {
        MutableNode currentNode = nodesnew[i];
        gnew.add(currentNode);

        if (previousNode != null && !previousNode.equals(currentNode)) {
            previousNode.addLink(currentNode);
        }

        previousNode = currentNode;
    }
       
        // Renderizar el gráfico a una imagen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Graphviz.fromGraph(gnew).render(Format.PNG).toOutputStream(outputStream);
        } catch (IOException ex) {
        }

        // Convertir la imagen en un BufferedImage
        BufferedImage image = null;
        try {
            image = ImageIO.read(new ByteArrayInputStream(outputStream.toByteArray()));
        } catch (IOException e) {
        }

        label = new JLabel(new ImageIcon(image));
        //setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        jPanel1.add(label);
        jPanel1.revalidate();
       
    }
    
    public void reset(){
        jPanel1.remove(label);
        popupMenu.setVisible(false);
        jPanel1.repaint();
        
    }
    
    public void PalabrasRes(HashMap mapTokens){
        
        
        for (Object key : mapTokens.keySet()) {
            
            if (key instanceof TipoPalabraRes) {
                System.out.println(key + ": " + mapTokens.get(key));
                mapaToltal.put(key,mapTokens.get(key));                    
            }
        }
        
        for (Object key : mapaToltal.keySet()) {
            GenerarImagen(mapaToltal.get(key));
            System.out.println(mapaToltal.get(key));
        }
        
    }
    
    public void Arimetico(HashMap mapTokens){
        
        
        for (Object key : mapTokens.keySet()) {
            
            if (key instanceof TipoAritmetico) {
                System.out.println(key + ": " + mapTokens.get(key));
                mapaToltal.put(key,mapTokens.get(key));      
                
            }
        }
        
        for (Object key : mapaToltal.keySet()) {
            GenerarImagen(mapaToltal.get(key));
            System.out.println(mapaToltal.get(key));
        }
        
        
    }
    public void Asignacion(HashMap mapTokens){
        
        
        for (Object key : mapTokens.keySet()) {
            
            if (key instanceof TipoAsignacion) {
                System.out.println(key + ": " + mapTokens.get(key));
                //GenerarImagen(mapTokens.get(key));
                mapaToltal.put(key,mapTokens.get(key));
                
            }
        }
        for (Object key : mapaToltal.keySet()) {
            GenerarImagen(mapaToltal.get(key));
            System.out.println(mapaToltal.get(key));
        }
        
        
    }
    public void Comentario(HashMap mapTokens){
        
        
        for (Object key : mapTokens.keySet()) {
            
            if (key instanceof TipoComentario) {
                System.out.println(key + ": " + mapTokens.get(key));
                //GenerarImagen(mapTokens.get(key));
                mapaToltal.put(key,mapTokens.get(key));
                
            }
        }
        for (Object key : mapaToltal.keySet()) {
            GenerarImagen(mapaToltal.get(key));
            System.out.println(mapaToltal.get(key));
        }
        
        
    }
    public void Comparacion(HashMap mapTokens){
        
        
        for (Object key : mapTokens.keySet()) {
            
            if (key instanceof TipoComparacion) {
                System.out.println(key + ": " + mapTokens.get(key));
                //GenerarImagen(mapTokens.get(key));
                mapaToltal.put(key,mapTokens.get(key));
                
            }
        }
        for (Object key : mapaToltal.keySet()) {
            GenerarImagen(mapaToltal.get(key));
            System.out.println(mapaToltal.get(key));
        }
        
        
    }
    
    public void Constante(HashMap mapTokens){
        
        
        for (Object key : mapTokens.keySet()) {
            
            if (key instanceof TipoConstante) {
                System.out.println(key + ": " + mapTokens.get(key));
                //GenerarImagen(mapTokens.get(key));
                mapaToltal.put(key,mapTokens.get(key));
                
            }
        }
        for (Object key : mapaToltal.keySet()) {
            GenerarImagen(mapaToltal.get(key));
            System.out.println(mapaToltal.get(key));
        }
        
        
    }
    
    public void Logico(HashMap mapTokens){
        
        
        for (Object key : mapTokens.keySet()) {
            
            if (key instanceof TipoLogico) {
                System.out.println(key + ": " + mapTokens.get(key));
                //GenerarImagen(mapTokens.get(key));
                mapaToltal.put(key,mapTokens.get(key));
                
            }
        }
        for (Object key : mapaToltal.keySet()) {
            GenerarImagen(mapaToltal.get(key));
            System.out.println(mapaToltal.get(key));
        }
        
        
    }
    
    public void Identificador(HashMap mapTokens){
        
        
        for (Object key : mapTokens.keySet()) {
            
            if (key instanceof TipoIdentificador) {
                System.out.println(key + ": " + mapTokens.get(key));
                //GenerarImagen(mapTokens.get(key));
                mapaToltal.put(key,mapTokens.get(key));
                
            }
        }
        for (Object key : mapaToltal.keySet()) {
            GenerarImagen(mapaToltal.get(key));
            System.out.println(mapaToltal.get(key));
        }
        
        
    }
    
    public void Otro(HashMap mapTokens){
        
        
        for (Object key : mapTokens.keySet()) {
            
            if (key instanceof TipoOtro) {
                System.out.println(key + ": " + mapTokens.get(key));
                //GenerarImagen(mapTokens.get(key));
                mapaToltal.put(key,mapTokens.get(key));
                
            }
        }
        for (Object key : mapaToltal.keySet()) {
            GenerarImagen(mapaToltal.get(key));
            System.out.println(mapaToltal.get(key));
        }
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
