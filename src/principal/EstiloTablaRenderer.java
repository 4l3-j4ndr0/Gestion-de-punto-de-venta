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
            componenete.setForeground(Color.BLACK);
            componenete.setBackground(new Color(232, 232, 232));
        } else {
            componenete.setForeground(Color.BLACK);
            componenete.setBackground(Color.WHITE);
        }
        if (table.getValueAt(row, 3) != null) {
            double numero = Double.parseDouble(table.getValueAt(row, 3).toString());
            if (numero < 10 && numero > 0) {
                setBackground(Color.YELLOW);
                setForeground(Color.BLACK);
            }
            if (numero == 0) {
                setBackground(Color.RED);
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
