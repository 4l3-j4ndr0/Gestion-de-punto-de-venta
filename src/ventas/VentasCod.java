/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import java.util.Date;

/**
 *
 * @author Rojeru San CL
 */
public class VentasCod {
    
    public static String LISTAR = "SELECT * FROM registro_venta ORDER BY fecha";
    
    public static String BUSCA_PRECIO_COMPRA = "SELECT `precio_compra` FROM `alimentos` WHERE nombre_al =?";
    
    public static String BUSCA_PRECIO_VENTA = "SELECT `precio_venta` FROM `alimentos` WHERE `nombre_al` = ?";
    
    public static String BUSCA_CANTIDAD_PRODUCTO = "SELECT `cantidad_al` FROM `alimentos` WHERE `nombre_al` = ?";
    
    public static String REGISTRAR = "INSERT INTO registro_venta(numero, descripcion, cantidad, total, inversion, ganancia, fecha) "
            + "VALUES(?,?,?,?,?,?,?)";
    
    public static String REGISTRAR_numero_venta_y_fecha = "INSERT INTO registro_venta(numero, fecha) "
            + "VALUES(?,?)";
    
    public static String REGISTRAR2 = "INSERT INTO registro_venta(descripcion, cantidad, total, inversion, ganancia) "
            + "VALUES(?,?,?,?,?)";  
    
    public static String ELIMINAR = "DELETE FROM registro_venta WHERE numero = ?";
    
    public static String ELIMINAR2 = "DELETE FROM registro_venta WHERE numero = ? and `descripcion` = ?";  
    
    public static String ELIMINAR3 = "UPDATE registro_venta SET cantidad=? WHERE numero=? and descripcion=?"; 
    
    public static String ELIMINAR_TODO = "DELETE FROM `registro_venta`";
    
     public static String ACTUALIZAR = "UPDATE alimentos SET cantidad_al=?, ganancia=? WHERE nombre_al=?";
     
    
     
     public static String ACTUALIZAR2 = "UPDATE registro_deudas SET cantidad_al=?, ganancia=? WHERE nombre_al=?";
     
     public static String ACTUALIZAR_GANANCIA_ALIMENTOS = "UPDATE alimentos SET ganancia=? WHERE codigo_al=?";
     
      public static String ACTUALIZAR_REGISTRO_VENTA = "UPDATE registro_venta SET  total=? WHERE numero=?";
      
      public static String ACTUALIZAR_REGISTRO_VENTA2 = "UPDATE registro_venta SET inversion=?, ganancia=? WHERE numero=? and descripcion=?";
      public static String ACTUALIZAR_REGISTRO_VENTA3 = "UPDATE registro_venta SET total=? WHERE numero=? ";
    
    
    private String primaryKey;
    private String total;
    private String fecha;
    private String descripcion;
    private String inversion;
    private String ganancia;
    private String cantidad;

    public VentasCod(){
        
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGanancia() {
        return ganancia;
    }

    public void setGanancia(String ganancia) {
        this.ganancia = ganancia;
    }

    public String getInversion() {
        return inversion;
    }

    public void setInversion(String inversion) {
        this.inversion = inversion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

}
