/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import deudas.RegistroDeudas;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.NumberAxis;
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
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import static principal.MenuPrincipalAd.escritorio;
import productos.OpcionesAl;
import reportes.Campo_tabla_ventas;
import reportes.Campo_tabla_ventas_agrupadas;
import javax.swing.JFrame;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;
import principal.conectar;

/**
 *
 * @author 4l3
 */
public class RegistroVentas extends javax.swing.JInternalFrame {

    /**
     * Creates new form Usuarios
     */
    public RegistroVentas() {
        initComponents();
        tablaVentas.getTableHeader().setDefaultRenderer(new principal.EstiloTablaHeader());
        tablaVentas.setDefaultRenderer(Object.class, new principal.EstiloTablaVentas());
        this.setFrameIcon(new ImageIcon(getClass().getResource("/imagenes/caja/icono1.png")));
        tablaVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaVentas1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        limpiaCampos();
        combo_filtro.setVisible(false);
        AutoCompleteDecorator.decorate(combo_filtro);
        ((JLabel) combo_filtro.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        OpcionesVen.get_combos_productos(combo_filtro);
        cantidad_edit.setEnabled(false);
        check_restar.setEnabled(false);

        tablaVentas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tablaVentas.getSelectedRow() != -1) {
                    // cambiaDatos();
                    int fila = tablaVentas.getSelectedRow();
                    if (on_off.isSelected()) {
                        cantidad_edit.setText(tablaVentas.getValueAt(fila, 2).toString());
                        cantidad_edit.setForeground(Color.red);
                    }
                    if (check_restar.isSelected()) {
                        check_restar.setForeground(Color.red);
                    } else {
                        check_restar.setForeground(new Color(255, 153, 153));
                    }
                    selecionRegistro = true;
                }
            }
        });

        tablaVentas1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tablaVentas1.getSelectedRow() != -1) {
                    // cambiaDatos();
                    int fila = tablaVentas1.getSelectedRow();
                    if (on_off.isSelected()) {
                        cantidad_edit.setText(tablaVentas1.getValueAt(fila, 1).toString());
                        cantidad_edit.setForeground(Color.red);
                    }
                    if (check_restar.isSelected()) {
                        check_restar.setForeground(Color.red);
                    } else {
                        check_restar.setForeground(new Color(255, 153, 153));
                    }
                    selecionRegistro = true;
                }
            }
        });
    }

    boolean selecionRegistro = false;

    void limpiaCampos() {
        if (tablaVentas.getSelectedRow() > -1) {
            tablaVentas.removeRowSelectionInterval(tablaVentas.getSelectedRow(), tablaVentas.getSelectedRow());
        }
        if (tablaVentas1.getSelectedRow() > -1) {
            tablaVentas1.removeRowSelectionInterval(tablaVentas1.getSelectedRow(), tablaVentas1.getSelectedRow());
        }
        fecha.setDate(null);
        buscar2.setText("");
        cantidad_edit.setText("CANTIDAD");
        cantidad_edit.setForeground(new Color(255, 153, 153));
        check_restar.setSelected(false);
        check_restar.setForeground(new Color(255, 153, 153));
        OpcionesVen.listar("");
        organizar_tabla();
        montoTotal();
        ganancia_total();
        inversion_total();
        on_off.setSelected(false);
        cantidad_edit.setEnabled(false);
        check_restar.setSelected(false);
        check_restar.setEnabled(false);
        check_restar.setForeground(new Color(255, 153, 153));
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
        tablaVentas = new javax.swing.JTable();
        panel_agrupado = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaVentas1 = new javax.swing.JTable();
        panel_inversion = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        inversion_total = new javax.swing.JLabel();
        panel_edit_cantidad = new javax.swing.JPanel();
        cantidad_edit = new javax.swing.JTextField();
        check_restar = new javax.swing.JCheckBox();
        on_off = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

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
        setTitle("REGISTRO VENTAS");

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
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BUSQUEDA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
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
        buscar2.setPlaceholder("CÓDIGO/PRODUCTO");
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
        jPanel2.add(buscar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 160, -1));

        codigoL2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        codigoL2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/buscarL.png"))); // NOI18N
        jPanel2.add(codigoL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 210, 52));

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
        ventasH1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/ventasH1.png"))); // NOI18N
        ventasH1.setToolTipText("Buscar");
        ventasH1.setBorder(null);
        ventasH1.setBorderPainted(false);
        ventasH1.setContentAreaFilled(false);
        ventasH1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ventasH1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ventasH1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/ventasH2.png"))); // NOI18N
        ventasH1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ventasH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasH1ActionPerformed(evt);
            }
        });
        jPanel6.add(ventasH1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, 50));

        ventasH2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ventasH2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/allVentas1.png"))); // NOI18N
        ventasH2.setToolTipText("Buscar");
        ventasH2.setBorder(null);
        ventasH2.setBorderPainted(false);
        ventasH2.setContentAreaFilled(false);
        ventasH2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ventasH2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ventasH2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/allVentas2.png"))); // NOI18N
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

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. VENTA", "PRODUCTO", "CANTIDAD", "TOTAL", "INVERSION", "GANANCIA", "FECHA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(tablaVentas);

        panel_no_agrupado.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 900, 280));

        jPanel1.add(panel_no_agrupado, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 260, -1, 310));

        panel_agrupado.setBackground(new java.awt.Color(255, 255, 255));
        panel_agrupado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaVentas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PRODUCTO", "CANTIDAD", "TOTAL", "INVERSION", "GANANCIA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaVentas1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane2.setViewportView(tablaVentas1);

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

        check_restar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        check_restar.setForeground(new java.awt.Color(255, 153, 153));
        check_restar.setText("Restar cantidad");
        check_restar.setBorder(null);
        check_restar.setOpaque(false);
        check_restar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_restarActionPerformed(evt);
            }
        });
        panel_edit_cantidad.add(check_restar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 26, -1, -1));

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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 6, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/grafic.png"))); // NOI18N
        jButton2.setToolTipText("Graficar Estadisticas de Ventas");
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/grafic2.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 6, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        limpiaCampos();
    }//GEN-LAST:event_limpiarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        if (tablaVentas.getRowCount() > 0) {
            if (tablaVentas.getSelectedRowCount() > 0) {
                if (JOptionPane.showConfirmDialog(this, "Esta a punto de eliminar\nun registro.\n¿Desea continuar?", "Registro Ventas", JOptionPane.YES_NO_OPTION, 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                    int fila = tablaVentas.getSelectedRow();
                    String id = tablaVentas.getValueAt(fila, 0).toString();
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
        if (tablaVentas.getRowCount() > 0) {
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
            OpcionesVen.listar("");

            ganancia_total();
        } else {
            String formato = fecha.getDateFormatString();
            Date date = fecha.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            OpcionesVen.listar(String.valueOf(sdf.format(date)));

            ganancia_total();
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

    public void montoTotal() {
        double total = 0;
        double ganancia = 0;
        /* Date sistemaFech = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecH = formato.format(sistemaFech);*/

        //inversion
        if (tablaVentas.getRowCount() < 1) {
            monto_total.setText(String.valueOf(total));
        } else {
            for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                total += Double.parseDouble(tablaVentas.getValueAt(i, 4).toString());
            }
            // GANANCIA
            for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                ganancia += Double.parseDouble(tablaVentas.getValueAt(i, 5).toString());
            }
            // monto.setText(String.valueOf(total+ganancia));
            monto_total.setText(String.valueOf(total + ganancia));
        }

    }

    public void inversion_total() {
        double inversion = 0;
        /* Date sistemaFech = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecH = formato.format(sistemaFech);*/
        if (tablaVentas.getRowCount() < 1) {
            inversion_total.setText(String.valueOf(inversion));
        } else {
            for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                inversion += Double.parseDouble(tablaVentas.getValueAt(i, 4).toString());
            }
            // inversion_total.setText(String.valueOf(inversion));
            inversion_total.setText(String.valueOf(inversion));
        }
    }

    public void ganancia_total() {
        double ganancia = 0;
        /* Date sistemaFech = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecH = formato.format(sistemaFech);*/
        if (tablaVentas.getRowCount() < 1) {
            ganancia_total.setText(String.valueOf(ganancia));
        } else {
            for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                ganancia += Double.parseDouble(tablaVentas.getValueAt(i, 5).toString());

            }
            //  ganancia_total.setText(String.valueOf(ganancia));
            ganancia_total.setText(String.valueOf(ganancia));
        }
    }

    public void ganancia_total_x_producto() {
        double ganancia = 0;
        /* Date sistemaFech = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecH = formato.format(sistemaFech);*/
        if (tablaVentas1.getRowCount() < 1) {
            ganancia_total.setText(String.valueOf(ganancia));
        } else {
            for (int i = 0; i < tablaVentas1.getRowCount(); i++) {
                ganancia += Double.parseDouble(tablaVentas1.getValueAt(i, 4).toString());

            }
            ganancia_total.setText(String.valueOf(ganancia));
        }
    }

    public void montoTotal_x_producto() {
        double total = 0;
        if (tablaVentas1.getRowCount() < 1) {
            monto_total.setText(String.valueOf(total));
        } else {
            for (int i = 0; i < tablaVentas1.getRowCount(); i++) {
                total += Double.parseDouble(tablaVentas1.getValueAt(i, 2).toString());
            }
        }
        monto_total.setText(String.valueOf(total));
    }

    public void inversion_total_x_producto() {
        double inversion = 0;
        /* Date sistemaFech = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecH = formato.format(sistemaFech);*/
        if (tablaVentas1.getRowCount() < 1) {
            inversion_total.setText(String.valueOf(inversion));
        } else {
            for (int i = 0; i < tablaVentas1.getRowCount(); i++) {
                inversion += Double.parseDouble(tablaVentas1.getValueAt(i, 3).toString());
                inversion_total.setText(String.valueOf(inversion));
            }
        }
    }

    public void organizar_tabla() {
        String numero = "";
        int fila = 0;
        //  tablaVentas.setDefaultRenderer(Object.class, new principal.EstiloTablaVentas());
        //  EstiloTablaVentas estilo=new EstiloTablaVentas();
        for (int i = 0; i < tablaVentas.getRowCount() - 1; i++) {
            numero = tablaVentas.getValueAt(fila, 0).toString();
            if (numero.equals(tablaVentas.getValueAt(i + 1, 0).toString())) {
                tablaVentas.setValueAt("", i + 1, 0);
                tablaVentas.setValueAt("", i + 1, 3);
                tablaVentas.setValueAt("", i + 1, 6);
            } else {
                fila = i + 1;
            }
        }

    }

    private void eliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar1ActionPerformed
        // TODO add your handling code here:
        elimina_desagrupado();

    }//GEN-LAST:event_eliminar1ActionPerformed

    public void elimina_desagrupado() {
        if (tablaVentas.getRowCount() > 0) {
            if (tablaVentas.getSelectedRowCount() > 0) {
                Runnable runnable1 = new Runnable() {
                    public void run() {
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        RegistroVentas.this.setEnabled(false);
                        //inicio metodo
                        int fila = tablaVentas.getSelectedRow();
                        int fila2 = tablaVentas.getSelectedRow();
                        String id = "";
                        String descripcion = tablaVentas.getValueAt(fila, 1).toString();
                        String cant = String.valueOf(Integer.parseInt(tablaVentas.getValueAt(fila, 2).toString()));

                        if (!tablaVentas.getValueAt(fila, 0).toString().equals("")) {
                            id = tablaVentas.getValueAt(fila, 0).toString();
                        } else {
                            while (tablaVentas.getValueAt(fila, 0).toString().equals("")) {
                                fila -= 1;
                            }
                            id = tablaVentas.getValueAt(fila, 0).toString();
                        }
                        // hasta aki muestra bien el id de venta
                        if (!on_off.isSelected()) {

                            if (JOptionPane.showConfirmDialog(RegistroVentas.this, "Esta a punto de eliminar\nun registro.\n¿Desea continuar?", "Registro Ventas", JOptionPane.YES_NO_OPTION, 0,
                                    new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                                OpcionesVen.actualizar(descripcion, cant);  // actualiza y recalcula en tabla alimentos los productos
                                try {
                                    OpcionesVen.eliminar_dos(id, descripcion);  // elimina fila de tabla ventas 
                                    OpcionesVen.actualizar_y_recalcular_registro_venta(id);  // el nombre del metodo lo dice
                                    limpiaCampos();
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(null, e);
                                }
                                JOptionPane.showMessageDialog(RegistroVentas.this, "Registro eliminado.", "Registro Ventas", 0,
                                        new ImageIcon(getClass().getResource("/imagenes/caja/borrado1.png")));
                                if (principal.MenuPrincipalAd.cerra) {
                                    OpcionesVen.numeros();
                                }
                            }
//                    
                        } else {
                            System.out.println("cantidad a editar:" + cantidad_edit.getText());
                            System.out.println("cantidad en la tabla: " + Integer.parseInt(tablaVentas.getValueAt(fila2, 2).toString()));
                            if (Integer.parseInt(cantidad_edit.getText()) < Integer.parseInt(tablaVentas.getValueAt(fila2, 2).toString())) {

                                if (JOptionPane.showConfirmDialog(RegistroVentas.this, "Esta a punto de editar\nun registro.\n¿Desea continuar?", "Registro Ventas", JOptionPane.YES_NO_OPTION, 0,
                                        new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {

                                    OpcionesVen.actualizar_con_on_of(descripcion, cantidad_edit.getText(), id, activo(check_restar)); // actualizar almacen 
                                    OpcionesVen.eliminar_tres(id, descripcion, cantidad_edit.getText(), activo(check_restar));
                                    OpcionesVen.actualizar_y_recalcular_registro_venta2(descripcion, cantidad_edit.getText(), id);  // el nombre del metodo lo dice
                                    OpcionesVen.actualizar_y_recalcular_total_registro_venta(id);
                                    limpiaCampos();

                                    JOptionPane.showMessageDialog(RegistroVentas.this, "Registro actualizado.", "Registro Ventas", 0,
                                            new ImageIcon(getClass().getResource("/imagenes/caja/borrado1.png")));
                                    if (principal.MenuPrincipalAd.cerra) {
                                        OpcionesVen.numeros();
                                    }
                                }
                            } else {

                                JOptionPane.showMessageDialog(RegistroVentas.this, "La cantidad a editar debe ser menor que la cantidad actual.\n"
                                        + "Si desea aumentar el número de ventas por favor hágalo desde la 'CAJA DE COBRO'", "Registro Ventas", 0,
                                        new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png")));
                            }
                        }

                        //       }
                        RegistroVentas.this.setEnabled(true);
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    }
                };
                Thread t1 = new Thread(runnable1);
                t1.start();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un registro.", "Registro Ventas", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
            }

        } else {
            JOptionPane.showMessageDialog(this, "No hay registros\npara eliminar.", "Registro Ventas", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        }
    }

    public boolean activo(JCheckBox checbox) {
        boolean activo = false;
        if (checbox.isSelected()) {
            activo = true;
        }
        return activo;
    }

    private void eliminarT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarT1ActionPerformed
        // TODO add your handling code here:
        if (tablaVentas.getRowCount() > 0) {
            if (JOptionPane.showConfirmDialog(this, "Esta a punto de elimnar todos los registros.\nEstos productos no seran reintegrados al almacen\n¿Desea continuar?", "Registro Ventas", JOptionPane.YES_NO_OPTION, 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                Runnable runnable1 = new Runnable() {
                    public void run() {
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        RegistroVentas.this.setEnabled(false);
                        //inicio metodo
                        //code
                        //fin metodo
                        int eliminaT = OpcionesVen.eliminaTodos();
                        if (eliminaT != 0) {
                            limpiaCampos();
                            JOptionPane.showMessageDialog(RegistroVentas.this, "Registros eliminados.", "Registro Ventas", 0,
                                    new ImageIcon(getClass().getResource("/imagenes/caja/borrado.png")));
                            if (principal.MenuPrincipalAd.cerra) {
                                OpcionesVen.numeros();
                            }
                        }
                        RegistroVentas.this.setEnabled(true);
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    }
                };
                Thread t1 = new Thread(runnable1);
                t1.start();
            }
        } else {
            JOptionPane.showMessageDialog(this, "No hay registros\npara eliminar.", "Registro Ventas", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        }

        ganancia_total();
    }//GEN-LAST:event_eliminarT1ActionPerformed

    private void limpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiar1ActionPerformed
        // TODO add your handling code here:
        if (!check_filtro.isSelected()) {
            limpiaCampos();
        } else {
            combo_filtro.setSelectedIndex(0);
            limpiaCampos();
        }
    }//GEN-LAST:event_limpiar1ActionPerformed

    private void ventasH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasH1ActionPerformed
        // TODO add your handling code here:
        Date sistemaFech = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecH = formato.format(sistemaFech);
        Runnable runnable1 = new Runnable() {
            public void run() {
                escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                RegistroVentas.this.setEnabled(false);
                //inicio metodo
                //code
                //fin metodo
                OpcionesVen.listar(fecH);
                fecha.setDate(null);

                montoTotal();
                ganancia_total();
                inversion_total();
                organizar_tabla();
                RegistroVentas.this.setEnabled(true);
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
                RegistroVentas.this.setEnabled(false);
                //inicio metodo
                //code
                //fin metodo
                OpcionesVen.listar("");
                fecha.setDate(null);
                montoTotal();
                ganancia_total();
                inversion_total();
                organizar_tabla();
                RegistroVentas.this.setEnabled(true);
                escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
        };
        Thread t1 = new Thread(runnable1);
        t1.start();
    }//GEN-LAST:event_ventasH2ActionPerformed

    public void cambia_texto(String elTexto) {
        inversion_total.setText(elTexto);
    }

    private void check_filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_filtroActionPerformed
        // TODO add your handling code here:

        if (check_filtro.isSelected()) {
            Runnable runnable1 = new Runnable() {
                public void run() {
                    escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
                    RegistroVentas.this.setEnabled(false);
                    //inicio metodo
                    //code
                    //fin metodo
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
                    fecha.setEnabled(false);
                    buscF.setEnabled(false);

                    OpcionesVen.listar_por_producto(combo_filtro.getSelectedItem().toString());
                    tablaVentas1.getTableHeader().setDefaultRenderer(new principal.EstiloTablaHeader());
                    tablaVentas1.setDefaultRenderer(Object.class, new principal.EstiloTablaVentasAgrupado());
                    ganancia_total_x_producto();
                    montoTotal_x_producto();
                    inversion_total_x_producto();
                    RegistroVentas.this.setEnabled(true);
                    escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                }
            };
            Thread t1 = new Thread(runnable1);
            t1.start();
        } else {
            Runnable runnable1 = new Runnable() {
                public void run() {
                    escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    RegistroVentas.this.setEnabled(false);
                    //inicio metodo
                    //code
                    //fin metodo
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
                    RegistroVentas.this.setEnabled(true);
                    escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                }
            };
            Thread t1 = new Thread(runnable1);
            t1.start();
        }
    }//GEN-LAST:event_check_filtroActionPerformed

    private void combo_filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_filtroActionPerformed
        // TODO add your handling code here:
        if (check_filtro.isSelected()) {
            OpcionesVen.listar_por_producto(combo_filtro.getSelectedItem().toString());
            montoTotal_x_producto();
            ganancia_total_x_producto();
            inversion_total_x_producto();
        }
    }//GEN-LAST:event_combo_filtroActionPerformed

    private void check_restarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_restarActionPerformed
        // TODO add your handling code here:
        if (check_restar.isSelected()) {
            check_restar.setForeground(Color.red);
            cantidad_edit.setText("");
            cantidad_edit.requestFocus();
        } else {
            check_restar.setForeground(new Color(255, 153, 153));
        }
    }//GEN-LAST:event_check_restarActionPerformed

    private void on_offActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_on_offActionPerformed
        // TODO add your handling code here:
        if (tablaVentas.getSelectedRowCount() > 0) {
            if (on_off.isSelected()) {
                cantidad_edit.setEnabled(true);
                check_restar.setEnabled(true);
                check_restar.setSelected(false);
                check_restar.setForeground(new Color(255, 153, 153));
                cantidad_edit.setForeground(new Color(255, 153, 153));
                if (check_filtro.isSelected()) {
                    int fila = tablaVentas1.getSelectedRow();
                    cantidad_edit.setText(tablaVentas1.getValueAt(fila, 2).toString());
                    cantidad_edit.setForeground(Color.red);
                } else {
                    int fila = tablaVentas.getSelectedRow();
                    cantidad_edit.setText(tablaVentas.getValueAt(fila, 2).toString());
                    cantidad_edit.setForeground(Color.red);
                }
            } else {
                cantidad_edit.setEnabled(false);
                check_restar.setSelected(false);
                check_restar.setEnabled(false);
                check_restar.setForeground(new Color(255, 153, 153));
                cantidad_edit.setText("CANTIDAD");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Primero debe seleccionar un registro\n.", "Registro Ventas", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
            on_off.setSelected(false);
        }
    }//GEN-LAST:event_on_offActionPerformed

    private void buscar2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar2KeyReleased
        // TODO add your handling code here:
        buscar2.setText(buscar2.getText().toUpperCase());
        if (!check_filtro.isSelected()) {
            OpcionesVen.listar_buscar(buscar2.getText());
            for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                tablaVentas.setValueAt(String.valueOf(Double.parseDouble(tablaVentas.getValueAt(i, 4).toString()) + Double.parseDouble(tablaVentas.getValueAt(i, 5).toString())), i, 3);
            }
            ganancia_total();
            montoTotal();
            inversion_total();
            organizar_tabla();
        } else {
            OpcionesVen.listar_buscar_con_filtro(buscar2.getText());
            ganancia_total_x_producto();
            montoTotal_x_producto();
            inversion_total_x_producto();
            organizar_tabla();
        }
        if (buscar2.getText().equals("")) {
            limpiaCampos();
        }
    }//GEN-LAST:event_buscar2KeyReleased

    private void buscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar2ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_buscar2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Runnable runnable1 = new Runnable() {
            public void run() {
                escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
                RegistroVentas.this.setEnabled(false);
                //inicio metodo
                if (!check_filtro.isSelected()) {
                    crea_reporte_desagrupado();
                } else {
                    crea_reporte_agrupado();
                }
                //fin metodo
                RegistroVentas.this.setEnabled(true);
                escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
        };
        Thread t1 = new Thread(runnable1);
        t1.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private TimeSeries createSeries(String name, int scala) {
        TimeSeries series = new TimeSeries(name);

        series.add(new Year(2020), scala);

        return series;
    }

    private XYDataset createDataSet() {
        TimeSeriesCollection tsc = new TimeSeriesCollection();
        for (int i = 0; i < (OpcionesVen.all_productos().size()); i++) {
            tsc.addSeries(createSeries(OpcionesVen.all_productos().get(i).toString(), OpcionesVen.get_ventas(OpcionesVen.all_productos().get(i).toString())));
        }
        return tsc;
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JFreeChart grafico = null;
        JFrame f = new JFrame("BarChart");
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        DefaultCategoryDataset datos2 = new DefaultCategoryDataset();
        //obtener datos y añadir datos
        for (int i = 0; i < OpcionesVen.all_productos().size(); i++) {
            datos.addValue(OpcionesVen.get_ventas(OpcionesVen.all_productos().get(i).toString()), "", OpcionesVen.all_productos().get(i).toString());
        }
        //datos grafico lineal

        //decidir que tipo de grafico mostrar
        String[] opciones = {"Grafica de Barra", "Grafica de Linea", "Grafica de Pastel", "Cancelar"};
        int opcion = JOptionPane.showOptionDialog(f, "¿Qué grafica desea crear?", "Control",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png")),
                opciones, 3);

        switch (opcion) {
            case 0://barra

                grafico = ChartFactory.createBarChart("Estadística de Ventas", "PRODUCTOS", "CANTIDAD", datos, PlotOrientation.HORIZONTAL, false, true, false);
                BarRenderer renderer = (BarRenderer) grafico.getCategoryPlot().getRenderer();
//                renderer.setItemMargin(-2);
                renderer.setSeriesPaint(0, Color.blue);
                grafico.setBackgroundPaint(new java.awt.Color(204, 204, 255));
                CategoryPlot plot_barra = (CategoryPlot) grafico.getPlot();
                //borde del grafico en azul
                plot_barra.setOutlinePaint(Color.BLUE);
                plot_barra.setOutlineStroke(new BasicStroke(2.0f));
                //fondo del grafico en blanco
                plot_barra.setBackgroundPaint(Color.WHITE);
                //visible las rayas horizontal de guia y color de la misma
                plot_barra.setRangeGridlinesVisible(true);
                plot_barra.setRangeGridlinePaint(new java.awt.Color(192, 192, 192));
                ChartPanel cPanel_barra = new ChartPanel(grafico);
        f.getContentPane().add(cPanel_barra);
        f.pack();
        f.setSize(900, 600);
        f.setVisible(true);
                break;
            case 1://lineal
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                for (int tiempo = 0; tiempo < OpcionesVen.all_productos().size(); tiempo++) {
                    dataset.addValue(OpcionesVen.get_ventas(OpcionesVen.all_productos().get(tiempo).toString()), "", OpcionesVen.all_productos().get(tiempo).toString());
                }

                grafico = ChartFactory.createLineChart(
                        "Estadistíca de Ventas",
                        "Productos",
                        "Ventas",
                        dataset,
                        PlotOrientation.HORIZONTAL,
                        false,
                        true,
                        false
                );
                grafico.setBackgroundPaint(new java.awt.Color(204, 204, 255));
                grafico.setBorderVisible(true);
                CategoryPlot plot_linea = (CategoryPlot) grafico.getPlot();
                //borde del grafico en azul
                plot_linea.setOutlinePaint(Color.BLUE);
                plot_linea.setOutlineStroke(new BasicStroke(2.0f));
                //fondo del grafico en blanco
                plot_linea.setBackgroundPaint(Color.WHITE);
                //visible las rayas horizontal de guia y color de la misma
                plot_linea.setRangeGridlinesVisible(true);
                plot_linea.setRangeGridlinePaint(new java.awt.Color(192, 192, 192));
                //visible las rayas vertical de guia y color de la misma
                plot_linea.setDomainGridlinesVisible(true);
                plot_linea.setDomainGridlinePaint(new java.awt.Color(192, 192, 192));

                LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) plot_linea.getRenderer();
                lineandshaperenderer.setBaseShapesVisible(true);
// line seris colors in line chart
                lineandshaperenderer.setSeriesPaint(0, Color.BLUE);
//        lineandshaperenderer.setSeriesPaint(1, Color.BLUE);
//        lineandshaperenderer.setSeriesPaint(2, Color.MAGENTA);
//        lineandshaperenderer.setSeriesPaint(3, Color.YELLOW);
//        lineandshaperenderer.setSeriesPaint(4, Color.PINK);

                //numero encima de los putos
                lineandshaperenderer.setItemLabelsVisible(false);
                lineandshaperenderer.setBaseStroke(new BasicStroke(3.5f));

// series font style
                lineandshaperenderer.setSeriesItemLabelFont(0, new Font("TimesRoman", Font.BOLD, 11));
//        lineandshaperenderer.setSeriesItemLabelFont(1, new Font("TimesRoman", Font.BOLD, 11));
//        lineandshaperenderer.setSeriesItemLabelFont(2, new Font("TimesRoman", Font.BOLD, 12));
//        lineandshaperenderer.setSeriesItemLabelFont(3, new Font("TimesRoman", Font.BOLD, 11));
//        lineandshaperenderer.setSeriesItemLabelFont(4, new Font("TimesRoman", Font.BOLD, 11));
// values colors in line chart
                lineandshaperenderer.setSeriesItemLabelPaint(0, Color.BLACK);
//        lineandshaperenderer.setSeriesItemLabelPaint(1, Color.WHITE);
//        lineandshaperenderer.setSeriesItemLabelPaint(2, Color.BLACK);
//        lineandshaperenderer.setSeriesItemLabelPaint(3, Color.YELLOW);
//        lineandshaperenderer.setSeriesItemLabelPaint(4, Color.RED);

                DecimalFormat decimalformat1 = new DecimalFormat("##");
                lineandshaperenderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
                lineandshaperenderer.setSeriesPositiveItemLabelPosition(1, new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
ChartPanel cPanel_linea = new ChartPanel(grafico);
        f.getContentPane().add(cPanel_linea);
        f.pack();
        f.setSize(900, 600);
        f.setVisible(true);
                break;
            case 2://pastel
                DefaultPieDataset datosPie = new DefaultPieDataset();
                for (int i = 0; i < OpcionesVen.get_all_productos_vendidos().size(); i++) {
                    datosPie.setValue(OpcionesVen.all_productos().get(i).toString(), OpcionesVen.get_ventas(OpcionesVen.all_productos().get(i).toString()));
                }
//            datosPie.setValue("Uno", dato1);
//            datosPie.setValue("Dos", dato2);
//            datosPie.setValue("Tres", dato3);
//            datosPie.setValue("Cuatro", dato4);        
                grafico = ChartFactory.createPieChart("Estadistíca de Ventas", datosPie, true, true, false);
                grafico.setBackgroundPaint(new java.awt.Color(204, 204, 255));
                grafico.getLegend().setPosition(RectangleEdge.RIGHT);
                Plot plot_pie = grafico.getPlot();
                //borde del grafico en azul
                plot_pie.setOutlinePaint(Color.BLUE);
                plot_pie.setOutlineStroke(new BasicStroke(2.0f));
                //fondo del grafico en blanco
                plot_pie.setBackgroundPaint(Color.WHITE);
                ChartPanel cPanel_pie = new ChartPanel(grafico);
        f.getContentPane().add(cPanel_pie);
        f.pack();
        f.setSize(900, 600);
        f.setVisible(true);
                break;
            case 3:
                f.dispose();
                break;
        }
        

    }//GEN-LAST:event_jButton2ActionPerformed

    public void crea_reporte_desagrupado() {
        try {
            conectar cc = new conectar();
            int fila = 0;
            String datos = "";
            String ruta_logo = "imagenes/logo3.png";
            List resultados = new ArrayList();
            Campo_tabla_ventas tipo;
            resultados.clear();
            //recorrer la tabla
            for (fila = 0; fila < tablaVentas.getRowCount(); fila++) {
                tipo = new Campo_tabla_ventas(tablaVentas.getValueAt(fila, 1).toString(), tablaVentas.getValueAt(fila, 2).toString(), RegistroDeudas.diseñador(tablaVentas.getValueAt(fila, 3).toString()), tablaVentas.getValueAt(fila, 6).toString());
                resultados.add(tipo);
            }
            Map map = new HashMap();
            JasperPrint jprPrint = null;
            JDialog reporte = new JDialog();
            reporte.setSize(900, 700);
            reporte.setLocationRelativeTo(null);
            reporte.setTitle("REGISTRO VENTAS");
            Image icon = new ImageIcon(getClass().getResource("/imagenes/caja/icono1.png")).getImage();
            reporte.setIconImage(icon);

            map.put("columna_monto_total", monto_total.getText());
            map.put("columna_total_ventas", OpcionesAl.extraer_totales("SELECT sum(cantidad) FROM registro_venta"));
            map.put("columna_inversion", inversion_total.getText());
            map.put("columna_ganancia", ganancia_total.getText());
            map.put("logo", ruta_logo);

//             String[] opciones = {"Grafica de Barra", "Grafica de Linea", "Grafica de Pastel","Ninguna","Todas", "Cancelar"};
//        int opcion = JOptionPane.showOptionDialog(this, "¿Qué grafica desea incluir?", "Control",
//                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png")),
//                opciones, 3);
//        switch(opcion){
//            case 0:
//                jprPrint = JasperFillManager.fillReport(this.getClass().getClassLoader().getResourceAsStream("reportes/ventas_tabla_barra.jasper"),
//                    map, new JRBeanCollectionDataSource(resultados));
//                    JRViewer jv_barra = new JRViewer(jprPrint);
//        reporte.getContentPane().add(jv_barra);
//                    reporte.setVisible(true);
//                break;
//            case 1:
//                jprPrint = JasperFillManager.fillReport(this.getClass().getClassLoader().getResourceAsStream("reportes/ventas_tabla_linea.jasper"),
//                    map, new JRBeanCollectionDataSource(resultados));
//                JRViewer jv_linea = new JRViewer(jprPrint);
//        reporte.getContentPane().add(jv_linea);
//                    reporte.setVisible(true);
//                break;
//            case 2:
//                jprPrint = JasperFillManager.fillReport(this.getClass().getClassLoader().getResourceAsStream("reportes/ventas_tabla_pastel.jasper"),
//                    map, new JRBeanCollectionDataSource(resultados));
//                JRViewer jv_pastel = new JRViewer(jprPrint);
//        reporte.getContentPane().add(jv_pastel);
//                    reporte.setVisible(true);
//                break;
//            case 3:
//                jprPrint = JasperFillManager.fillReport(this.getClass().getClassLoader().getResourceAsStream("reportes/ventas_tabla_sin_grafica.jasper"),
//                    map, new JRBeanCollectionDataSource(resultados));
//                JRViewer jv_ninguna = new JRViewer(jprPrint);
//        reporte.getContentPane().add(jv_ninguna);
//                    reporte.setVisible(true);
//                break;
//            case 4:
//                jprPrint = JasperFillManager.fillReport(this.getClass().getClassLoader().getResourceAsStream("reportes/ventas_tabla_all_graficas.jasper"),
//                    map, new JRBeanCollectionDataSource(resultados));
//                JRViewer jv_todas = new JRViewer(jprPrint);
//        reporte.getContentPane().add(jv_todas);
//                    reporte.setVisible(true);
//                break;
//            case 5:
//                reporte.dispose();
//                break;
            jprPrint = JasperFillManager.fillReport(this.getClass().getClassLoader().getResourceAsStream("reportes/ventas_tabla_sin_grafica.jasper"),
                    map, new JRBeanCollectionDataSource(resultados));
                JRViewer jv_ninguna = new JRViewer(jprPrint);
        reporte.getContentPane().add(jv_ninguna);
                    reporte.setVisible(true);
        
        } catch (JRException ex) {
            Logger.getLogger(RegistroDeudas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crea_reporte_agrupado() {
        try {
            int fila = 0;
            String datos = "";
            String ruta_logo = "imagenes/logo3.png";
            List resultados = new ArrayList();
            Campo_tabla_ventas_agrupadas tipo;
            resultados.clear();
//                            recorrer la tabla
            for (fila = 0; fila < tablaVentas1.getRowCount(); fila++) {
                tipo = new Campo_tabla_ventas_agrupadas(tablaVentas1.getValueAt(fila, 0).toString(), tablaVentas1.getValueAt(fila, 1).toString(), tablaVentas1.getValueAt(fila, 2).toString());
                resultados.add(tipo);
            }
            Map map = new HashMap();
            JasperPrint jprPrint;
            JDialog reporte = new JDialog();
            reporte.setSize(900, 700);
            reporte.setLocationRelativeTo(null);
            reporte.setTitle("REGISTRO VENTAS AGRUPADAS");
            Image icon = new ImageIcon(getClass().getResource("/imagenes/caja/icono1.png")).getImage();
            reporte.setIconImage(icon);

            map.put("columna_monto_total", monto_total.getText());
            map.put("columna_total_ventas", OpcionesAl.extraer_totales("select sum(cantidad) from registro_venta"));
            map.put("columna_ganancia", ganancia_total.getText());
            map.put("columna_inversion", inversion_total.getText());
            map.put("logo", ruta_logo);

            jprPrint = JasperFillManager.fillReport(this.getClass().getClassLoader().getResourceAsStream("reportes/ventas_tabla_agrupadas.jasper"),
                    map, new JRBeanCollectionDataSource(resultados));
            JRViewer jv = new JRViewer(jprPrint);
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
    private javax.swing.JCheckBox check_restar;
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
    private javax.swing.JButton jButton2;
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
    public static javax.swing.JTable tablaVentas;
    public static javax.swing.JTable tablaVentas1;
    private javax.swing.JButton ventasH;
    private javax.swing.JButton ventasH1;
    private javax.swing.JButton ventasH2;
    // End of variables declaration//GEN-END:variables
}
