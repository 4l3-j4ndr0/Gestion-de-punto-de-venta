/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
public class OpcionesVen {

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
        String sql = VentasCod.ELIMINAR;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, id);
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
    
     public static int eliminar_tres(String id,String descripcion, String cantidad, boolean activo) {
        int rsu = 0;
        String sql = "";
            sql=VentasCod.ELIMINAR3;
        try {
            ps = cn.prepareStatement(sql);
            if(activo){
/*cantidad restando*/ ps.setString(1, String.valueOf((Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))-Integer.parseInt(cantidad))));
            }else{
/*cantidad sin restar*/ ps.setString(1, String.valueOf(Integer.parseInt(cantidad)));               
            }
            ps.setString(2, id);
            ps.setString(3, descripcion);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }

    public static int eliminaTodos() {
        int rsu = 0;
        String sql = VentasCod.ELIMINAR_TODO;
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
        DefaultTableModel modelo = (DefaultTableModel) ventas.RegistroVentas.tablaVentas.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (busca.equals("")) {
            sql = VentasCod.LISTAR;
        } else {
            sql = "SELECT * FROM registro_venta WHERE (numero like'" + busca + "%' or fecha='" + busca + "')"
                    + " ORDER BY fecha";
        }
        String datos[] = new String[7];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("numero");
                datos[1] = rs.getString("descripcion");
                datos[2] = rs.getString("cantidad");
                datos[3] = rs.getString("total");
                datos[4] = rs.getString("inversion");
                datos[5] = rs.getString("ganancia");
                datos[6] = rs.getString("fecha");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void listar_buscar(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) ventas.RegistroVentas.tablaVentas.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (busca.equals("")) {
            sql = VentasCod.LISTAR;
        } else {
            sql = "SELECT * FROM registro_venta WHERE (descripcion like'" + busca + "%' or fecha='" + busca + "' or numero like'" + busca + "%')"
                    + " ORDER BY fecha";
        }
        String datos[] = new String[7];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("numero");
                datos[1] = rs.getString("descripcion");
                datos[2] = rs.getString("cantidad");
                datos[3] = rs.getString("total");
                datos[4] = rs.getString("inversion");
                datos[5] = rs.getString("ganancia");
                datos[6] = rs.getString("fecha");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void listar_buscar_con_filtro(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) ventas.RegistroVentas.tablaVentas1.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (busca.equals("")) {
            sql = "SELECT `descripcion`,sum(`cantidad`) as CANTIDAD,sum(`ganancia`) + sum(`inversion`) as TOTAL,sum(`inversion`) as INVERSION,sum(`ganancia`) as GANANCIA FROM `registro_venta` group by `descripcion` ";
        } else {
            sql = "SELECT `descripcion`,sum(`cantidad`) as CANTIDAD,sum(`ganancia`) + sum(`inversion`) as TOTAL,sum(`inversion`) as INVERSION,sum(`ganancia`) as GANANCIA FROM `registro_venta` WHERE descripcion like'"+busca+"%' group by `descripcion`";
                   
        }
        String datos[] = new String[5];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("descripcion");
                datos[1] = rs.getString("cantidad");
                datos[2] = rs.getString("total");
                datos[3] = rs.getString("inversion");
                datos[4] = rs.getString("ganancia");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void listar_por_producto(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) ventas.RegistroVentas.tablaVentas1.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (busca.equals("TODOS")) {
            sql = "SELECT `descripcion`,sum(`cantidad`) as CANTIDAD,sum(`ganancia`) + sum(`inversion`) as TOTAL,sum(`inversion`) as INVERSION,sum(`ganancia`) as GANANCIA FROM `registro_venta` group by `descripcion` ";
        } else {
            sql = "SELECT `descripcion`,sum(`cantidad`) as CANTIDAD,sum(`ganancia`) + sum(`inversion`) as TOTAL,sum(`inversion`) as INVERSION,sum(`ganancia`) as GANANCIA FROM `registro_venta` where descripcion='"+busca+"' group by `descripcion` ";
        }
//         sql = "SELECT `descripcion`,sum(`cantidad`) as CANTIDAD,sum(`ganancia`) + sum(`inversion`) as TOTAL,sum(`inversion`) as INVERSION,sum(`ganancia`) as GANANCIA FROM `registro_venta` group by `descripcion` ";
        String datos[] = new String[5];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("descripcion");
                datos[1] = rs.getString("cantidad");
                datos[2] = rs.getString("total");
                datos[3] = rs.getString("inversion");
                datos[4] = rs.getString("ganancia");
                modelo.addRow(datos);
            }
//            for(int i=0;i<RegistroVentas.tablaVentas1.getRowCount();i++){
//           // ganancia(RegistroVentas.tablaVentas.getValueAt(i, 0).toString());
//                RegistroVentas.tablaVentas1.setValueAt(String.valueOf(Double.parseDouble(RegistroVentas.tablaVentas1.getValueAt(i, 1).toString())*Double.parseDouble(RegistroVentas.tablaVentas1.getValueAt(i, 4).toString())), i, 4);
//                RegistroVentas.tablaVentas1.setValueAt(String.valueOf(Double.parseDouble(RegistroVentas.tablaVentas1.getValueAt(i, 3).toString())-Double.parseDouble(RegistroVentas.tablaVentas1.getValueAt(i, 4).toString())), i, 3);
//                    }
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void get_combos_productos(JComboBox combo) {         
        try {
            
          // viga  
            String sql = "SELECT `descripcion` FROM `registro_venta` group by `descripcion` ";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            combo.addItem("TODOS");
            while (rs.next()) {
                combo.addItem(rs.getString("descripcion"));
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
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static ArrayList all_productos(){
        String dato="";
        ArrayList result=new ArrayList();
        //vendidos
        String SQL="SELECT `descripcion` FROM `registro_venta` group by `descripcion`  ";
        //no vendidos
        String SQL2="SELECT `nombre_al` FROM `alimentos` WHERE `nombre_al` not in (select descripcion from registro_venta) ";
        try {
            Statement st = cn.createStatement();
            
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                dato = rs.getString(1);
                result.add(dato);
            }
            
            ResultSet rs2 = st.executeQuery(SQL2);
            while (rs2.next()) {
                dato = rs2.getString(1);
                result.add(dato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static ArrayList get_all_productos_vendidos(){
        String dato="";
        ArrayList result=new ArrayList();
        String SQL="SELECT `descripcion` FROM `registro_venta` group by `descripcion`  ";
       
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                dato = rs.getString(1);
                result.add(dato.replace("[", "").replace("]", "").replace(",", ""));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static ArrayList get_all_productos_no_vendidos(){
        String dato="";
        ArrayList result=new ArrayList();
        String SQL="SELECT `nombre_al` FROM `alimentos` WHERE `nombre_al` not in (select descripcion from registro_venta) ";
       
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                dato = rs.getString(1);
                result.add(dato.replace("[", "").replace("]", "").replace(",", ""));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static int get_ventas(String producto){
        String dato="";
        int result=0;
        String SQL="SELECT sum(`cantidad`) as suma FROM `registro_venta` WHERE `descripcion`='"+producto+"'";
       
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                dato = rs.getString(1);
                if(dato==null){
                    result=0;
                }else{
                result=Integer.parseInt(dato);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static ArrayList lista_all_productos(ArrayList vendidos,ArrayList no_vendidos){
        ArrayList all=new ArrayList();
        for(int i=0;i<=no_vendidos.size()-1;i++){
            vendidos.add(no_vendidos.get(i).toString().replace("[", "").replace("]", "").replace(",", ""));
        }
        return all;
    }

    public static void numeros() {
        int j;
        int cont = 1;
        String num = "";
        String c = "";
        String c2 = "";
        String SQL = "SELECT MAX(numero) FROM registro_venta";
        String SQL2 = "SELECT MAX(numero) FROM registro_deuda";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }
            Statement st2 = cn.createStatement();
            ResultSet rs2 = st2.executeQuery(SQL2);
            if (rs2.next()) {
                c2 = rs2.getString(1);
            }
            System.out.println("C "+c);
            System.out.println("C2 "+c2);
            if (c == null&&c2==null) {
                ventas.CajaAd.numFac.setText("00000001");
            } else {
                if(c2!=null){
                    if(c==null){
                        j = Integer.parseInt(c2);
                    }else{
                if(Integer.parseInt(c)>Integer.parseInt(c2)){
                j = Integer.parseInt(c);
                }else{
                    j = Integer.parseInt(c2);
                }
                }
                }else{
                    j = Integer.parseInt(c);
                }
                GenerarNumero gen = new GenerarNumero();
                gen.generar(j);
                ventas.CajaAd.numFac.setText(gen.serie());
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
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
    
   public static int actualizar(String descripcion, String cantidad) {
        int rsu = 0;
        String sql = VentasCod.ACTUALIZAR;
        try {
            ps = cn.prepareStatement(sql);
            
/*cantidad*/      ps.setString(1, String.valueOf(Integer.parseInt(cantidad_producto(descripcion))+Integer.parseInt(cantidad)));
            
/*ganancia*/      ps.setString(2, String.valueOf((Integer.parseInt(precio_venta(descripcion))-Integer.parseInt(precio_compra(descripcion)))*(Integer.parseInt(cantidad_producto(descripcion))+Integer.parseInt(cantidad))));
            System.out.println("ganancia actualizada : "+String.valueOf((Integer.parseInt(precio_venta(descripcion))-Integer.parseInt(precio_compra(descripcion)))*(Integer.parseInt(cantidad_producto(descripcion))+Integer.parseInt(cantidad))));
            ps.setString(3, descripcion);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
        }
        System.out.println(sql);
        return rsu;
    }
   
   public static int actualizar_con_on_of(String descripcion, String cantidad, String id, boolean activo) {
        int rsu = 0;
        String sql = VentasCod.ACTUALIZAR;
        try {
            ps = cn.prepareStatement(sql);
            if(activo){
/*cantidad restando*/    ps.setString(1, String.valueOf(Integer.parseInt(cantidad_producto(descripcion))+Integer.parseInt(cantidad)));  
                System.out.println("cantidad producto de alimentos "+cantidad_producto(descripcion));
            }else{
/*cantidad sin restar*/      ps.setString(1, String.valueOf(Integer.parseInt(cantidad_producto(descripcion))+(Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))-Integer.parseInt(cantidad))));
            }
            if(activo){
/*ganancia restando*/      ps.setString(2, String.valueOf((Integer.parseInt(precio_venta(descripcion))-Integer.parseInt(precio_compra(descripcion)))*(Integer.parseInt(cantidad_producto(descripcion))+Integer.parseInt(cantidad))));
            }else{
/*ganancia sin restando*/      ps.setString(2, String.valueOf((Integer.parseInt(precio_venta(descripcion))-Integer.parseInt(precio_compra(descripcion)))*(Integer.parseInt(cantidad_producto(descripcion))+(Integer.parseInt(cantidad_producto_tablaVentas(id, descripcion))-Integer.parseInt(cantidad)))));                
            }
            ps.setString(3, descripcion);
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
    
    public static int actualizar_ganancia_alimentos( String descripcion,String codigo_al) {
        int rsu = 0;
        String sql = VentasCod.ACTUALIZAR_GANANCIA_ALIMENTOS;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,String.valueOf((Double.parseDouble(precio_venta(descripcion))-Double.parseDouble(precio_compra(descripcion)))*Double.parseDouble(cantidad_producto(descripcion)))); //total
            ps.setString(2, codigo_al);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
        }
        System.out.println(sql);
        return rsu;
    }
    
    public static int actualizar_y_recalcular_registro_venta2( String descripcion, String cantidad, String id) {
       
        int rsu = 0;
        String sql = VentasCod.ACTUALIZAR_REGISTRO_VENTA2;
        try {
            ps = cn.prepareStatement(sql);
            
            ps.setString(1, String.valueOf(Double.parseDouble(OpcionesVen.precio_compra(descripcion))*Integer.parseInt(cantidad_producto_tablaVentas(id,descripcion))));//inversion
            
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
    
    public static int actualizar_y_recalcular_total_registro_venta( String id) {
       
        int rsu = 0;
        String sql = VentasCod.ACTUALIZAR_REGISTRO_VENTA3;
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
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
        precio=String.valueOf(cant);
        return precio;
    }
     
     public static String cantidad_producto(String descripcion) {
        String sql = "SELECT `cantidad_al` FROM `alimentos` WHERE nombre_al ='"+descripcion+"'";
        String cant_producto = "";
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
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
        cant_producto=String.valueOf(cant);
        return cant_producto;
    }
}
