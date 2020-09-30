/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author 4L3
 */
public class EstiloTablaVentasAgrupado extends DefaultTableCellRenderer{

    private Component componenete;
String texto="";
        int fila = 0;
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componenete = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.       
        this.setHorizontalAlignment(0);
        this.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(0, 0, 0)));

        if (row % 2 == 0) {
           componenete.setBackground(Color.green);
           componenete.setForeground(Color.BLACK);
        } else {
            componenete.setBackground(new Color(255,204,153));
            componenete.setForeground(Color.BLACK);
        }
     
        //  agrupar x color
//        if (!table.getValueAt(row, 0).toString().equals("")) {
//            componenete.setBackground(new Color(240,149,149));
//        }else{
//            componenete.setBackground(new Color(252,245,176));
//        }
//        
//        if(row<table.getRowCount()-1){
//            if (!table.getValueAt(row, 0).toString().equals("")) {
//                componenete.setBackground(new Color(240,149,149));
//                if(row<table.getRowCount()-1){
//                    if(table.getValueAt(row+1,0).toString().equals("")){
//                        componenete.setBackground(new Color(240,149,149));
//                    }
//                
//            }else {
//                componenete.setBackground(new Color(252,245,176));
//            }
//            }
//        }else{
//            if(table.getValueAt(row,0).toString().equals(table.getValueAt(row-1, 0))){
//                       table.getValueAt(row-1,0)
//                componenete.setBackground(new Color(240,149,149));
//                    }
//        }
       
        if (isSelected) {
            componenete.setForeground(Color.WHITE);
            componenete.setBackground(new Color(28, 134, 238));
        }
        return componenete;

    }
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;  //
}
}
