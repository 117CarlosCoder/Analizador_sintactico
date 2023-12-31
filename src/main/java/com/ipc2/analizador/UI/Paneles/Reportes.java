/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.ipc2.analizador.UI.Paneles;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author carlos117
 */
public class Reportes extends javax.swing.JPanel {

  
    public static JButton button;

    /**
     * Creates new form Reportes
     */
    public Reportes() {
        initComponents();

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
        jTable1 = new javax.swing.JTable();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token", "Patron", "Lemax", "Fila", "Columna", "Imagen"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    public void insertarInfo(List<List<Object>> listOfLists){
        jTable1.removeAll();
        jTable1.repaint();
        
        DefaultTableModel tableModel = new DefaultTableModel(
        new Object[][] {},
        new String[] {"Token", "Patron", "Lexema", "Fila", "Columna", "Imagen"}
        );

        for (List<Object> list : listOfLists) {
            tableModel.addRow(list.toArray());
                    jTable1.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());

        }

        SwingUtilities.invokeLater(() -> {
            jTable1.setModel(tableModel);
            tableModel.fireTableDataChanged();
            jTable1.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
            jTable1.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor());
        });
        
        
    }
    
   public class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText("Generar"); // Texto en el botón

        // Configurar el botón para cada fila
        if (isSelected) {
            setBackground(table.getSelectionBackground());
        } else {
            setBackground(table.getBackground());
        }

        return this;
    }
}

public class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private int clickedRow;
    private int clickedColumn;

    public ButtonEditor() {
        super(new JTextField());
        button = new JButton();
        button.setOpaque(true);

        // Acción al hacer clic en el botón
        button.addActionListener((ActionEvent e) -> {
            Object lexemaValue = jTable1.getValueAt(clickedRow, 2); // Para la columna "Lexema"
            Imagen imagen = new Imagen();
            imagen.GenerarImagen(lexemaValue);
            imagen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            imagen.setVisible(true);
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        clickedRow = row;
        clickedColumn = column;
        button.setText("Generar"); // Texto en el botón
        return button;
    }
}

    
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
