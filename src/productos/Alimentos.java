/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import static principal.MenuPrincipalAd.escritorio;
import principal.conectar;

/**
 *
 * @author 4L3
 */
public class Alimentos extends javax.swing.JInternalFrame {

    /**
     * Creates new form Usuarios
     */
    int cantAlmacen=0;
    String tempCant="";
    int[] indicesfilasSeleccionadas={};
    ArrayList al = new ArrayList();
    public Alimentos() {
        initComponents();
        tablaAlimentos.getTableHeader().setDefaultRenderer(new principal.EstiloTablaHeader());
        tablaAlimentos.setDefaultRenderer(Object.class, new principal.EstiloTablaRenderer());
        this.tipoAl.setCursor(new Cursor(12));
        this.setFrameIcon(new ImageIcon(getClass().getResource("/imagenes/inventario.png")));
        limpiaCampos();
        jpanel_eliminar_x_tipo.setEnabled(false);
        lista_eliminar_tipo.setEnabled(false);
        foto_tipo_eliminar_tipo.setEnabled(false);
        foto_off_lista_eliminar.setVisible(true);
        check_suma.setVisible(false);
        tablaAlimentos.setRowSelectionAllowed(true);
        tablaAlimentos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        tipoAl.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent ie) {
                 if (tipoAl.getSelectedIndex() == 0) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/productos.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/productos1.png")));
                    cantidadL.setIcon(new ImageIcon(getClass().getResource("/imagenes/productos1.png")));
                }
                if (tipoAl.getSelectedIndex() == 1) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/ropa.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/ropa1.png")));
                    cantidadL.setIcon(new ImageIcon(getClass().getResource("/imagenes/ropa1.png")));
                }
                if (tipoAl.getSelectedIndex() == 2) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/calzado.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/calzado1.png")));
                    cantidadL.setIcon(new ImageIcon(getClass().getResource("/imagenes/calzado1.png")));
                }
                if (tipoAl.getSelectedIndex() == 3) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/cosmetico.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/cosmetico1.png")));
                    cantidadL.setIcon(new ImageIcon(getClass().getResource("/imagenes/cosmetico1.png")));
                }
                if (tipoAl.getSelectedIndex() == 4) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/ropa interior.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/ropa interior1.png")));
                    cantidadL.setIcon(new ImageIcon(getClass().getResource("/imagenes/ropa interior1.png")));
                }
                if (tipoAl.getSelectedIndex() == 5) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/ceramica.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/ceramica1.png")));
                    cantidadL.setIcon(new ImageIcon(getClass().getResource("/imagenes/ceramica1.png")));
                }
                if (tipoAl.getSelectedIndex() == 6) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/ACCESORIOS.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/ACCESORIOS1.png")));
                    cantidadL.setIcon(new ImageIcon(getClass().getResource("/imagenes/ACCESORIOS1.png")));
                }
                if (tipoAl.getSelectedIndex() == 7) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/JOYAS.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/JOYAS1.png")));
                    cantidadL.setIcon(new ImageIcon(getClass().getResource("/imagenes/JOYAS1.png")));
                }
                if (tipoAl.getSelectedIndex() == 8) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/ASEO.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/ASEO1.png")));
                    cantidadL.setIcon(new ImageIcon(getClass().getResource("/imagenes/ASEO1.png")));
                }
                if (tipoAl.getSelectedIndex() == 9) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/UTILES HOGAR.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/UTILES HOGAR1.png")));
                    cantidadL.setIcon(new ImageIcon(getClass().getResource("/imagenes/UTILES HOGAR1.png")));
                }
                if (tipoAl.getSelectedIndex() == 10) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/UTILES ESCOLARES.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/UTILES ESCOLARES1.png")));
                    cantidadL.setIcon(new ImageIcon(getClass().getResource("/imagenes/UTILES ESCOLARES1.png")));
                }
                if (tipoAl.getSelectedIndex() == 11) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/alimentos/otros.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/alimentos/otros1.png")));
                    cantidadL.setIcon(new ImageIcon(getClass().getResource("/imagenes/alimentos/otros1.png")));
                }
            }
        });

        lista_eliminar_tipo.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent ie) {
                 if (lista_eliminar_tipo.getSelectedIndex() == 0) {
                     OpcionesAl.listar_por_tipo(lista_eliminar_tipo.getSelectedItem().toString());
                }
                if (lista_eliminar_tipo.getSelectedIndex() == 1) {
                     OpcionesAl.listar_por_tipo(lista_eliminar_tipo.getSelectedItem().toString());
                }
                if (lista_eliminar_tipo.getSelectedIndex() == 2) {
                     OpcionesAl.listar_por_tipo(lista_eliminar_tipo.getSelectedItem().toString());
                }
                if (lista_eliminar_tipo.getSelectedIndex() == 3) {
                     OpcionesAl.listar_por_tipo(lista_eliminar_tipo.getSelectedItem().toString());
                }
                if (lista_eliminar_tipo.getSelectedIndex() == 4) {
                     OpcionesAl.listar_por_tipo(lista_eliminar_tipo.getSelectedItem().toString());
                }
                if (lista_eliminar_tipo.getSelectedIndex() == 5) {
                     OpcionesAl.listar_por_tipo(lista_eliminar_tipo.getSelectedItem().toString());
                }
                if (lista_eliminar_tipo.getSelectedIndex() == 6) {
                     OpcionesAl.listar_por_tipo(lista_eliminar_tipo.getSelectedItem().toString());
                }
                if (lista_eliminar_tipo.getSelectedIndex() == 7) {
                     OpcionesAl.listar_por_tipo(lista_eliminar_tipo.getSelectedItem().toString());
                }
                if (lista_eliminar_tipo.getSelectedIndex() == 8) {
                     OpcionesAl.listar_por_tipo(lista_eliminar_tipo.getSelectedItem().toString());
                }
                if (lista_eliminar_tipo.getSelectedIndex() == 9) {
                     OpcionesAl.listar_por_tipo(lista_eliminar_tipo.getSelectedItem().toString());
                }
                if (lista_eliminar_tipo.getSelectedIndex() == 10) {
                     OpcionesAl.listar_por_tipo(lista_eliminar_tipo.getSelectedItem().toString());
                }
                if (lista_eliminar_tipo.getSelectedIndex() == 11) {
                     OpcionesAl.listar_por_tipo(lista_eliminar_tipo.getSelectedItem().toString());
                }
            }
        });
        
        tablaAlimentos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                al.clear();
                int cuentaFilasSeleccionadas = tablaAlimentos.getSelectedRowCount(); 
                if (cuentaFilasSeleccionadas ==1 &&tablaAlimentos.getSelectedRow()!=-1) {
                    cambiaDatos();
                    selecionRegistro = true;
                    cuentaFilasSeleccionadas=0;
                }else if(cuentaFilasSeleccionadas >1 ){
                    //Hay varias filas seleccionadas 
                     indicesfilasSeleccionadas = tablaAlimentos.getSelectedRows();
                    System.out.println("Filas seleccionadas: ");
                    
                    for (int indice : indicesfilasSeleccionadas) {
                        al.add(indice);
                        System.out.print(indice + " CODIGO HOY");
                    }
                    System.out.println();
                    System.out.println("CODIGO HOY arreglo "+al);
                cuentaFilasSeleccionadas=0;
            }
        }
        });
       // resizeColumnWidth(tablaAlimentos);
    }

    //###################### auto ajustar ancho de columnas ####################
    public static void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
    //##########################################################################

    void cambiaDatos() {
        int fila = tablaAlimentos.getSelectedRow();
        codigo.setText(tablaAlimentos.getValueAt(fila, 0).toString());
        tipoAl.setSelectedItem(tablaAlimentos.getValueAt(fila, 1).toString());
        nombre.setText(tablaAlimentos.getValueAt(fila, 2).toString());
        precio_venta.setText(tablaAlimentos.getValueAt(fila, 5).toString());
        if (tablaAlimentos.getValueAt(fila, 3) != null) {
            cantidad.setText(tablaAlimentos.getValueAt(fila, 3).toString());
            tempCant=tablaAlimentos.getValueAt(fila, 3).toString();
            check_suma.setVisible(true);
                        cantAlmacen=Integer.parseInt(cantidad.getText());
        } else {
            check_suma.setVisible(false);
            cantidad.setText("");
        }
        precio_compra.setText(tablaAlimentos.getValueAt(fila, 4).toString());
        OpcionesAl opc=new OpcionesAl();
        stock_minimo.setText(opc.extraer_stock("SELECT `stock_minimo` FROM `alimentos` WHERE `codigo_al`='"+tablaAlimentos.getValueAt(fila, 0).toString()+"'"));
        stock_maximo.setText(opc.extraer_stock("SELECT `stock_maximo` FROM `alimentos` WHERE `codigo_al`='"+tablaAlimentos.getValueAt(fila, 0).toString()+"'"));
    }
    boolean selecionRegistro = false;

    void limpiaCampos() {
        if (tablaAlimentos.getSelectedRow() > -1) {
            tablaAlimentos.removeRowSelectionInterval(tablaAlimentos.getSelectedRow(), tablaAlimentos.getSelectedRow());
        }
        check_suma.setVisible(false);
        codigo.setText("");
        nombre.setText("");
        cantidad.setText("");
        tipoAl.setSelectedItem("TIPO PRODUCTO");
        precio_compra.setText("");
        precio_venta.setText("");
        buscar.setText("");
        selecionRegistro = false;
        OpcionesAl.listar("");
        OpcionesAl.extraerID();
        on_off.setSelected(false);
        lista_eliminar_tipo.setVisible(false);
        foto_tipo_eliminar_tipo.setEnabled(false);
        foto_off_lista_eliminar.setVisible(true);
        jpanel_eliminar_x_tipo.setEnabled(false);
        lista_eliminar_tipo.getModel().setSelectedItem("TIPO PRODUCTO");
        stock_minimo.setText("");
        stock_maximo.setText("");
    }

    void selecionaFila(String id) {
        for (int i = 0; i < tablaAlimentos.getRowCount(); i++) {
            if (id.equals(tablaAlimentos.getValueAt(i, 0))) {
                tablaAlimentos.setRowSelectionInterval(i, i);
                break;
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        on_off = new javax.swing.JCheckBox();
        codigo = new app.bolivia.swing.JCTextField();
        codigoL = new javax.swing.JLabel();
        nombre = new app.bolivia.swing.JCTextField();
        nombreL = new javax.swing.JLabel();
        precio_compra = new app.bolivia.swing.JCTextField();
        precio_compraL = new javax.swing.JLabel();
        cantidad = new app.bolivia.swing.JCTextField();
        cantidadL = new javax.swing.JLabel();
        check_suma = new javax.swing.JCheckBox();
        precio_venta = new app.bolivia.swing.JCTextField();
        precio_ventaL = new javax.swing.JLabel();
        jpanel_eliminar_x_tipo = new javax.swing.JPanel();
        foto_off_lista_eliminar = new javax.swing.JLabel();
        lista_eliminar_tipo = new org.bolivia.combo.SComboBoxBlue();
        foto_tipo_eliminar_tipo = new javax.swing.JLabel();
        tipoAl = new org.bolivia.combo.SComboBoxBlue();
        tipoL = new javax.swing.JLabel();
        PANEL_STOCK = new javax.swing.JPanel();
        stock_minimo = new app.bolivia.swing.JCTextField();
        stock_maximo = new app.bolivia.swing.JCTextField();
        cantidadL2 = new javax.swing.JLabel();
        cantidadL3 = new javax.swing.JLabel();
        cantidadL1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlimentos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        registrar = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        eliminarT = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        buscar = new app.bolivia.swing.JCTextField();
        codigoL1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Productos");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "REGISTRO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel2.add(on_off, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 50, 50));

        codigo.setEditable(false);
        codigo.setBackground(new java.awt.Color(34, 102, 145));
        codigo.setBorder(null);
        codigo.setForeground(new java.awt.Color(255, 255, 255));
        codigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        codigo.setOpaque(false);
        codigo.setPhColor(new java.awt.Color(255, 255, 255));
        codigo.setPlaceholder("CÓDIGO");
        jPanel2.add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 180, 20));

        codigoL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/alimentos/codigoL.png"))); // NOI18N
        jPanel2.add(codigoL, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 40));

        nombre.setBackground(new java.awt.Color(34, 102, 145));
        nombre.setBorder(null);
        nombre.setForeground(new java.awt.Color(255, 255, 255));
        nombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nombre.setOpaque(false);
        nombre.setPhColor(new java.awt.Color(255, 255, 255));
        nombre.setPlaceholder("NOMBRE PRODUCTO");
        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreKeyTyped(evt);
            }
        });
        jPanel2.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 180, 30));

        nombreL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/productos1.png"))); // NOI18N
        jPanel2.add(nombreL, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, 50));

        precio_compra.setBackground(new java.awt.Color(34, 102, 145));
        precio_compra.setBorder(null);
        precio_compra.setForeground(new java.awt.Color(255, 255, 255));
        precio_compra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        precio_compra.setOpaque(false);
        precio_compra.setPhColor(new java.awt.Color(255, 255, 255));
        precio_compra.setPlaceholder("PRECIO COMPRA");
        precio_compra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                precio_compraKeyTyped(evt);
            }
        });
        jPanel2.add(precio_compra, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 180, 30));

        precio_compraL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/alimentos/precioL.png"))); // NOI18N
        jPanel2.add(precio_compraL, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, -1, 50));

        cantidad.setBackground(new java.awt.Color(34, 102, 145));
        cantidad.setBorder(null);
        cantidad.setForeground(new java.awt.Color(255, 255, 255));
        cantidad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cantidad.setOpaque(false);
        cantidad.setPhColor(new java.awt.Color(255, 255, 255));
        cantidad.setPlaceholder("CANTIDAD ");
        cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantidadKeyTyped(evt);
            }
        });
        jPanel2.add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 120, 30));

        cantidadL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cantidadL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/productos1.png"))); // NOI18N
        jPanel2.add(cantidadL, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 50));

        check_suma.setBackground(new java.awt.Color(255, 255, 255));
        check_suma.setForeground(new java.awt.Color(42, 102, 142));
        check_suma.setToolTipText("Sumar la cantidad con la del almacen");
        check_suma.setBorder(null);
        check_suma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_sumaActionPerformed(evt);
            }
        });
        jPanel2.add(check_suma, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, -1, -1));

        precio_venta.setBackground(new java.awt.Color(34, 102, 145));
        precio_venta.setBorder(null);
        precio_venta.setForeground(new java.awt.Color(255, 255, 255));
        precio_venta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        precio_venta.setOpaque(false);
        precio_venta.setPhColor(new java.awt.Color(255, 255, 255));
        precio_venta.setPlaceholder("PRECIO VENTA");
        precio_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                precio_ventaKeyTyped(evt);
            }
        });
        jPanel2.add(precio_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 140, 180, -1));

        precio_ventaL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/alimentos/precioL.png"))); // NOI18N
        jPanel2.add(precio_ventaL, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 130, -1, 52));

        jpanel_eliminar_x_tipo.setBackground(new java.awt.Color(255, 255, 255));
        jpanel_eliminar_x_tipo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ELIMINAR POR TIPO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jpanel_eliminar_x_tipo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        foto_off_lista_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/off_enable.png"))); // NOI18N
        jpanel_eliminar_x_tipo.add(foto_off_lista_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        lista_eliminar_tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TIPO PRODUCTO", "ROPA", "CALZADO", "COSMETICOS", "ROPA INTERIOR", "CERAMICA", "ACCESORIOS", "JOYAS", "ASEO", "UTILES DEL HOGAR", "UTILES ESCOLARES", "OTROS" }));
        lista_eliminar_tipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lista_eliminar_tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lista_eliminar_tipoActionPerformed(evt);
            }
        });
        jpanel_eliminar_x_tipo.add(lista_eliminar_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 190, -1));

        foto_tipo_eliminar_tipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/elimina_tipo.png"))); // NOI18N
        foto_tipo_eliminar_tipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                foto_tipo_eliminar_tipoMouseClicked(evt);
            }
        });
        jpanel_eliminar_x_tipo.add(foto_tipo_eliminar_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, 52));

        jPanel2.add(jpanel_eliminar_x_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 270, 90));

        tipoAl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TIPO PRODUCTO", "ROPA", "CALZADO", "COSMETICOS", "ROPA INTERIOR", "CERAMICA", "ACCESORIOS", "JOYAS", "ASEO", "UTILES DEL HOGAR", "UTILES ESCOLARES", "OTROS" }));
        tipoAl.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tipoAl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoAlActionPerformed(evt);
            }
        });
        jPanel2.add(tipoAl, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 30, 190, -1));

        tipoL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/productos.png"))); // NOI18N
        jPanel2.add(tipoL, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 22, -1, 40));

        PANEL_STOCK.setBackground(new java.awt.Color(255, 255, 255));
        PANEL_STOCK.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "STOCK ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        PANEL_STOCK.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        stock_minimo.setBackground(new java.awt.Color(34, 102, 145));
        stock_minimo.setBorder(null);
        stock_minimo.setForeground(new java.awt.Color(255, 255, 255));
        stock_minimo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        stock_minimo.setOpaque(false);
        stock_minimo.setPhColor(new java.awt.Color(255, 255, 255));
        stock_minimo.setPlaceholder("MÍNIMO");
        stock_minimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stock_minimoActionPerformed(evt);
            }
        });
        stock_minimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                stock_minimoKeyTyped(evt);
            }
        });
        PANEL_STOCK.add(stock_minimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, 30));

        stock_maximo.setBackground(new java.awt.Color(34, 102, 145));
        stock_maximo.setBorder(null);
        stock_maximo.setForeground(new java.awt.Color(255, 255, 255));
        stock_maximo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        stock_maximo.setOpaque(false);
        stock_maximo.setPhColor(new java.awt.Color(255, 255, 255));
        stock_maximo.setPlaceholder("MÁXIMO");
        stock_maximo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                stock_maximoKeyTyped(evt);
            }
        });
        PANEL_STOCK.add(stock_maximo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 80, 30));

        cantidadL2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cantidadL2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/productos1.png"))); // NOI18N
        PANEL_STOCK.add(cantidadL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 100, 30));

        cantidadL3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cantidadL3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/productos1.png"))); // NOI18N
        PANEL_STOCK.add(cantidadL3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 100, 30));

        jPanel2.add(PANEL_STOCK, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 270, 60));

        cantidadL1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cantidadL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/productos1.png"))); // NOI18N
        jPanel2.add(cantidadL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 50));

        tablaAlimentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "TIPO PRODUCTO", "PRODUCTO", "CANTIDAD", "PRECIO COMPRA", "PRECIO VENTA", "GANANCIA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaAlimentos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(tablaAlimentos);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "OPCIONES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        registrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agrega1.png"))); // NOI18N
        registrar.setText("Registrar");
        registrar.setBorder(null);
        registrar.setBorderPainted(false);
        registrar.setContentAreaFilled(false);
        registrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        registrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agrega2.png"))); // NOI18N
        registrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });

        actualizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/actualizar.png"))); // NOI18N
        actualizar.setText("Actualizar");
        actualizar.setBorder(null);
        actualizar.setBorderPainted(false);
        actualizar.setContentAreaFilled(false);
        actualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        actualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        actualizar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/actualizar1.png"))); // NOI18N
        actualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });

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

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imp.png"))); // NOI18N
        jButton1.setText("Reporte");
        jButton1.setToolTipText("Imprimir / Guardar Reporte");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imp2.png"))); // NOI18N
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/excel.png"))); // NOI18N
        jButton2.setText("EXCEL");
        jButton2.setToolTipText("Exportar tabla en formato excel");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/excel2.png"))); // NOI18N
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(actualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(eliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(eliminarT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(limpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {actualizar, eliminar, eliminarT, limpiar});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(registrar)
                    .addComponent(actualizar)
                    .addComponent(eliminar)
                    .addComponent(eliminarT)
                    .addComponent(limpiar)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "BUSQUEDA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buscar.setBackground(new java.awt.Color(34, 102, 145));
        buscar.setBorder(null);
        buscar.setForeground(new java.awt.Color(255, 255, 255));
        buscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buscar.setOpaque(false);
        buscar.setPhColor(new java.awt.Color(255, 255, 255));
        buscar.setPlaceholder("CÓDIGO/PRODUCTO");
        buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarMouseClicked(evt);
            }
        });
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
        });
        jPanel4.add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 180, -1));

        codigoL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codigoL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/buscarL.png"))); // NOI18N
        jPanel4.add(codigoL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 250, 52));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        if (selecionRegistro) {
            JOptionPane.showMessageDialog(this, "El CODIGO: " + this.codigo.getText() + "\nya existe en un registro.", "Productos", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/impo.png")));
        } else if (codigo.getText().equals("") || nombre.getText().equals("") || tipoAl.getSelectedItem().equals("TIPO PRODUCTO")
                || precio_compra.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Todos los campos\nson obligatorios.", "Productos", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        } else {
            if (cantidad.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Todos los campos\nson obligatorios.", "Productos", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
                /*
                Runnable runnable1 = new Runnable() {
                    public void run() {
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        Alimentos.this.setEnabled(false);
                        //inicio metodo
                        //code
                        //fin metodo
                productos.AlimentosCod us = new AlimentosCod();
                us.setPrimaryKey(codigo.getText());
                us.setNombre(nombre.getText());
                us.setTipoal(tipoAl.getSelectedItem().toString());
                us.setPrecio_compra(precio_compra.getText());
                us.setPrecio_venta(precio_venta.getText());
                int opcion = OpcionesAl.registrar2(us);
                if (opcion != 0) {
                    String id = codigo.getText();
                    limpiaCampos();
                    selecionaFila(id);
                    codigo.setText("");
                    nombre.setText("");
                    cantidad.setText("");
                    tipoAl.setSelectedItem("TIPO PRODUCTO");
                    precio_compra.setText("");
                    precio_venta.setText("");
                    buscar.setText("");
                     OpcionesAl.extraerID();
                     selecionRegistro = false;
                    JOptionPane.showMessageDialog(Alimentos.this, "Registro éxitoso.", "Alimentos", 0,
                            new ImageIcon(getClass().getResource("/imagenes/agregado.png")));
                }
                Alimentos.this.setEnabled(true);
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    }
                };
                Thread t1 = new Thread(runnable1);
                t1.start();*/
            } else {
                if(OpcionesAl.existe_nombre_producto(nombre.getText())){
                    JOptionPane.showMessageDialog(this, "El producto: ' " + this.nombre.getText() + " '\nya existe en un registro.", "Productos", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/impo.png")));
                }else{
                    if((stock_minimo.getText().equals(stock_maximo.getText()))&&(!stock_minimo.getText().isEmpty()&&!stock_maximo.getText().isEmpty())){
                    JOptionPane.showMessageDialog(this, "El 'STOCK MÍNIMO' no puede ser igual al 'STOCK MÁXIMO.", "Productos", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
                    }else{
                        if((!stock_minimo.getText().isEmpty()&&!stock_maximo.getText().isEmpty())&&(Integer.parseInt(stock_minimo.getText())>Integer.parseInt(stock_maximo.getText()))){
                            JOptionPane.showMessageDialog(this, "El 'STOCK MÍNIMO' no puede mayor que el 'STOCK MÁXIMO.", "Productos", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
                        }else{
                Runnable runnable1 = new Runnable() {
                    @Override
                    public void run() {
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        Alimentos.this.setEnabled(false);
                        //inicio metodo
                        //code
                        //fin metodo
                productos.AlimentosCod us = new AlimentosCod();
                us.setPrimaryKey(codigo.getText());
                us.setNombre(nombre.getText());
                us.setCantidad(cantidad.getText());
                us.setTipoal(tipoAl.getSelectedItem().toString());
                us.setPrecio_compra(precio_compra.getText());
                us.setPrecio_venta(precio_venta.getText());
                    if(stock_minimo.getText().isEmpty()){
                        us.setStock_minimo("0");
                    }else{
                        us.setStock_minimo(stock_minimo.getText());
                    }
                    if(stock_maximo.getText().isEmpty()){
                        us.setStock_maximo("20000");
                    }else{
                        us.setStock_maximo(stock_maximo.getText());
                    }
                int opcion = OpcionesAl.registrar(us);
                if (opcion != 0) {
                    String id = codigo.getText();
                    limpiaCampos();
                    selecionaFila(id);
                    codigo.setText("");
                    nombre.setText("");
                    cantidad.setText("");
                    tipoAl.setSelectedItem("TIPO PRODUCTO");
                    precio_compra.setText("");
                    precio_venta.setText("");
                    buscar.setText("");
                    check_suma.setVisible(false);
                     OpcionesAl.extraerID();
                     selecionRegistro = false;
                     stock_minimo.setText("");
                     stock_maximo.setText("");
                    JOptionPane.showMessageDialog(Alimentos.this, "Registro éxitoso.", "Productos", 0,
                            new ImageIcon(getClass().getResource("/imagenes/agregado.png")));
                }
                Alimentos.this.setEnabled(true);
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    }
                };
                Thread t1 = new Thread(runnable1);
                t1.start();
            }
                }
            }
        }
        }
    }//GEN-LAST:event_registrarActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        if (tablaAlimentos.getRowCount() > 0) {
            if (tablaAlimentos.getSelectedRowCount() > 0) {
                if (codigo.getText().equals("") || nombre.getText().equals("") || tipoAl.getSelectedItem().equals("TIPO ALIMENTOS")
                        || precio_compra.getText().equals("") || precio_venta.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Es necesario completar\nlos campos a actualizar.", "Productos", 0,
                            new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
                } else if (JOptionPane.showConfirmDialog(this, "Esta a punto de actualizar\nun registro.\n¿Desea continuar?", "Productos", JOptionPane.YES_NO_OPTION, 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                    if(stock_minimo.getText().equals(stock_maximo.getText())||
                            Integer.parseInt(stock_minimo.getText())>Integer.parseInt(stock_maximo.getText())){
                    
                        if(stock_minimo.getText().equals(stock_maximo.getText())){
                                    JOptionPane.showMessageDialog(this, "El 'STOCK MÍNIMO' no puede ser igual al 'STOCK MÁXIMO.", "Productos", 0,
                                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
                            }
                        if(Integer.parseInt(stock_minimo.getText())>Integer.parseInt(stock_maximo.getText())){
                                    JOptionPane.showMessageDialog(this, "El 'STOCK MÍNIMO' no puede mayor que el 'STOCK MÁXIMO.", "Productos", 0,
                                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
                            } 
                    }else{
                        if (!cantidad.getText().equals("")) {
//                        Runnable runnable1 = new Runnable() {
//                    public void run() {
//                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//                        Alimentos.this.setEnabled(false);
//                        //inicio metodo
//                        //code
//                        //fin metodo
                        if (check_suma.isSelected()) {
                            productos.AlimentosCod us = new AlimentosCod();
                            us.setPrimaryKey(codigo.getText());
                            us.setNombre(nombre.getText());
                            us.setCantidad(String.valueOf(Integer.parseInt(cantidad.getText())+cantAlmacen));
                            us.setTipoal(tipoAl.getSelectedItem().toString());
                            us.setPrecio_compra(precio_compra.getText());
                            us.setPrecio_venta(precio_venta.getText());
                            us.setStock_minimo(stock_minimo.getText());
                            us.setStock_maximo(stock_maximo.getText());
                            int opcion = OpcionesAl.actualizar(us);
                            if (opcion != 0) {
                                String id = codigo.getText();
                                limpiaCampos();
                                selecionaFila(id);
                                JOptionPane.showMessageDialog(Alimentos.this, "Registro actualizado.", "Productos", 0,
                                        new ImageIcon(getClass().getResource("/imagenes/actualiza1.png")));
                            }
                        } else {
                            productos.AlimentosCod us = new AlimentosCod();
                            us.setPrimaryKey(codigo.getText());
                            us.setNombre(nombre.getText());
                            us.setCantidad(cantidad.getText());
                            us.setTipoal(tipoAl.getSelectedItem().toString());
                            us.setPrecio_compra(precio_compra.getText());
                            us.setPrecio_venta(precio_venta.getText());
                            us.setStock_minimo(stock_minimo.getText());
                            us.setStock_maximo(stock_maximo.getText());
                            int opcion = OpcionesAl.actualizar(us);
                            if (opcion != 0) {
                                String id = codigo.getText();
                                limpiaCampos();
                                selecionaFila(id);
                                JOptionPane.showMessageDialog(Alimentos.this, "Registro actualizado.", "Productos", 0,
                                        new ImageIcon(getClass().getResource("/imagenes/actualiza1.png")));
                            }
                        }
//                        Alimentos.this.setEnabled(true);
//                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
//                    }
//                };
//                Thread t1 = new Thread(runnable1);
//                t1.start();
                    } else {
                        JOptionPane.showMessageDialog(this, "Debe especificar la cantidad.", "Productos", 0,
                                new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png")));
//                        productos.AlimentosCod us = new AlimentosCod();
//                        us.setPrimaryKey(codigo.getText());
//                        us.setNombre(nombre.getText());
//                        us.setTipoal(tipoAl.getSelectedItem().toString());
//                        us.setPrecio_compra(precio_compra.getText());
//                        us.setPrecio_venta(precio_venta.getText());
//                        int opcion = OpcionesAl.actualizar(us);
//                        if (opcion != 0) {
//                            String id = codigo.getText();
//                            limpiaCampos();
//                            selecionaFila(id);
//                            JOptionPane.showMessageDialog(this, "Registro actualizado.", "Productos", 0,
//                                    new ImageIcon(getClass().getResource("/imagenes/alimentos/actualizado.png")));
//                        }
                    }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un registro.", "Productos", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
            }

        } else {
            JOptionPane.showMessageDialog(this, "No hay registros\npara actualizar.", "Productos", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        }
        check_suma.setSelected(false);
        check_suma.setVisible(false);
    }//GEN-LAST:event_actualizarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        if (tablaAlimentos.getRowCount() > 0) {
            if (tablaAlimentos.getSelectedRowCount() > 0) {
                if (JOptionPane.showConfirmDialog(this, "Esta a punto de eliminar\nun registro.\n¿Desea continuar?", "Productos", JOptionPane.YES_NO_OPTION, 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                   Runnable runnable1 = new Runnable() {
                    public void run() {
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        Alimentos.this.setEnabled(false);
                        //inicio metodo
                        //code
                        //fin metodo
                        if(al.size()<1){
                    int fila = tablaAlimentos.getSelectedRow();
                    String id = tablaAlimentos.getValueAt(fila, 0).toString();
                    int elimina = OpcionesAl.eliminar(id);
                    if (elimina != 0) {
                        limpiaCampos();
                        JOptionPane.showMessageDialog(Alimentos.this, "Registro eliminado.", "Productos", 0,
                                new ImageIcon(getClass().getResource("/imagenes/borrar1 copia.png")));
                    }
                    }else{
                            int elimina=0;
                            int total=al.size();
                            for(int i=0;i<=al.size()-1;i++){
                                elimina+=OpcionesAl.eliminar(String.valueOf(tablaAlimentos.getValueAt(Integer.parseInt(al.get(i).toString()) , 0)));
                                
                            }
                        limpiaCampos();
                        JOptionPane.showMessageDialog(Alimentos.this, "Registros eliminados.", "Productos", 0,
                                new ImageIcon(getClass().getResource("/imagenes/borrar1 copia.png")));
                        al.clear();
                    
                        }
                    Alimentos.this.setEnabled(true);
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    }
                };
                Thread t1 = new Thread(runnable1);
                t1.start();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un registro.", "Productos", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
            }

        } else {
            JOptionPane.showMessageDialog(this, "No hay registros\npara eliminar.", "Productos", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void eliminarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarTActionPerformed
        if (tablaAlimentos.getRowCount() > 0) {
            if (JOptionPane.showConfirmDialog(this, "Esta a punto de elimnar\ntodos los registros.\n¿Desea continuar?", "Productos", JOptionPane.YES_NO_OPTION, 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
               Runnable runnable1 = new Runnable() {
                    public void run() {
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        Alimentos.this.setEnabled(false);
                        //inicio metodo
                        //code
                        //fin metodo
                int eliminaT = OpcionesAl.eliminaTodos();
                if (eliminaT != 0) {
                    limpiaCampos();
                    JOptionPane.showMessageDialog(Alimentos.this, "Registros eliminados.", "Productos", 0,
                            new ImageIcon(getClass().getResource("/imagenes/borrar1 copia.png")));
                }
                Alimentos.this.setEnabled(true);
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    }
                };
                Thread t1 = new Thread(runnable1);
                t1.start();
            }
        } else {
            JOptionPane.showMessageDialog(this, "No hay registros\npara eliminar.", "Productos", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        }
    }//GEN-LAST:event_eliminarTActionPerformed

    private void nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyTyped
        char letras = evt.getKeyChar();
        if ((letras < 'a' || letras > 'z') && (letras < 'A' | letras > 'Z')) {
            if ((letras != 'ñ') && (letras != 'Ñ') && (letras != 'á') && (letras != 'Á') && (letras != 'é') && (letras != 'É') && (letras != 'í')
                    && (letras != 'Í') && (letras != 'ó') && (letras != 'Ó') && (letras != 'ú') && (letras != 'Ú') && (letras != ' ')) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_nombreKeyTyped

    private void precio_compraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precio_compraKeyTyped
        char num = evt.getKeyChar();
        if ((num < '0' || num > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_precio_compraKeyTyped

    private void nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyReleased
        nombre.setText(nombre.getText().toUpperCase());
    }//GEN-LAST:event_nombreKeyReleased

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        buscar.setText(buscar.getText().toUpperCase());
        OpcionesAl.listar(buscar.getText());
    }//GEN-LAST:event_buscarKeyReleased

    private void buscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarMouseClicked
        limpiaCampos();
    }//GEN-LAST:event_buscarMouseClicked

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        limpiaCampos();
        
    }//GEN-LAST:event_limpiarActionPerformed

    private void cantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyTyped
        // TODO add your handling code here:
        char num = evt.getKeyChar();
        if ((num < '0' || num > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_cantidadKeyTyped

    private void tipoAlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoAlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoAlActionPerformed

    private void check_sumaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_sumaActionPerformed
        // TODO add your handling code here:
        if(check_suma.isSelected()){
            cantidad.setText("");
        }else{
            cantidad.setText(tempCant);
        }
        cantidad.requestFocus();
    }//GEN-LAST:event_check_sumaActionPerformed

    private void precio_ventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precio_ventaKeyTyped
        // TODO add your handling code here:
        char num = evt.getKeyChar();
        if ((num < '0' || num > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_precio_ventaKeyTyped

    private void lista_eliminar_tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lista_eliminar_tipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lista_eliminar_tipoActionPerformed

    private void on_offActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_on_offActionPerformed
        // TODO add your handling code here:
        if(on_off.isSelected()){
            lista_eliminar_tipo.setVisible(true);
            lista_eliminar_tipo.setEnabled(true);
        foto_tipo_eliminar_tipo.setEnabled(true);
        foto_off_lista_eliminar.setVisible(false);
        jpanel_eliminar_x_tipo.setEnabled(true);
        }else{
            lista_eliminar_tipo.setVisible(false);
            lista_eliminar_tipo.setEnabled(false);
        foto_tipo_eliminar_tipo.setEnabled(false);
        foto_off_lista_eliminar.setVisible(true);
        jpanel_eliminar_x_tipo.setEnabled(false);
        lista_eliminar_tipo.getModel().setSelectedItem("TIPO PRODUCTO");
        }
        
    }//GEN-LAST:event_on_offActionPerformed

    private void foto_tipo_eliminar_tipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foto_tipo_eliminar_tipoMouseClicked
        // TODO add your handling code here:
        String tipo=lista_eliminar_tipo.getModel().getSelectedItem().toString();
        if(!tipo.equals("TIPO PRODUCTO")){
        if (JOptionPane.showConfirmDialog(Alimentos.this, "Esta a punto de eliminar todos los\nproductos del tipo '"+tipo+"'.\n¿Desea continuar?", "Productos", JOptionPane.YES_NO_OPTION, 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                         
                   Runnable runnable1 = new Runnable() {
                    public void run() {
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        Alimentos.this.setEnabled(false);
                        //inicio metodo
                        //code
                        //fin metodo
                    int elimina = OpcionesAl.eliminar_por_tipo(tipo);
                    if (elimina != 0) {
                        JOptionPane.showMessageDialog(Alimentos.this, "Registro eliminado.", "Productos", 0,
                                new ImageIcon(getClass().getResource("/imagenes/borrar1 copia.png")));
                        limpiaCampos();
                    }
                    Alimentos.this.setEnabled(true);
                        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    }
                };
                Thread t1 = new Thread(runnable1);
                t1.start();
                }
        }
    }//GEN-LAST:event_foto_tipo_eliminar_tipoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Runnable runnable1 = new Runnable() {
            public void run() {
                escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
                Alimentos.this.setEnabled(false);
                //inicio metodo
                report();
                //fin metodo
                Alimentos.this.setEnabled(true);
                escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
        };
        Thread t1 = new Thread(runnable1);
        t1.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Runnable runnable1 = new Runnable() {
            public void run() {
                escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
                Alimentos.this.setEnabled(false);
                //inicio metodo
                OpcionesAl o=new OpcionesAl();
                o.generarExcel();
                //fin metodo
                Alimentos.this.setEnabled(true);
                escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
        };
        Thread t1 = new Thread(runnable1);
        t1.start();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void stock_minimoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stock_minimoKeyTyped
        // TODO add your handling code here:
        char num = evt.getKeyChar();
        if ((num < '0' || num > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_stock_minimoKeyTyped

    private void stock_minimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stock_minimoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stock_minimoActionPerformed

    private void stock_maximoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stock_maximoKeyTyped
        // TODO add your handling code here:
        char num = evt.getKeyChar();
        if ((num < '0' || num > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_stock_maximoKeyTyped

    public void report() {
        try {
            
            conectar cc = new conectar();
            String ruta_logo="imagenes/logo3.png";
            Map parametro = new HashMap();
            parametro.put("total", OpcionesAl.extraer_totales("SELECT sum(cantidad_al) FROM alimentos"));
            parametro.put("ganancia", OpcionesAl.extraer_totales("SELECT sum(ganancia) FROM alimentos"));
            parametro.put("inversion", OpcionesAl.extraer_totales("SELECT sum(precio_compra) FROM alimentos"));
            parametro.put("logo", ruta_logo);
            
            JasperPrint jprint = JasperFillManager.fillReport(this.getClass().getClassLoader().getResourceAsStream("reportes/almacen.jasper"), parametro, cc.conexion());
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            Image icon = new ImageIcon(getClass().getResource("/imagenes/inventario.png")).getImage();
            view.setIconImage(icon);
            view.setTitle("REPORTE DE ALMACEN");
            view.setVisible(true);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
    public String total(){
        String total = null;
        
        return total;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PANEL_STOCK;
    private javax.swing.JButton actualizar;
    private app.bolivia.swing.JCTextField buscar;
    private app.bolivia.swing.JCTextField cantidad;
    private javax.swing.JLabel cantidadL;
    private javax.swing.JLabel cantidadL1;
    private javax.swing.JLabel cantidadL2;
    private javax.swing.JLabel cantidadL3;
    private javax.swing.JCheckBox check_suma;
    public static app.bolivia.swing.JCTextField codigo;
    private javax.swing.JLabel codigoL;
    private javax.swing.JLabel codigoL1;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton eliminarT;
    private javax.swing.JLabel foto_off_lista_eliminar;
    private javax.swing.JLabel foto_tipo_eliminar_tipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpanel_eliminar_x_tipo;
    private javax.swing.JButton limpiar;
    private org.bolivia.combo.SComboBoxBlue lista_eliminar_tipo;
    private app.bolivia.swing.JCTextField nombre;
    private javax.swing.JLabel nombreL;
    private javax.swing.JCheckBox on_off;
    private app.bolivia.swing.JCTextField precio_compra;
    private javax.swing.JLabel precio_compraL;
    private app.bolivia.swing.JCTextField precio_venta;
    private javax.swing.JLabel precio_ventaL;
    private javax.swing.JButton registrar;
    private app.bolivia.swing.JCTextField stock_maximo;
    private app.bolivia.swing.JCTextField stock_minimo;
    public static javax.swing.JTable tablaAlimentos;
    private org.bolivia.combo.SComboBoxBlue tipoAl;
    private javax.swing.JLabel tipoL;
    // End of variables declaration//GEN-END:variables
}
