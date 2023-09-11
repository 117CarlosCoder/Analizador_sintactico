/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ipc2.analizador.UI.Paneles;

import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Factory;
import static guru.nidi.graphviz.model.Factory.mutGraph;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author carlos117
 */
public class Imagen extends javax.swing.JFrame {
public static JLabel label;
    /**
     * Creates new form Imagen
     */
    public Imagen() {
        initComponents();
        label = new JLabel();
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
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        getContentPane().add(jScrollPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        jPanel1.add(label);
        jPanel1.revalidate();
        
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}