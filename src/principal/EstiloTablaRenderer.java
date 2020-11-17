/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import productos.OpcionesAl;

/**
 *
 * @author 4l3
 */
public class EstiloTablaRenderer extends DefaultTableCellRenderer {

    private Component componenete;
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;  //
}

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componenete = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.       
        this.setHorizontalAlignment(0);
        this.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(0, 0, 0)));

        if (row % 2 == 0) {
           componenete.setBackground(Color.WHITE);
           componenete.setForeground(Color.BLACK);
        } else {
            componenete.setBackground(Color.white); //new Color(255,204,153)
            componenete.setForeground(Color.BLACK);
        }
        
        if (table.getValueAt(row, 3) != null) {
            int numero = Integer.parseInt(table.getValueAt(row, 3).toString());
            if (numero <= Integer.parseInt(OpcionesAl.extraer_stock("SELECT `stock_minimo` FROM `alimentos` WHERE `codigo_al`='"+table.getValueAt(row, 0).toString()+"'")) && numero > 0) {
                setBackground(Color.YELLOW);
                setForeground(Color.BLACK);
            }
            if (numero == 0) {
                setBackground(Color.RED);
                setForeground(Color.BLACK);
            }
            if ( numero >= Integer.parseInt(OpcionesAl.extraer_stock("SELECT `stock_maximo` FROM `alimentos` WHERE `codigo_al`='"+table.getValueAt(row, 0).toString()+"'"))) {
                setBackground(Color.GREEN);
                setForeground(Color.BLACK);
            }
        }

        if (isSelected) {
            componenete.setForeground(Color.WHITE);
            componenete.setBackground(new Color(28, 134, 238));
        }
        
        isCellEditable(row, column);
        return componenete;

    }
    

}
