/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deudas;

import ventas.*;
import com.placeholder.PlaceHolder;
import static deudas.OpcionesDeudas.cantidad_producto_tablaVentas;
import java.awt.Color;
import java.awt.Image;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import principal.EstiloTablaVentas;
import static principal.MenuPrincipalAd.escritorio;
import principal.conectar;
import productos.OpcionesAl;
import reportes.Campo_tabla_deudas_agrupado;
import reportes.Campos_Tabla_Deudas;

/**
 *
 * @author 4l3
 */
public class RegistroDeudas extends javax.swing.JInternalFrame {

    /**
     * Creates new form Usuarios
     */
    public RegistroDeudas() {
        initComponents();
        tablaDeudas.getTableHeader().setDefaultRenderer(new principal.EstiloTablaHeader());
        tablaDeudas.setDefaultRenderer(Object.class, new principal.EstiloTablaVentas());
        this.setFrameIcon(new ImageIcon(getClass().getResource("/imagenes/deuda.png")));
        tablaDeudas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaDeudas1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        limpiaCampos();
        combo_filtro.setVisible(false);
        AutoCompleteDecorator.decorate(combo_filtro);
        ((JLabel) combo_filtro.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        OpcionesDeudas.get_combos_titulares(combo_filtro);
        cantidad_edit.setEnabled(false);
        check_restar_deuda.setEnabled(false);
        
        tablaDeudas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tablaDeudas.getSelectedRow() != -1) {
                   // cambiaDatos();
                   int fila = tablaDeudas.getSelectedRow();
                   if(on_off.isSelected()){
                   cantidad_edit.setText(tablaDeudas.getValueAt(fila, 3).toString());
                   cantidad_edit.setForeground(Color.red);
                   }
                   if(check_restar_deuda.isSelected()){
                        check_restar_deuda.setForeground(Color.red);
                   }else{
                       check_restar_deuda.setForeground(new Color(255,153,153));
                   }
                    selecionRegistro = true;
                }
            }
        });
        
        tablaDeudas1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tablaDeudas1.getSelectedRow() != -1) {
                   // cambiaDatos();
                   int fila = tablaDeudas1.getSelectedRow();
                   if(on_off.isSelected()){
                   cantidad_edit.setText(tablaDeudas1.getValueAt(fila, 1).toString());
                   cantidad_edit.setForeground(Color.red);
                   }
                   if(check_restar_deuda.isSelected()){
                        check_restar_deuda.setForeground(Color.red);
                   }else{
                       check_restar_deuda.setForeground(new Color(255,153,153));
                   }
                    selecionRegistro = true;
                }
            }
        });
    }

    boolean selecionRegistro = false;
    
    void limpiaCampos() {
        if (tablaDeudas.getSelectedRow() > -1) {
            tablaDeudas.removeRowSelectionInterval(tablaDeudas.getSelectedRow(), tablaDeudas.getSelectedRow());
        }
        if (tablaDeudas1.getSelectedRow() > -1) {
            tablaDeudas1.removeRowSelectionInterval(tablaDeudas1.getSelectedRow(), tablaDeudas1.getSelectedRow());
        }
        fecha.setDate(null);
        buscar2.setText("");
        cantidad_edit.setText("CANTIDAD");
        cantidad_edit.setForeground(new Color(255,153,153));
        check_restar_deuda.setSelected(false);
        check_restar_deuda.setForeground(new Color(255,153,153));
        OpcionesDeudas.listar("");
        organizar_tabla();
        montoTotal();
        ganancia_total();
        inversion_total();
        on_off.setSelected(false);
        cantidad_edit.setEnabled(false);
            check_restar_deuda.setSelected(false);
            check_restar_deuda.setEnabled(false);
            check_restar_deuda.setForeground(new Color(255,153,153));
            cantidad_edit.setText("CANTIDAD");
        //organizar_tabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        eliminar = new javax.swing.JButton();
        eliminarT = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        ventasH = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        monto_total = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        fecha = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        buscF = new javax.swing.JButton();
        buscar2 = new app.bolivia.swing.JCTextField();
        codigoL2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        ganancia_total = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        eliminar1 = new javax.swing.JButton();
        eliminarT1 = new javax.swing.JButton();
        limpiar1 = new javax.swing.JButton();
        ventasH1 = new javax.swing.JButton();
        ventasH2 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        check_filtro = new javax.swing.JCheckBox();
        combo_filtro = new javax.swing.JComboBox<>();
        panel_no_agrupado = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDeudas = new javax.swing.JTable();
        panel_agrupado = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDeudas1 = new javax.swing.JTable();
        panel_inversion = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        inversion_total = new javax.swing.JLabel();
        panel_edit_cantidad = new javax.swing.JPanel();
        cantidad_edit = new javax.swing.JTextField();
        check_restar_deuda = new javax.swing.JCheckBox();
        on_off = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "OPCIONES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        eliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/borrar1.png"))); // NOI18N
        eliminar.setText("Eliminar");
        eliminar.setBorder(null);
        eliminar.setBorderPainted(false);
        eliminar.setContentAreaFilled(false);
        eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eliminar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/borrar2.png"))); // NOI18N
        eliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPanel4.add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        eliminarT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        eliminarT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/borrarT1.png"))); // NOI18N
        eliminarT.setText("Eliminar Todo");
        eliminarT.setBorder(null);
        eliminarT.setBorderPainted(false);
        eliminarT.setContentAreaFilled(false);
        eliminarT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        eliminarT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eliminarT.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/borrarT2.png"))); // NOI18N
        eliminarT.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        eliminarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarTActionPerformed(evt);
            }
        });
        jPanel4.add(eliminarT, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, -1));

        limpiar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/limpiar1.png"))); // NOI18N
        limpiar.setText("Limpiar Campos");
        limpiar.setBorder(null);
        limpiar.setBorderPainted(false);
        limpiar.setContentAreaFilled(false);
        limpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        limpiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        limpiar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/limpiar2.png"))); // NOI18N
        limpiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });
        jPanel4.add(limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        ventasH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ventasH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/ventasH1.png"))); // NOI18N
        ventasH.setToolTipText("Buscar");
        ventasH.setBorder(null);
        ventasH.setBorderPainted(false);
        ventasH.setContentAreaFilled(false);
        ventasH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ventasH.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ventasH.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/ventasH2.png"))); // NOI18N
        ventasH.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ventasH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasHActionPerformed(evt);
            }
        });
        jPanel4.add(ventasH, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, 50));

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("REGISTRO DEUDAS");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("MONTO TOTAL"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        monto_total.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        monto_total.setForeground(new java.awt.Color(0, 102, 0));
        monto_total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        monto_total.setText("jLabel4");
        jPanel3.add(monto_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 16, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 0));
        jLabel3.setText("$");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 14, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 310, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("BUSQUEDA"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fecha.setDateFormatString("dd/MM/yyyy");
        fecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 150, 30));

        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Fecha");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        buscF.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buscF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/buscaF1.png"))); // NOI18N
        buscF.setToolTipText("Buscar");
        buscF.setBorder(null);
        buscF.setBorderPainted(false);
        buscF.setContentAreaFilled(false);
        buscF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buscF.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscF.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/buscaF2.png"))); // NOI18N
        buscF.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buscF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscFActionPerformed(evt);
            }
        });
        jPanel2.add(buscF, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, -1, -1));

        buscar2.setBackground(new java.awt.Color(34, 102, 145));
        buscar2.setBorder(null);
        buscar2.setForeground(new java.awt.Color(255, 255, 255));
        buscar2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buscar2.setOpaque(false);
        buscar2.setPhColor(new java.awt.Color(255, 255, 255));
        buscar2.setPlaceholder("CÓDIGO/NOMBRE");
        buscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar2ActionPerformed(evt);
            }
        });
        buscar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscar2KeyReleased(evt);
            }
        });
        jPanel2.add(buscar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 150, -1));

        codigoL2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        codigoL2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/buscarL.png"))); // NOI18N
        jPanel2.add(codigoL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 210, 52));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 230, 170));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("GANANCIA TOTAL"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 0));
        jLabel5.setText("$");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 14, -1, -1));

        ganancia_total.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        ganancia_total.setForeground(new java.awt.Color(0, 102, 0));
        ganancia_total.setText("jLabel2");
        jPanel5.add(ganancia_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 16, -1, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 310, 50));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("OPCIONES"));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        eliminar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        eliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/borrar1.png"))); // NOI18N
        eliminar1.setText("Eliminar/Editar");
        eliminar1.setBorder(null);
        eliminar1.setBorderPainted(false);
        eliminar1.setContentAreaFilled(false);
        eliminar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        eliminar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eliminar1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/borrar2.png"))); // NOI18N
        eliminar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        eliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar1ActionPerformed(evt);
            }
        });
        jPanel6.add(eliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        eliminarT1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        eliminarT1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/borrarT1.png"))); // NOI18N
        eliminarT1.setText("Eliminar Todo");
        eliminarT1.setBorder(null);
        eliminarT1.setBorderPainted(false);
        eliminarT1.setContentAreaFilled(false);
        eliminarT1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        eliminarT1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eliminarT1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/borrarT2.png"))); // NOI18N
        eliminarT1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        eliminarT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarT1ActionPerformed(evt);
            }
        });
        jPanel6.add(eliminarT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, -1));

        limpiar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        limpiar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/limpiar1.png"))); // NOI18N
        limpiar1.setText("Limpiar Campos");
        limpiar1.setBorder(null);
        limpiar1.setBorderPainted(false);
        limpiar1.setContentAreaFilled(false);
        limpiar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        limpiar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        limpiar1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/limpiar2.png"))); // NOI18N
        limpiar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        limpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiar1ActionPerformed(evt);
            }
        });
        jPanel6.add(limpiar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, -1, -1));

        ventasH1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ventasH1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ventasH1.png"))); // NOI18N
        ventasH1.setToolTipText("Buscar");
        ventasH1.setBorder(null);
        ventasH1.setBorderPainted(false);
        ventasH1.setContentAreaFilled(false);
        ventasH1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ventasH1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ventasH1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ventasH2.png"))); // NOI18N
        ventasH1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ventasH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasH1ActionPerformed(evt);
            }
        });
        jPanel6.add(ventasH1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, 50));

        ventasH2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ventasH2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/allDeudas1.png"))); // NOI18N
        ventasH2.setToolTipText("Buscar");
        ventasH2.setBorder(null);
        ventasH2.setBorderPainted(false);
        ventasH2.setContentAreaFilled(false);
        ventasH2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ventasH2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ventasH2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/allDeudas2.png"))); // NOI18N
        ventasH2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ventasH2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasH2ActionPerformed(evt);
            }
        });
        jPanel6.add(ventasH2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 170, 50));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("FILTRADO"));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        check_filtro.setBackground(new java.awt.Color(255, 255, 255));
        check_filtro.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        check_filtro.setForeground(new java.awt.Color(0, 0, 0));
        check_filtro.setText("ACTIVAR");
        check_filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_filtroActionPerformed(evt);
            }
        });
        jPanel8.add(check_filtro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 27, 90, -1));

        combo_filtro.setBackground(new java.awt.Color(51, 0, 255));
        combo_filtro.setEditable(true);
        combo_filtro.setForeground(new java.awt.Color(0, 0, 0));
        combo_filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_filtroActionPerformed(evt);
            }
        });
        jPanel8.add(combo_filtro, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 23, 170, -1));

        jPanel6.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 320, 60));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 7, 340, 250));

        panel_no_agrupado.setBackground(new java.awt.Color(255, 255, 255));
        panel_no_agrupado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaDeudas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. VENTA", "TITULAR", "PRODUCTO", "CANTIDAD", "TOTAL", "INVERSION", "GANANCIA", "FECHA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDeudas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(tablaDeudas);

        panel_no_agrupado.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 900, 280));

        jPanel1.add(panel_no_agrupado, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 260, -1, 310));

        panel_agrupado.setBackground(new java.awt.Color(255, 255, 255));
        panel_agrupado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaDeudas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TITULAR", "TOTAL", "INVERSION", "GANANCIA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDeudas1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane2.setViewportView(tablaDeudas1);

        panel_agrupado.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 900, 280));

        jPanel1.add(panel_agrupado, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 260, -1, 310));

        panel_inversion.setBackground(new java.awt.Color(255, 255, 255));
        panel_inversion.setBorder(javax.swing.BorderFactory.createTitledBorder("INVERSION TOTAL"));
        panel_inversion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 0));
        jLabel4.setText("$");
        panel_inversion.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 14, -1, -1));

        inversion_total.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        inversion_total.setForeground(new java.awt.Color(0, 102, 0));
        inversion_total.setText("jLabel2");
        panel_inversion.add(inversion_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 16, -1, -1));

        jPanel1.add(panel_inversion, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 310, 50));

        panel_edit_cantidad.setBackground(new java.awt.Color(255, 255, 255));
        panel_edit_cantidad.setBorder(javax.swing.BorderFactory.createTitledBorder("EDITAR CANTIDAD"));
        panel_edit_cantidad.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cantidad_edit.setBackground(new java.awt.Color(255, 255, 255));
        cantidad_edit.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cantidad_edit.setForeground(new java.awt.Color(255, 153, 153));
        cantidad_edit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cantidad_edit.setText("CANTIDAD");
        cantidad_edit.setBorder(null);
        panel_edit_cantidad.add(cantidad_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 23, 110, -1));

        check_restar_deuda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        check_restar_deuda.setForeground(new java.awt.Color(255, 153, 153));
        check_restar_deuda.setText("Restar cantidad");
        check_restar_deuda.setBorder(null);
        check_restar_deuda.setOpaque(false);
        check_restar_deuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_restar_deudaActionPerformed(evt);
            }
        });
        panel_edit_cantidad.add(check_restar_deuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 26, -1, -1));

        jPanel1.add(panel_edit_cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, 280, 60));

        on_off.setBackground(new java.awt.Color(255, 255, 255));
        on_off.setForeground(new java.awt.Color(0, 102, 0));
        on_off.setToolTipText("Activar/Desactivar \"EDITAR CANTIDAD\"");
        on_off.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        on_off.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        on_off.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/apagar.png"))); // NOI18N
        on_off.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/apagar.png"))); // NOI18N
        on_off.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/on.png"))); // NOI18N
        on_off.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/on.png"))); // NOI18N
        on_off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                on_offActionPerformed(evt);
            }
        });
        jPanel1.add(on_off, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 190, 60, 60));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imp.png"))); // NOI18N
        jButton1.setToolTipText("Imprimir / Guardar Reporte");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imp2.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 5, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        limpiaCampos();
    }//GEN-LAST:event_limpiarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        if (tablaDeudas.getRowCount() > 0) {
            if (tablaDeudas.getSelectedRowCount() > 0) {
                if (JOptionPane.showConfirmDialog(this, "Esta a punto de eliminar\nun registro.\n¿Desea continuar?", "Registro Ventas", JOptionPane.YES_NO_OPTION, 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                    int fila = tablaDeudas.getSelectedRow();
                    String id = tablaDeudas.getValueAt(fila, 0).toString();
                    int elimina = OpcionesVen.eliminar(id);
                    if (elimina != 0) {
                        limpiaCampos();
                        JOptionPane.showMessageDialog(this, "Registro eliminado.", "Registro Ventas", 0,
                                new ImageIcon(getClass().getResource("/imagenes/caja/borrado1.png")));
                        if (principal.MenuPrincipalAd.cerra) {
                            OpcionesVen.numeros();
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un registro.", "Registro Ventas", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
            }

        } else {
            JOptionPane.showMessageDialog(this, "No hay registros\npara eliminar.", "Registro Ventas", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        }
        
    }//GEN-LAST:event_eliminarActionPerformed

    private void eliminarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarTActionPerformed
        if (tablaDeudas.getRowCount() > 0) {
            if (JOptionPane.showConfirmDialog(this, "Esta a punto de elimnar\ntodos los registros.\n¿Desea continuar?", "Registro Ventas", JOptionPane.YES_NO_OPTION, 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                int eliminaT = OpcionesVen.eliminaTodos();
                if (eliminaT != 0) {
                    limpiaCampos();
                    JOptionPane.showMessageDialog(this, "Registros eliminados.", "Registro Ventas", 0,
                            new ImageIcon(getClass().getResource("/imagenes/caja/borrado.png")));
                    if (principal.MenuPrincipalAd.cerra) {
                        OpcionesVen.numeros();
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No hay registros\npara eliminar.", "Registro Ventas", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        }
        
    }//GEN-LAST:event_eliminarTActionPerformed

    private void buscFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscFActionPerformed
        if (fecha.getDate() == null) {
            OpcionesDeudas.listar("");
            ganancia_total();
            montoTotal();
            inversion_total();
        } else {
            String formato = fecha.getDateFormatString();
            Date date = fecha.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            OpcionesDeudas.listar(String.valueOf(sdf.format(date)));
            ganancia_total();
            montoTotal();
            inversion_total();
        }
        organizar_tabla();
    }//GEN-LAST:event_buscFActionPerformed

    private void ventasHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasHActionPerformed
        Date sistemaFech = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecH = formato.format(sistemaFech);
        OpcionesVen.listar(fecH);
        fecha.setDate(null);
        
        ganancia_total();
        inversion_total();
    }//GEN-LAST:event_ventasHActionPerformed

    public void montoTotal(){
        double total=0;
        double ganancia=0;
       /* Date sistemaFech = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecH = formato.format(sistemaFech);*/
       
       //inversion
       if(tablaDeudas.getRowCount()<1){
           monto_total.setText(String.valueOf(total));
       }else{
        for(int i=0;i<tablaDeudas.getRowCount();i++){
           total+= Double.parseDouble(tablaDeudas.getValueAt(i, 5).toString());
        }
        // GANANCIA
        for(int i=0;i<tablaDeudas.getRowCount();i++){
           ganancia+= Double.parseDouble(tablaDeudas.getValueAt(i, 6).toString());
        }
        // monto.setText(String.valueOf(total+ganancia));
      monto_total.setText(String.valueOf(total+ganancia));
    }
      
    }

    
    
    public void inversion_total(){
        double inversion=0;
       /* Date sistemaFech = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecH = formato.format(sistemaFech);*/
       if(tablaDeudas.getRowCount()<1){
           inversion_total.setText(String.valueOf(inversion));
       }else{
        for(int i=0;i<tablaDeudas.getRowCount();i++){
           inversion+= Double.parseDouble(tablaDeudas.getValueAt(i, 5).toString());
        }
         // inversion_total.setText(String.valueOf(inversion));
      inversion_total.setText(String.valueOf(inversion));
    }
    }
    
    public void ganancia_total(){
        double ganancia=0;
       /* Date sistemaFech = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecH = formato.format(sistemaFech);*/
       if(tablaDeudas.getRowCount()<1){
           ganancia_total.setText(String.valueOf(ganancia));
       }else{
        for(int i=0;i<tablaDeudas.getRowCount();i++){
           ganancia+= Double.parseDouble(tablaDeudas.getValueAt(i, 6).toString());
           
        }
      //  ganancia_total.setText(String.valueOf(ganancia));
      ganancia_total.setText(String.valueOf(ganancia));
    }
    }
    
    public void ganancia_total_x_producto(){
        double ganancia=0;
       /* Date sistemaFech = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecH = formato.format(sistemaFech);*/
       if(tablaDeudas1.getRowCount()<1){
           ganancia_total.setText(String.valueOf(ganancia));
       }else{
        for(int i=0;i<tablaDeudas1.getRowCount();i++){
           ganancia+= Double.parseDouble(tablaDeudas1.getValueAt(i, 3).toString());
           
        }
        ganancia_total.setText(String.valueOf(ganancia));
    }
    }
    
    public void montoTotal_x_producto(){
        double total=0;
       if(tablaDeudas1.getRowCount()<1){
           monto_total.setText(String.valueOf(total));
       }else{
        for(int i=0;i<tablaDeudas1.getRowCount();i++){
           total+= Double.parseDouble(tablaDeudas1.getValueAt(i, 1).toString());
        }
    }
       monto_total.setText(String.valueOf(total));
    }
    
    public void inversion_total_x_producto(){
        double inversion=0;
       /* Date sistemaFech = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecH = formato.format(sistemaFech);*/
       if(tablaDeudas1.getRowCount()<1){
           inversion_total.setText(String.valueOf(inversion));
       }else{
        for(int i=0;i<tablaDeudas1.getRowCount();i++){
           inversion+= Double.parseDouble(tablaDeudas1.getValueAt(i, 2).toString());
           inversion_total.setText(String.valueOf(inversion));
        }
    }
    }
    
    public void organizar_tabla(){
        String numero="";
        int fila=0;
      //  tablaVentas.setDefaultRenderer(Object.class, new principal.EstiloTablaVentas());
      //  EstiloTablaVentas estilo=new EstiloTablaVentas();
        for(int i=0;i<tablaDeudas.getRowCount()-1;i++){
            numero=tablaDeudas.getValueAt(fila, 0).toString();
            if(numero.equals(tablaDeudas.getValueAt(i+1, 0).toString())){
                tablaDeudas.setValueAt("", i+1, 0);
                tablaDeudas.setValueAt("", i+1, 1);
                tablaDeudas.setValueAt("", i+1, 4);
                tablaDeudas.setValueAt("", i+1, 7);
            }else{
                fila=i+1;
            }
        }
        
    }
    
    
    private void eliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar1ActionPerformed
        // TODO add your handling code here:
        Runnable runnable1 = new Runnable() {
                    public void run() {
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
                        RegistroDeudas.this.setEnabled(false);
                        //inicio metodo
                        elimina_desagrupado();
                        //fin metodo
                        RegistroDeudas.this.setEnabled(true);
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    }
                };
                Thread t1 = new Thread(runnable1);
                t1.start();
                
           
    }//GEN-LAST:event_eliminar1ActionPerformed

    public void elimina_desagrupado(){
        if (tablaDeudas.getRowCount() > 0) {
            if (tablaDeudas.getSelectedRowCount() > 0) {
                
                    int fila = tablaDeudas.getSelectedRow();
                    int fila2 = tablaDeudas.getSelectedRow();
                     String id ="";
                     String titular ="";
                String descripcion =tablaDeudas.getValueAt(fila, 2).toString();
                     String cant=String.valueOf(Integer.parseInt(tablaDeudas.getValueAt(fila, 3).toString()));
                     System.out.println("fila inicial "+fila);
                    if(!tablaDeudas.getValueAt(fila, 0).toString().equals("")){
                            id = tablaDeudas.getValueAt(fila, 0).toString();
                            titular=tablaDeudas.getValueAt(fila, 1).toString();
                    }else{
                        while(tablaDeudas.getValueAt(fila, 0).toString().equals("")){
                            fila-=1;
                        }
                        id = tablaDeudas.getValueAt(fila, 0).toString();
                        titular=tablaDeudas.getValueAt(fila, 1).toString();
                    }
                    System.out.println("ID "+ id);
                    System.out.println("fila final "+ fila);
                    // hasta aki muestra bien el id de venta
                    if(!on_off.isSelected()){
                        String[] opciones={"Solo la fila seleccionada","Toda la deuda","Cancelar"};
                        if (JOptionPane.showConfirmDialog(this, "Esta a punto de eliminar\nun registro.\n¿Desea continuar?", "Registro Ventas", JOptionPane.YES_NO_OPTION, 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                             int control=OpcionesDeudas.cantidad_coincidencias_id(id);
                             if(control>1){
                         int opcion= JOptionPane.showOptionDialog(this, "¿Desea eliminar solo la fila seleccionada\n"
                                   + "o toda la deuda?", "Control", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png")), opciones, 2);
                             
                            // borrar toda la deuda
                            switch (opcion) {
                                case 1:
                                    //                            if(OpcionesDeudas.existe_venta(id)==false){
//                                while(OpcionesDeudas.existe_deuda(id)==true){
//                                    OpcionesDeudas.registrar_ventas_desde_deudas(id);
//                                    OpcionesDeudas.eliminar(id);
//                                }
//                            }else{
//                                if(OpcionesDeudas.existe_venta_id_descripcion(id, descripcion)){
//                                    while(OpcionesDeudas.existe_venta_id_descripcion(id, descripcion)){
//                                   OpcionesDeudas.registrar_ventas_desde_deudas_sin_id_pero_sola_fila(id, descripcion, OpcionesDeudas.cantidad_registro_venta_sin_on_off(id, descripcion), OpcionesDeudas.total_tabla_registro_venta(id), OpcionesDeudas.inversion_registro_venta_sin_on_off(id, descripcion), OpcionesDeudas.ganancia_registro_VENTA_sin_on_off(id, descripcion));
//                                   OpcionesDeudas.eliminar(id);
//                                    }
//                                }
                                  //  cant=String.valueOf(Integer.parseInt(tablaDeudas.getValueAt(fila, 3).toString()));
                                   
                                    descripcion=tablaDeudas.getValueAt(fila,2).toString();
                                    while(control>0){
                                        
                                        if(OpcionesDeudas.existe_venta_id_descripcion(id, descripcion)){
                                            OpcionesDeudas.actualizar_sin_on_of(descripcion, id);
                                            OpcionesDeudas.eliminar(id);
                                        }else{
                                            OpcionesDeudas.registrar_ventas_desde_deudas(id);
                                            OpcionesDeudas.eliminar(id);
                                        }
                                        if(OpcionesDeudas.existe_deuda(id)){
                                            descripcion=tablaDeudas.getValueAt(fila+1, 2).toString();
                                            cant=String.valueOf(Integer.parseInt(tablaDeudas.getValueAt(fila+1, 3).toString()));
                                            fila+=1;
                                        }
                                        control--;
                                    }
                                        OpcionesVen.actualizar_y_recalcular_total_registro_venta(id);// ACTUALIZA TOTAL EN TABLA VENTAS  CUANDO HAY REGISTRO EN COMUN EN LA TABLA VENTA
                            limpiaCampos();
                                JOptionPane.showMessageDialog(this, "Cuenta por Cobrar saldada para el Titular: '"+titular+"'.", "Registro Cuentas por Cobrar", 0,
                                new ImageIcon(getClass().getResource("/imagenes/caja/borrado1.png")));
                        if (principal.MenuPrincipalAd.cerra) {
                            OpcionesDeudas.numeros();
                        }
                                    //borrar solo la fila seleccionada
                                    break;
                                case 0:
                                    if(OpcionesDeudas.existe_venta_id_descripcion(id, descripcion)){
                                        OpcionesDeudas.actualizar_sin_on_of(descripcion, id);
                                        OpcionesDeudas.eliminar_dos_deuda(id,descripcion);
                                    }else{
                                        OpcionesDeudas.registrar_ventas_desde_deudas_sin_id_pero_sola_fila_dosDatos(id,descripcion);
                                        OpcionesDeudas.eliminar_dos_deuda(id,descripcion);
                                    }
                                    OpcionesVen.actualizar_y_recalcular_total_registro_venta(id);// ACTUALIZA TOTAL EN TABLA VENTAS  CUANDO HAY REGISTRO EN COMUN EN LA TABLA VENTA
                            limpiaCampos();
                                JOptionPane.showMessageDialog(this, "Cuenta por Cobrar saldada para el Titular: '"+titular+"'.", "Registro Cuentas por Cobrar", 0,
                                new ImageIcon(getClass().getResource("/imagenes/caja/borrado1.png")));
                        if (principal.MenuPrincipalAd.cerra) {
                            OpcionesDeudas.numeros();
                        }
                                    break;
                                case 2:
                                    break;
                                default:
                                    break;
                            }
                           }else{
                                 if(OpcionesDeudas.existe_venta_id_descripcion(id, descripcion)){
                                        OpcionesDeudas.actualizar_sin_on_of(descripcion, id);
                                        OpcionesDeudas.eliminar_dos_deuda(id,descripcion);
                                        OpcionesVen.actualizar_y_recalcular_total_registro_venta(id);// ACTUALIZA TOTAL EN TABLA VENTAS  CUANDO HAY REGISTRO EN COMUN EN LA TABLA VENTA
                            limpiaCampos();
                                JOptionPane.showMessageDialog(this, "Cuenta por Cobrar saldada para el Titular: '"+titular+"'.", "Registro Cuentas por Cobrar", 0,
                                new ImageIcon(getClass().getResource("/imagenes/caja/borrado1.png")));
                        if (principal.MenuPrincipalAd.cerra) {
                            OpcionesDeudas.numeros();
                        }
                                    }else{
                                        OpcionesDeudas.registrar_ventas_desde_deudas_sin_id_pero_sola_fila_dosDatos(id,descripcion);
                                        OpcionesDeudas.eliminar_dos_deuda(id,descripcion);
                                        OpcionesVen.actualizar_y_recalcular_total_registro_venta(id);// ACTUALIZA TOTAL EN TABLA VENTAS  CUANDO HAY REGISTRO EN COMUN EN LA TABLA VENTA
                            limpiaCampos();
                                JOptionPane.showMessageDialog(this, "Cuenta por Cobrar saldada para el Titular: '"+titular+"'.", "Registro Cuentas por Cobrar", 0,
                                new ImageIcon(getClass().getResource("/imagenes/caja/borrado1.png")));
                        if (principal.MenuPrincipalAd.cerra) {
                            OpcionesDeudas.numeros();
                        }
                                    }
                             }
                             
                    }
//                    int elimina = OpcionesVen.eliminar_dos(id,descripcion);
//                    if (elimina != 0) {
//                        limpiaCampos();
//                        JOptionPane.showMessageDialog(this, "Registro eliminado.", "Registro Ventas", 0,
//                                new ImageIcon(getClass().getResource("/imagenes/caja/borrado1.png")));
//                        if (principal.MenuPrincipalAd.cerra) {
//                            OpcionesVen.numeros();
//                        }
//                        
//                    }
                    }else{
                        System.out.println("cantidad a editar:"+cantidad_edit.getText());
                            System.out.println("cantidad en la tabla: "+Integer.parseInt(tablaDeudas.getValueAt(fila2, 3).toString()));
                        if(Integer.parseInt(cantidad_edit.getText())<Integer.parseInt(tablaDeudas.getValueAt(fila2, 3).toString())){
                            
                           if (JOptionPane.showConfirmDialog(this, "Esta a punto de editar\nun registro.\n¿Desea continuar?", "Registro Ventas", JOptionPane.YES_NO_OPTION, 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                               System.out.println("descripcion: "+descripcion);
                                System.out.println("cantidad_producto_tablVenta: "+cantidad_producto_tablaVentas(id,descripcion));
                                if(OpcionesDeudas.existe_venta(id)){
                                    if(OpcionesDeudas.existe_venta_id_descripcion(id, descripcion)){
                       OpcionesDeudas.actualizar_con_on_of(descripcion, cantidad_edit.getText(), id, activo(check_restar_deuda)); // actualizar cantidad, ganancia e inversion en tabla ventas   CUANDO HAY REGISTRO EN COMUN EN LA TABLA VENTA
                       OpcionesVen.actualizar_y_recalcular_total_registro_venta(id);// ACTUALIZA TOTAL EN TABLA VENTAS  CUANDO HAY REGISTRO EN COMUN EN LA TABLA VENTA
                                }else{
                                        OpcionesDeudas.registrar_ventas_desde_deudas_sin_id_pero_sola_fila(id, descripcion, OpcionesDeudas.cantidad_registro_venta(id, descripcion, cantidad_edit.getText(), activo(check_restar_deuda)), OpcionesDeudas.total_tabla_registro_venta(id), OpcionesDeudas.inversion_registro_venta(id, descripcion, cantidad_edit.getText(), activo(check_restar_deuda)), OpcionesDeudas.ganancia_registro_VENTA(id, descripcion, cantidad_edit.getText(), activo(check_restar_deuda)));
                                OpcionesVen.actualizar_y_recalcular_total_registro_venta(id);
                                    }
                                }else{
                                    OpcionesDeudas.registrar_ventas_desde_deudas_sin_id_pero_sola_fila(id, descripcion, OpcionesDeudas.cantidad_registro_venta(id, descripcion, cantidad_edit.getText(), activo(check_restar_deuda)), OpcionesDeudas.total_tabla_registro_venta(id), OpcionesDeudas.inversion_registro_venta(id, descripcion, cantidad_edit.getText(), activo(check_restar_deuda)), OpcionesDeudas.ganancia_registro_VENTA(id, descripcion, cantidad_edit.getText(), activo(check_restar_deuda)));
                                OpcionesVen.actualizar_y_recalcular_total_registro_venta(id);
                                }
                                OpcionesDeudas.actualizar_cantidad_inversion_ganancia_registro_deuda(id,descripcion , cantidad_edit.getText(),activo(check_restar_deuda));// nombre lo indica
                       OpcionesDeudas.actualizar_y_recalcular_total_registro_deuda(id);  // el nombre del metodo lo dice
                       limpiaCampos();
                       
                    JOptionPane.showMessageDialog(this, "Registro actualizado.", "Registro Ventas", 0,
                                new ImageIcon(getClass().getResource("/imagenes/caja/borrado1.png")));
                        if (principal.MenuPrincipalAd.cerra) {
                            OpcionesDeudas.numeros();
                        }
                           }
                        }else{
                             
                            JOptionPane.showMessageDialog(this, "La cantidad a editar debe ser menor que la cantidad actual.\n"
                                    + "Si desea aumentar el número de Cuentas por Cobrar, por favor hágalo desde la 'CAJA DE COBRO'", "Registro Cuentas por Cobrar", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png")));
                        }
                    }
                
         //       }
           
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un registro.", "Registro Ventas", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
            }

        } else {
            JOptionPane.showMessageDialog(this, "No hay registros\npara eliminar.", "Registro Ventas", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        }
    }
        
    public boolean activo(JCheckBox checbox){
        boolean activo=false;
        if(checbox.isSelected()){
            activo=true;
        }
        return activo;
    }
    
    private void eliminarT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarT1ActionPerformed
        // TODO add your handling code here:
       if (tablaDeudas.getRowCount() > 0) {
            if (JOptionPane.showConfirmDialog(this, "Esta a punto de elimnar todos los registros.\nEstos productos no seran reintegrados al 'Almacen' ni al 'Registro de Ventas'\n¿Desea continuar?", "Registro Deudas", JOptionPane.YES_NO_OPTION, 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                int eliminaT = OpcionesDeudas.eliminaTodos();
                if (eliminaT != 0) {
                    limpiaCampos();
                    JOptionPane.showMessageDialog(this, "Registros eliminados.", "Registro Ventas", 0,
                            new ImageIcon(getClass().getResource("/imagenes/caja/borrado.png")));
                    if (principal.MenuPrincipalAd.cerra) {
                        OpcionesVen.numeros();
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No hay registros\npara eliminar.", "Registro Ventas", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        }
        
        ganancia_total();
    }//GEN-LAST:event_eliminarT1ActionPerformed

    private void limpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiar1ActionPerformed
        // TODO add your handling code here:
        if(!check_filtro.isSelected()){
        limpiaCampos();
        }else{
            combo_filtro.setSelectedIndex(0);
            limpiaCampos();
        }
    }//GEN-LAST:event_limpiar1ActionPerformed

    private void ventasH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasH1ActionPerformed
        // TODO add your handling code here:
        Runnable runnable1 = new Runnable() {
                    public void run() {
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        RegistroDeudas.this.setEnabled(false);
                        //inicio metodo
                        //code
                        //fin metodo
        Date sistemaFech = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecH = formato.format(sistemaFech);
        OpcionesDeudas.listar(fecH);
        fecha.setDate(null);
            montoTotal();
            ganancia_total();
            inversion_total();
        organizar_tabla();
        RegistroDeudas.this.setEnabled(true);
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    }
                };
                Thread t1 = new Thread(runnable1);
                t1.start();
    }//GEN-LAST:event_ventasH1ActionPerformed

    private void ventasH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasH2ActionPerformed
        // TODO add your handling code here:
        Runnable runnable1 = new Runnable() {
                    public void run() {
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        RegistroDeudas.this.setEnabled(false);
                        //inicio metodo
                        //code
                        //fin metodo
        OpcionesDeudas.listar("");
        fecha.setDate(null);
            montoTotal();
            ganancia_total();
            inversion_total();
         organizar_tabla();
         RegistroDeudas.this.setEnabled(true);
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    }
                };
                Thread t1 = new Thread(runnable1);
                t1.start();
    }//GEN-LAST:event_ventasH2ActionPerformed

    
   public void cambia_texto (String elTexto)
   {
      inversion_total.setText(elTexto);
   }
    
    private void check_filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_filtroActionPerformed
        // TODO add your handling code here:
        Runnable runnable1 = new Runnable() {
                    public void run() {
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        RegistroDeudas.this.setEnabled(false);
                        //inicio metodo
                        //code
                        //fin metodo
        if(check_filtro.isSelected()){
            limpiaCampos();
        combo_filtro.setVisible(true);
        on_off.setEnabled(false);
        panel_no_agrupado.setVisible(false);
        panel_agrupado.setVisible(true);
        ventasH1.setEnabled(false);
         ventasH2.setEnabled(false);
         eliminar1.setEnabled(false);
         eliminarT1.setEnabled(false);
         limpiar1.setEnabled(true);
         panel_inversion.setEnabled(false);
         fecha.setEnabled(false);
         buscF.setEnabled(false);
        OpcionesDeudas.listar_por_titulares(combo_filtro.getSelectedItem().toString());
        tablaDeudas1.getTableHeader().setDefaultRenderer(new principal.EstiloTablaHeader());
        tablaDeudas1.setDefaultRenderer(Object.class, new principal.EstiloTablaVentasAgrupado());
        ganancia_total_x_producto();
        montoTotal_x_producto();
        inversion_total_x_producto();
        }else{
            limpiaCampos();
            on_off.setEnabled(true);
            combo_filtro.setVisible(false);
            panel_no_agrupado.setVisible(true);
            panel_agrupado.setVisible(false);
            ventasH1.setEnabled(true);
         ventasH2.setEnabled(true);
         eliminar1.setEnabled(true);
         eliminarT1.setEnabled(true);
         limpiar1.setEnabled(true);
         panel_inversion.setEnabled(true);
         fecha.setEnabled(true);
         buscF.setEnabled(true);
        }
        RegistroDeudas.this.setEnabled(true);
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    }
                };
                Thread t1 = new Thread(runnable1);
                t1.start();
    }//GEN-LAST:event_check_filtroActionPerformed

    private void combo_filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_filtroActionPerformed
        // TODO add your handling code here:
        if(check_filtro.isSelected()){
        OpcionesDeudas.listar_por_titulares(combo_filtro.getSelectedItem().toString());
        montoTotal_x_producto();
        ganancia_total_x_producto();
        inversion_total_x_producto();
        }
    }//GEN-LAST:event_combo_filtroActionPerformed

    private void check_restar_deudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_restar_deudaActionPerformed
        // TODO add your handling code here:
        if(check_restar_deuda.isSelected()){
            check_restar_deuda.setForeground(Color.red);
            cantidad_edit.setText("");
            cantidad_edit.requestFocus();
        }else{
            check_restar_deuda.setForeground(new Color(255,153,153));
        }
    }//GEN-LAST:event_check_restar_deudaActionPerformed

    private void on_offActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_on_offActionPerformed
        // TODO add your handling code here:
        if(tablaDeudas.getSelectedRowCount() > 0){
        if(on_off.isSelected()){
            cantidad_edit.setEnabled(true);
            check_restar_deuda.setEnabled(true);
            check_restar_deuda.setSelected(false);
            check_restar_deuda.setForeground(new Color(255,153,153));
            cantidad_edit.setForeground(new Color(255,153,153));
            if(check_filtro.isSelected()){
                int fila = tablaDeudas1.getSelectedRow();
            cantidad_edit.setText(tablaDeudas1.getValueAt(fila, 2).toString());
            cantidad_edit.setForeground(Color.red);
            }else{
                int fila = tablaDeudas.getSelectedRow();
            cantidad_edit.setText(tablaDeudas.getValueAt(fila, 3).toString());
            cantidad_edit.setForeground(Color.red);
            }
        }else{
            cantidad_edit.setEnabled(false);
            check_restar_deuda.setSelected(false);
            check_restar_deuda.setEnabled(false);
            check_restar_deuda.setForeground(new Color(255,153,153));
            cantidad_edit.setText("CANTIDAD");
        }
        }else{
            JOptionPane.showMessageDialog(this, "Primero debe seleccionar un registro\n.", "Registro Cuentas por Cobrar", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
            on_off.setSelected(false);
        }
    }//GEN-LAST:event_on_offActionPerformed

    private void buscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar2ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_buscar2ActionPerformed

    private void buscar2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar2KeyReleased
        // TODO add your handling code here:
        buscar2.setText(buscar2.getText().toUpperCase());
        if(!check_filtro.isSelected()){
        OpcionesDeudas.listar_buscar(buscar2.getText());
        }else{
            OpcionesDeudas.listar_buscar_con_filtro(buscar2.getText());
        }
        ganancia_total();
            montoTotal();
            inversion_total();
        organizar_tabla();
        if(buscar2.getText().equals("")){
            limpiaCampos();
        }
        
    }//GEN-LAST:event_buscar2KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         Runnable runnable1 = new Runnable() {
                    public void run() {
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
                        RegistroDeudas.this.setEnabled(false);
                        //inicio metodo
                        if(!check_filtro.isSelected()){
                        crea_reporte_desagrupado();
                        }else{
                            crea_reporte_agrupado();
                        }
                        //fin metodo
                        RegistroDeudas.this.setEnabled(true);
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    }
                };
                Thread t1 = new Thread(runnable1);
                t1.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void crea_reporte_desagrupado(){
        try {
                            int fila=0;
                            String datos="";
                            String ruta_logo="imagenes/logo3.png";
                            List resultados=new ArrayList();
                            Campos_Tabla_Deudas tipo;
                            resultados.clear();
                            //recorrer la tabla
                            for(fila=0;fila<tablaDeudas.getRowCount();fila++){
                                tipo=new Campos_Tabla_Deudas(tablaDeudas.getValueAt(fila, 1).toString(), tablaDeudas.getValueAt(fila, 2).toString(), tablaDeudas.getValueAt(fila, 3).toString(), diseñador(tablaDeudas.getValueAt(fila, 4).toString()), tablaDeudas.getValueAt(fila, 7).toString());
                                resultados.add(tipo);
                                System.out.println(".run() "+tipo.getTitular());
                            }
                            Map map=new HashMap();
                            JasperPrint jprPrint;
                            JDialog reporte=new JDialog();
                            reporte.setSize(900,700);
                            reporte.setLocationRelativeTo(null);
                            reporte.setTitle("REGISTRO DEUDAS");
                            Image icon = new ImageIcon(getClass().getResource("/imagenes/deuda.png")).getImage();
                            reporte.setIconImage(icon);
                            
                            map.put("columna_monto_total",monto_total.getText());
                            map.put("columna_total_deudas", OpcionesAl.extraer_totales("SELECT sum(cantidad) FROM registro_deuda"));
                            map.put("columna_ganancia",ganancia_total.getText());
                            map.put("columna_inversion",inversion_total.getText());
                            map.put("logo",ruta_logo);
                            
                            jprPrint=JasperFillManager.fillReport(this.getClass().getClassLoader().getResourceAsStream("reportes/deudas_tabla.jasper"),
                                    map,new JRBeanCollectionDataSource(resultados));
                            JRViewer jv=new JRViewer(jprPrint);
                            reporte.getContentPane().add(jv);
                            reporte.setVisible(true);
                            
                            
                        } catch (JRException ex) {
                            Logger.getLogger(RegistroDeudas.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }

    public static String diseñador(String dato){
            String salida="";
            if(!dato.isEmpty()){
                salida="$ "+dato;
            }
            return salida;
        }
    
    public void crea_reporte_agrupado(){
        try {
                            int fila=0;
                            String datos="";
                            String ruta_logo="imagenes/logo3.png";
                            List resultados=new ArrayList();
                            Campo_tabla_deudas_agrupado tipo;
                            resultados.clear();
                            //recorrer la tabla
                            for(fila=0;fila<tablaDeudas1.getRowCount();fila++){
                                tipo=new Campo_tabla_deudas_agrupado(tablaDeudas1.getValueAt(fila, 0).toString(), diseñador(tablaDeudas1.getValueAt(fila, 1).toString()));
                                resultados.add(tipo);
                            }
                            Map map=new HashMap();
                            JasperPrint jprPrint;
                            JDialog reporte=new JDialog();
                            reporte.setSize(900,700);
                            reporte.setLocationRelativeTo(null);
                            reporte.setTitle("REGISTRO DEUDAS AGRUPADOS");
                            Image icon = new ImageIcon(getClass().getResource("/imagenes/deuda.png")).getImage();
                            reporte.setIconImage(icon);
                            
                            map.put("columna_monto_total",monto_total.getText());
                            map.put("columna_total_deudas", String.valueOf(tablaDeudas1.getRowCount()));
                            map.put("columna_ganancia",ganancia_total.getText());
                            map.put("columna_inversion",inversion_total.getText());
                            map.put("logo",ruta_logo);
                            
                            jprPrint=JasperFillManager.fillReport(this.getClass().getClassLoader().getResourceAsStream("reportes/deudas_tabla_agrupada.jasper"),
                                    map,new JRBeanCollectionDataSource(resultados));
                            JRViewer jv=new JRViewer(jprPrint);
                            reporte.getContentPane().add(jv);
                            reporte.setVisible(true);
                            
                            
                        } catch (JRException ex) {
                            Logger.getLogger(RegistroDeudas.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscF;
    private app.bolivia.swing.JCTextField buscar2;
    private javax.swing.JTextField cantidad_edit;
    private javax.swing.JCheckBox check_filtro;
    private javax.swing.JCheckBox check_restar_deuda;
    private javax.swing.JLabel codigoL2;
    private javax.swing.JComboBox<String> combo_filtro;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton eliminar1;
    private javax.swing.JButton eliminarT;
    private javax.swing.JButton eliminarT1;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JLabel ganancia_total;
    private javax.swing.JLabel inversion_total;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton limpiar;
    private javax.swing.JButton limpiar1;
    private javax.swing.JLabel monto_total;
    private javax.swing.JCheckBox on_off;
    private javax.swing.JPanel panel_agrupado;
    private javax.swing.JPanel panel_edit_cantidad;
    private javax.swing.JPanel panel_inversion;
    private javax.swing.JPanel panel_no_agrupado;
    public static javax.swing.JTable tablaDeudas;
    public static javax.swing.JTable tablaDeudas1;
    private javax.swing.JButton ventasH;
    private javax.swing.JButton ventasH1;
    private javax.swing.JButton ventasH2;
    // End of variables declaration//GEN-END:variables
}
