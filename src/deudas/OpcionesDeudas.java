/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deudas;

import ventas.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import principal.GenerarNumero;
import principal.conectar;

/**
 *
 * @author 4L3
 */
public class OpcionesDeudas {

    static conectar cc = new conectar();
    static Connection cn = cc.conexion();
    static PreparedStatement ps;

    public static int registrar(VentasCod uc) {
        int rsu = 0;
        String sql = VentasCod.REGISTRAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, uc.getPrimaryKey());
            ps.setString(2, uc.getDescripcion());
            ps.setString(3, uc.getCantidad());
            ps.setString(4, uc.getTotal());
            ps.setString(5, uc.getInversion());
            ps.setString(6, uc.getGanancia());
            ps.setString(7, uc.getFecha());
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }
    
    public static int registrar_ventas_desde_deudas(String numero) {
        int rsu = 0;
        String sql = "INSERT INTO `registro_venta`(`numero`, `descripcion`, `cantidad`, `total`, `inversion`, `ganancia`, `fecha`) VALUES ('"+numero+"', (select `descripcion` from registro_deuda where `numero`='"+numero+"' limit 1), (select `cantidad` from registro_deuda where `numero`='"+numero+"' limit 1), (select `total` from registro_deuda where `numero`='"+numero+"' limit 1), (select `inversion` from registro_deuda where `numero`='"+numero+"' limit 1), (select `ganancia` from registro_deuda where `numero`='"+numero+"' limit 1), (select `fecha` from registro_deuda where `numero`='"+numero+"' limit 1))";
                                                        
                                                          
        try {
            ps = cn.prepareStatement(sql);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }
    
    public static int registrar_ventas_desde_deudas_sin_id_pero_sola_fila_dosDatos(String numero, String descripcion) {
        int rsu = 0;
        String sql = "INSERT INTO `registro_venta`(`numero`, `descripcion`, `cantidad`, `total`, `inversion`, `ganancia`, `fecha`) "
                + "VALUES ('"+numero+"', "
                + "'"+descripcion+"',"
                + "(select `cantidad` from registro_deuda where `numero`='"+numero+"' and descripcion='"+descripcion+"'), "
                + "(select `total` from registro_deuda where `numero`='"+numero+"' and descripcion='"+descripcion+"'), "
                + "(select `inversion` from registro_deuda where `numero`='"+numero+"' and descripcion='"+descripcion+"'), "
                + "(select `ganancia` from registro_deuda where `numero`='"+numero+"' and descripcion='"+descripcion+"'), "
                + "(select `fecha` from registro_deuda where `numero`='"+numero+"' limit 1))";
                                                        
                                                          
        try {
            ps = cn.prepareStatement(sql);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }
    
    public static int registrar_ventas_desde_deudas_sin_id_pero_sola_fila(String numero, String descripcion, String cantidad, String total,  String inversion, String ganancia) {
        int rsu = 0;
        String sql = "INSERT INTO `registro_venta`(`numero`, `descripcion`, `cantidad`, `total`, `inversion`, `ganancia`, `fecha`) "
                + "VALUES ('"+numero+"', "
                + "(select `descripcion` from registro_deuda where `numero`='"+numero+"' and descripcion='"+descripcion+"'), "
                + "'"+cantidad+"', "
                + "'"+total+"', "
                + "'"+inversion+"', "
                + "'"+ganancia+"', "
                + "(select `fecha` from registro_deuda where `numero`='"+numero+"' limit 1))";
                                                        
                                                          
        try {
            ps = cn.prepareStatement(sql);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }
    
    
    public static int registrarDeuda(DeudasCod uc) {
        int rsu = 0;
        String sql = DeudasCod.REGISTRAR_DEUDA;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, uc.getPrimaryKey());
            ps.setString(2, uc.getTitular());
            ps.setString(3, uc.getDescripcion());
            ps.setString(4, uc.getCantidad());
            ps.setString(5, uc.getTotal());
            ps.setString(6, uc.getInversion());
            ps.setString(7, uc.getGanancia());
            ps.setString(8, uc.getFecha());
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }
    
    public static int registrar2(VentasCod uc) {
        int rsu = 0;
        String sql = VentasCod.REGISTRAR2;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, uc.getDescripcion());
            ps.setString(2, uc.getCantidad());
            ps.setString(3, uc.getTotal());
            ps.setString(4, uc.getInversion());
            ps.setString(5, uc.getGanancia());
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }
    
    public static int registrar_numero_venta(VentasCod uc) {
        int rsu = 0;
        String sql = VentasCod.REGISTRAR_numero_venta_y_fecha;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, uc.getPrimaryKey());
            ps.setString(2, uc.getFecha());
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }

    public static int eliminar(String id) {
        int rsu = 0;
        String sql =  "DELETE FROM `registro_deuda` WHERE numero='"+id+"' limit 1 ";

        try {
            ps = cn.prepareStatement(sql);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }
    
    public static int eliminar_todo(String id) {
        int rsu = 0;
        String sql =  "DELETE  FROM `registro_deuda` ";

        try {
            ps = cn.prepareStatement(sql);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }
    
    public static int eliminar_dos_deuda(String id, String descripcion) {
        int rsu = 0;
        String sql =  "DELETE FROM `registro_deuda` WHERE numero='"+id+"' and descripcion='"+descripcion+"' ";

        try {
            ps = cn.prepareStatement(sql);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }
    
    public static int eliminar_dos(String id, String descripcion) {
        int rsu = 0;
        String sql = "";
            sql=VentasCod.ELIMINAR2;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, descripcion);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }
    
    public static int actualizar_y_recalcular_total_registro_deuda( String id) {
       
        int rsu = 0;
        String sql = DeudasCod.ACTUALIZAR_TOTAL_REGISTRO_DEUDA;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, total_tabla_registro(id));//total
            ps.setString(2, id);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
        }
        System.out.println(sql);
        return rsu;
    }
    
    public static String total_tabla_registro(String numero) {
            String sql = "SELECT sum(`inversion`)+sum(`ganancia`) as TOTAL FROM `registro_deuda` WHERE numero='"+numero+"' ";
        String precio = "";
        int cant = 0;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cant = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
        precio=String.valueOf(cant);
        return precio;
    }
    
    public static String total_tabla_registro_venta(String numero) {
            String sql = "SELECT sum(`inversion`)+sum(`ganancia`) as TOTAL FROM `registro_venta` WHERE numero='"+numero+"' ";
        String precio = "";
        int cant = 0;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cant = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
        precio=String.valueOf(cant);
        return precio;
    }
    
     public static int actualizar_cantidad_inversion_ganancia_registro_deuda(String id,String descripcion, String cantidad, boolean activo) {
        int rsu = 0;
        String sql = "";
            sql=DeudasCod.ACTUALIZAR_CANTIDAD_INVERSION_GANANCIA_REGISTRO_DEUDA;
        try {
            ps = cn.prepareStatement(sql);
            if(activo){
/*cantidad restando*/ ps.setString(1, String.valueOf((Integer.parseInt(cantidad_producto_tablaDeudas(id,descripcion))-Integer.parseInt(cantidad))));
            }else{
/*cantidad sin restar*/ ps.setString(1, String.valueOf(Integer.parseInt(cantidad)));               
            }
            if(activo){
/*ganancia restando*/      ps.setString(2, String.valueOf((Integer.parseInt(precio_venta(descripcion))-Integer.parseInt(precio_compra(descripcion)))*(Integer.parseInt(cantidad_producto_tablaDeudas(id,descripcion))-Integer.parseInt(cantidad))));
            }else{
/*ganancia sin restando*/      ps.setString(2, String.valueOf((Integer.parseInt(precio_venta(descripcion))-Integer.parseInt(precio_compra(descripcion)))*Integer.parseInt(cantidad)));                
            }
            if(activo){
/*inversion restando*/      ps.setString(3, String.valueOf(Integer.parseInt(precio_compra(descripcion))*(Integer.parseInt(cantidad_producto_tablaDeudas(id,descripcion))-Integer.parseInt(cantidad))));
            }else{
/*inversion sin restando*/      ps.setString(3, String.valueOf(Integer.parseInt(precio_compra(descripcion))*Integer.parseInt(cantidad)));                
            }
            ps.setString(4, id);
            ps.setString(5, descripcion);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }
     
     public static int cantidad_coincidencias_id( String numero){
        String sql = "SELECT count(*) FROM `registro_deuda` WHERE `numero`='"+numero+"' ";
        int cant = 0;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cant = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cant;
    }

      public static String cantidad_registro_venta(String id,String descripcion, String cantidad, boolean activo) {
        String rsu = "";
        try {
            if(activo){
/*cantidad restando*/    rsu = String.valueOf(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))+Integer.parseInt(cantidad));  
            }
            else{
/*cantidad sin restar*/      rsu = String.valueOf(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))+(Integer.parseInt(cantidad_producto_tablaDeudas(id,descripcion))-Integer.parseInt(cantidad)));
                System.out.println("cantidad de la tabla deudas "+cantidad_producto_tablaDeudas(id, descripcion));
            }
        } catch (Exception ex) {
        }
        return rsu;
    }
      
      public static String cantidad_registro_venta_sin_on_off(String id,String descripcion) {
        String rsu = "";
        try {
/*cantidad*/    rsu = String.valueOf(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))+Integer.parseInt(cantidad_producto_tablaDeudas(id, descripcion)));  
        } catch (Exception ex) {
        }
        return rsu;
    }
     
       public static String ganancia_registro_VENTA(String id,String descripcion, String cantidad, boolean activo) {
       String rsu = "";
       try{ 
       if(activo){
/*ganancia restando*/     rsu = String.valueOf((Integer.parseInt(precio_venta(descripcion))-Integer.parseInt(precio_compra(descripcion)))*(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))+Integer.parseInt(cantidad)));
            }else{
/*ganancia sin restando*/      rsu = String.valueOf((Integer.parseInt(precio_venta(descripcion))-Integer.parseInt(precio_compra(descripcion)))*(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))+(Integer.parseInt(cantidad_producto_tablaDeudas(id, descripcion))-Integer.parseInt(cantidad))));                
            }
        } catch (Exception ex) {
            
        }
        return rsu;
    }
       
       public static String ganancia_registro_VENTA_sin_on_off(String id,String descripcion) {
       String rsu = "";
       try{ 
/*ganancia restando*/     rsu = String.valueOf((Integer.parseInt(precio_venta(descripcion))-Integer.parseInt(precio_compra(descripcion)))*(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))+Integer.parseInt(cantidad_producto_tablaDeudas(id, descripcion))));

        } catch (Exception ex) {
            
        }
        return rsu;
    }
       
       public static String inversion_registro_venta(String id,String descripcion, String cantidad, boolean activo) {
        String rsu = "";
        try {
            if(activo){
/*inversion restando*/      rsu= String.valueOf(Integer.parseInt(precio_compra(descripcion))*(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))+Integer.parseInt(cantidad)));
            }else{
/*inversion sin restando*/      rsu=String.valueOf(Integer.parseInt(precio_compra(descripcion))*(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))+(Integer.parseInt(cantidad_producto_tablaDeudas(id, descripcion))-Integer.parseInt(cantidad))));                
            }
        } catch (Exception ex) {
        }
        return rsu;
    }
      
       public static String inversion_registro_venta_sin_on_off(String id,String descripcion) {
        String rsu = "";
        try {
/*inversion restando*/      rsu= String.valueOf(Integer.parseInt(precio_compra(descripcion))*(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))+Integer.parseInt(cantidad_producto_tablaDeudas(id, descripcion))));
        } catch (Exception ex) {
        }
        return rsu;
    }
       
    public static int eliminaTodos() {
        int rsu = 0;
        String sql = DeudasCod.ELIMINAR_TODO;
        try {
            ps = cn.prepareStatement(sql);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }

    public static void listar(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) deudas.RegistroDeudas.tablaDeudas.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (busca.equals("")) {
            sql = DeudasCod.LISTAR;
        } else {
            sql = "SELECT * FROM registro_deuda WHERE (numero like'" + busca + "%' or fecha='" + busca + "')"
                    + " ORDER BY fecha";
        }
        String datos[] = new String[8];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("numero");
                datos[1] = rs.getString("titular");
                datos[2] = rs.getString("descripcion");
                datos[3] = rs.getString("cantidad");
                datos[4] = rs.getString("total");
                datos[5] = rs.getString("inversion");
                datos[6] = rs.getString("ganancia");
                datos[7] = rs.getString("fecha");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesDeudas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void listar_buscar(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) deudas.RegistroDeudas.tablaDeudas.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (busca.equals("")) {
            sql = DeudasCod.LISTAR;
        } else {
            sql = "SELECT * FROM registro_deuda WHERE (titular like'" + busca + "%' or fecha='" + busca + "'or numero like'" + busca + "%' or descripcion like'" + busca + "%')"
                    + " ORDER BY fecha";
        }
        String datos[] = new String[8];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("numero");
                datos[1] = rs.getString("titular");
                datos[2] = rs.getString("descripcion");
                datos[3] = rs.getString("cantidad");
                datos[4] = rs.getString("total");
                datos[5] = rs.getString("inversion");
                datos[6] = rs.getString("ganancia");
                datos[7] = rs.getString("fecha");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void listar_buscar_con_filtro(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) deudas.RegistroDeudas.tablaDeudas1.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (busca.equals("")) {
            sql = "SELECT `titular`,sum(`ganancia`) + sum(`inversion`) as TOTAL,sum(`inversion`) as INVERSION,sum(`ganancia`) as GANANCIA FROM `registro_deuda` group by `titular` ";
        } else {
            sql = "SELECT `titular`,sum(`ganancia`) + sum(`inversion`) as TOTAL,sum(`inversion`) as INVERSION,sum(`ganancia`) as GANANCIA FROM `registro_deuda` WHERE titular like'" + busca + "%' group by `titular` ";
        }
        String datos[] = new String[4];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("titular");
                datos[1] = rs.getString("total");
                datos[2] = rs.getString("inversion");
                datos[3] = rs.getString("ganancia");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void listar_por_titulares(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) deudas.RegistroDeudas.tablaDeudas1.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (busca.equals("TODOS")) {
            sql = "SELECT `titular`,sum(`ganancia`) + sum(`inversion`) as TOTAL,sum(`inversion`) as INVERSION,sum(`ganancia`) as GANANCIA FROM `registro_deuda` group by `titular` ";
        } else {
            sql = "SELECT `titular`,sum(`ganancia`) + sum(`inversion`) as TOTAL,sum(`inversion`) as INVERSION,sum(`ganancia`) as GANANCIA FROM `registro_deuda` where titular='"+busca+"' group by `titular` ";
        }
       String datos[] = new String[4];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("titular");
                datos[1] = rs.getString("total");
                datos[2] = rs.getString("inversion");
                datos[3] = rs.getString("ganancia");
                modelo.addRow(datos);
            }
//            for(int i=0;i<RegistroVentas.tablaVentas1.getRowCount();i++){
//           // ganancia(RegistroVentas.tablaVentas.getValueAt(i, 0).toString());
//                RegistroVentas.tablaVentas1.setValueAt(String.valueOf(Double.parseDouble(RegistroVentas.tablaVentas1.getValueAt(i, 1).toString())*Double.parseDouble(RegistroVentas.tablaVentas1.getValueAt(i, 4).toString())), i, 4);
//                RegistroVentas.tablaVentas1.setValueAt(String.valueOf(Double.parseDouble(RegistroVentas.tablaVentas1.getValueAt(i, 3).toString())-Double.parseDouble(RegistroVentas.tablaVentas1.getValueAt(i, 4).toString())), i, 3);
//                    }
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesDeudas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void get_combos_titulares(JComboBox combo) {         
        try {
            
          // viga  
            String sql = "SELECT `titular` FROM `registro_deuda` group by `titular` ";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            combo.addItem("TODOS");
            while (rs.next()) {
                combo.addItem(rs.getString("titular"));
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static String ganancia(String algo){
        
        String result="";
        String SQL="SELECT `precio_venta`-`precio_compra` as resta FROM `alimentos` WHERE `codigo_al`='"+algo+"' ";
       // String datos[] = new String[1];
       
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                result = rs.getString("resta");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesDeudas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    

    public static void numeros() {
        int j;
        int cont = 1;
        String num = "";
        String c = "";
        String SQL = "SELECT MAX(numero) FROM registro_venta";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }

            if (c == null) {
                ventas.CajaAd.numFac.setText("00000001");
            } else {
                j = Integer.parseInt(c);
                GenerarNumero gen = new GenerarNumero();
                gen.generar(j);
                ventas.CajaAd.numFac.setText(gen.serie());

            }

        } catch (SQLException ex) {
            Logger.getLogger(OpcionesDeudas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public static void numeros1() {
//        int j;
//        int cont = 1;
//        String num = "";
//        String c = "";
//        String SQL = "SELECT MAX(numero) FROM registro_venta";
//        try {
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery(SQL);
//            if (rs.next()) {
//                c = rs.getString(1);
//            }
//
//            if (c == null) {
//                ventas.CajaNor.numFac.setText("00000001");
//            } else {
//                j = Integer.parseInt(c);
//                GenerarNumero gen = new GenerarNumero();
//                gen.generar(j);
//                ventas.CajaNor.numFac.setText(gen.serie());
//
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
     /*public static boolean busca(String codigo) {
        int j;
        boolean hay=true;
        int cont = 1;
        String num = "";
        String c = "";
        String SQL = "SELECT * FROM `alimentos` WHERE `codigo_al`='"+codigo+"' and `cantidad_al` <> '0' ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }

            if (c == null) {
                hay=false;
            } 

        } catch (SQLException ex) {
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hay;
    }*/
    
//   public static int actualizar(String descripcion, String cantidad) {
//        int rsu = 0;
//        String sql = DeudasCod.ACTUALIZAR;
//        try {
//            ps = cn.prepareStatement(sql);
//            
///*cantidad*/      ps.setString(1, String.valueOf(Integer.parseInt(cantidad_producto_tablaVentas(descripcion))+Integer.parseInt(cantidad)));
//            
///*ganancia*/      ps.setString(2, String.valueOf((Integer.parseInt(precio_venta(descripcion))-Integer.parseInt(precio_compra(descripcion)))*(Integer.parseInt(cantidad_producto(descripcion))+Integer.parseInt(cantidad))));
//            System.out.println("ganancia actualizada : "+String.valueOf((Integer.parseInt(precio_venta(descripcion))-Integer.parseInt(precio_compra(descripcion)))*(Integer.parseInt(cantidad_producto(descripcion))+Integer.parseInt(cantidad))));
//            ps.setString(3, descripcion);
//            rsu = ps.executeUpdate();
//        } catch (SQLException ex) {
//        }
//        System.out.println(sql);
//        return rsu;
//    }
   
   public static boolean existe_venta(String numero) {
        String c = null;
        boolean existe=false;
        String sql = "SELECT * FROM `registro_venta` WHERE `numero`='"+numero+"' ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                c = rs.getString(1);
            }
            if (c != null) {
                existe=true;
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesDeudas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
   
   public static boolean existe_venta_id_descripcion(String numero, String descripcion) {
        String c = null;
        boolean existe=false;
        String sql = "SELECT * FROM `registro_venta` WHERE `numero`='"+numero+"' and `descripcion`='"+descripcion+"' ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                c = rs.getString(1);
            }
            if (c != null) {
                existe=true;
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesDeudas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
   
      public static boolean existe_venta_id_pero_no_descripcion(String numero, String descripcion) {
        String c = null;
        boolean existe=false;
        String sql = "SELECT * FROM `registro_venta` WHERE `numero`='"+numero+"' and `descripcion`<>'"+descripcion+"' ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                c = rs.getString(1);
            }
            if (c != null) {
                existe=true;
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesDeudas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
   
    public static boolean existe_deuda(String numero) {
        String c = null;
        boolean existe=false;
        String sql = "SELECT * FROM `registro_deuda` WHERE `numero`='"+numero+"' ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                c = rs.getString(1);
            }
            System.out.println("VALOR DE C: "+c);
            if (c != null) {
                existe=true;
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesDeudas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
    
   
    public static int actualizar_con_on_of(String descripcion, String cantidad, String id, boolean activo) {
        int rsu = 0;
        String sql = DeudasCod.ACTUALIZAR;
       
        try {
            ps = cn.prepareStatement(sql);
            if(activo){
/*cantidad restando*/    ps.setString(1, String.valueOf(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))+Integer.parseInt(cantidad)));  
            }
            else{
/*cantidad sin restar*/      ps.setString(1, String.valueOf(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))+(Integer.parseInt(cantidad_producto_tablaDeudas(id,descripcion))-Integer.parseInt(cantidad))));
                System.out.println("cantidad de la tabla deudas "+cantidad_producto_tablaDeudas(id, descripcion));
            }
            if(activo){
/*ganancia restando*/      ps.setString(2, String.valueOf((Integer.parseInt(precio_venta(descripcion))-Integer.parseInt(precio_compra(descripcion)))*(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))+Integer.parseInt(cantidad))));
            }else{
/*ganancia sin restando*/      ps.setString(2, String.valueOf((Integer.parseInt(precio_venta(descripcion))-Integer.parseInt(precio_compra(descripcion)))*(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))+(Integer.parseInt(cantidad_producto_tablaDeudas(id, descripcion))-Integer.parseInt(cantidad)))));                
            }
            if(activo){
/*inversion restando*/      ps.setString(3, String.valueOf(Integer.parseInt(precio_compra(descripcion))*(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))+Integer.parseInt(cantidad))));
            }else{
/*inversion sin restando*/      ps.setString(3, String.valueOf(Integer.parseInt(precio_compra(descripcion))*(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))+(Integer.parseInt(cantidad_producto_tablaDeudas(id, descripcion))-Integer.parseInt(cantidad)))));                
            }
            
            ps.setString(4, id);
            ps.setString(5, descripcion);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
        }
        System.out.println(sql);
        return rsu;
    }
    
    public static int actualizar_sin_on_of(String descripcion, String id) {
        int rsu = 0;
        String sql = DeudasCod.ACTUALIZAR;
       
        try {
            ps = cn.prepareStatement(sql);
/*cantidad */    ps.setString(1, String.valueOf(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))+Integer.parseInt(cantidad_producto_tablaDeudas(id,descripcion))));  
           
/*ganancia*/      ps.setString(2, String.valueOf((Integer.parseInt(precio_venta(descripcion))-Integer.parseInt(precio_compra(descripcion)))*(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))+Integer.parseInt(cantidad_producto_tablaDeudas(id,descripcion)))));  
/*inversion*/      ps.setString(3, String.valueOf(Integer.parseInt(precio_compra(descripcion))*(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))+Integer.parseInt(cantidad_producto_tablaDeudas(id,descripcion)))));  
            ps.setString(4, id);
            ps.setString(5, descripcion);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
        }
        System.out.println(sql);
        return rsu;
    }
    
    public static int actualizar_y_recalcular_registro_venta( String numero) {
        int rsu = 0;
        String sql = VentasCod.ACTUALIZAR_REGISTRO_VENTA;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,total_precio_venta(numero)); //total
            ps.setString(2, numero);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
        }
        System.out.println(sql);
        return rsu;
    }
    
//    public static int actualizar_ganancia_alimentos( String descripcion,String codigo_al) {
//        int rsu = 0;
//        String sql = VentasCod.ACTUALIZAR_GANANCIA_ALIMENTOS;
//        try {
//            ps = cn.prepareStatement(sql);
//            ps.setString(1,String.valueOf((Double.parseDouble(precio_venta(descripcion))-Double.parseDouble(precio_compra(descripcion)))*Double.parseDouble(cantidad_producto(descripcion)))); //total
//            ps.setString(2, codigo_al);
//            rsu = ps.executeUpdate();
//        } catch (SQLException ex) {
//        }
//        System.out.println(sql);
//        return rsu;
//    }
    
    public static int actualizar_y_recalcular_registro_venta2( String descripcion, String cantidad, String id) {
       
        int rsu = 0;
        String sql = VentasCod.ACTUALIZAR_REGISTRO_VENTA2;
        try {
            ps = cn.prepareStatement(sql);
            
            ps.setString(1, String.valueOf(Double.parseDouble(OpcionesDeudas.precio_compra(descripcion))*Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))));//inversion
            
            //ganancia
            ps.setString(2,String.valueOf(String.valueOf((Integer.parseInt(precio_venta(descripcion))-Integer.parseInt(precio_compra(descripcion)))*Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))))); //ganancia
         // id
            ps.setString(3,id);
            ps.setString(4,descripcion);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
        }
        System.out.println(sql);
        return rsu;
    }
    
    
    
   
    
     public static String precio_compra(String descripcion) {
        String sql = "SELECT `precio_compra` FROM `alimentos` WHERE `nombre_al` = '"+descripcion+"'";
        String precio = "";
        int cant = 0;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cant = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OpcionesDeudas.class.getName()).log(Level.SEVERE, null, ex);
        }
        precio=String.valueOf(cant);
        return precio;
    }
     
     public static String total_precio_venta(String numero) {
        String sql = "SELECT sum(`inversion`)+sum(`ganancia`) as TOTAL FROM `registro_venta` WHERE `numero`='"+numero+"' ";
        String total = "";
        int temp_total = 0;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                temp_total = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OpcionesDeudas.class.getName()).log(Level.SEVERE, null, ex);
        }
        total=String.valueOf(temp_total);
        return total;
    }
     
     public static String precio_compra_ID(String codigo) {
        String sql = "SELECT `precio_compra` FROM `alimentos` WHERE `codigo_al` = '"+codigo+"'";
        String precio = "";
        int cant = 0;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cant = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OpcionesDeudas.class.getName()).log(Level.SEVERE, null, ex);
        }
        precio=String.valueOf(cant);
        return precio;
    }
     
     public static String precio_venta(String descripcion) {
        String sql = "SELECT `precio_venta` FROM `alimentos` WHERE `nombre_al` = '"+descripcion+"'";
        String precio = "";
        int cant = 0;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cant = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OpcionesDeudas.class.getName()).log(Level.SEVERE, null, ex);
        }
        precio=String.valueOf(cant);
        return precio;
    }
     
     public static String cantidad_producto_tablaDeudas(String numero, String descripcion) {
        String sql = "SELECT `cantidad` FROM registro_deuda WHERE numero='"+numero+"' and descripcion ='"+descripcion+"'";
        String cant_producto = "";
        int cant = 0;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cant = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OpcionesDeudas.class.getName()).log(Level.SEVERE, null, ex);
        }
        cant_producto=String.valueOf(cant);
        return cant_producto;
    }
     
      public static String cantidad_producto_tablaVentas(String numero, String descripcion) {
        String sql = "SELECT `cantidad` FROM `registro_venta` WHERE descripcion='"+descripcion+"' and numero ='"+numero+"'";
        String cant_producto = "";
        int cant = 0;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cant = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OpcionesDeudas.class.getName()).log(Level.SEVERE, null, ex);
        }
        cant_producto=String.valueOf(cant);
        return cant_producto;
    }
      
      
}
