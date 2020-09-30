/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deudas;

import ventas.*;
import java.util.Date;

/**
 *
 * @author Rojeru San CL
 */
public class DeudasCod {
    
    public static String LISTAR = "SELECT * FROM registro_deuda ORDER BY fecha";
    
    public static String BUSCA_PRECIO_COMPRA = "SELECT `precio_compra` FROM `alimentos` WHERE nombre_al =?";
    
    public static String BUSCA_PRECIO_VENTA = "SELECT `precio_venta` FROM `alimentos` WHERE `nombre_al` = ?";
    
    public static String BUSCA_CANTIDAD_PRODUCTO = "SELECT `cantidad_al` FROM `alimentos` WHERE `nombre_al` = ?";
    
    public static String REGISTRAR_DEUDA = "INSERT INTO registro_deuda(numero,titular, descripcion, cantidad, total, inversion, ganancia, fecha) "
            + "VALUES(?,?,?,?,?,?,?,?)";
    
    public static String REGISTRAR_numero_venta_y_fecha = "INSERT INTO registro_venta(numero, fecha) "
            + "VALUES(?,?)";
    
    public static String REGISTRAR2 = "INSERT INTO registro_venta(descripcion, cantidad, total, inversion, ganancia) "
            + "VALUES(?,?,?,?,?)";  
    
    public static String ELIMINAR = "DELETE FROM registro_deuda WHERE numero = ?";
    
    public static String ELIMINAR2 = "DELETE FROM registro_deuda WHERE numero = ? and `descripcion` = ?";  
    
    public static String ACTUALIZAR_CANTIDAD_INVERSION_GANANCIA_REGISTRO_DEUDA = "UPDATE registro_deuda SET cantidad=?, inversion=?, ganancia=? WHERE numero=? and descripcion=?"; 
    
    public static String ACTUALIZAR_TOTAL_TABLA_VENTAS = "UPDATE registro_venta SET total=? WHERE numero=? "; 
    
    public static String ELIMINAR_TODO = "DELETE FROM registro_deuda";
    
     public static String ACTUALIZAR = "UPDATE registro_venta SET cantidad=?, ganancia=?, inversion=? WHERE numero=? and descripcion=?"; //
     
     public static String ACTUALIZAR_EN_VENTAS = "UPDATE registro_ventas SET cantidad_al=?, ganancia=? WHERE numero=?";
     
     public static String ACTUALIZAR_GANANCIA_REGISTRO_DEUDA = "UPDATE registro_deuda SET cantidad=? WHERE codigo_al=? and descripcion=?";
      
      public static String ACTUALIZAR_TOTAL_REGISTRO_DEUDA = "UPDATE registro_deuda SET total=? WHERE numero=? ";
    
    
    private String primaryKey;
    private String total;
    private String fecha;
    private String descripcion;
    private String inversion;
    private String ganancia;
    private String cantidad;
    private String titular;

    public DeudasCod(){
        
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

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    

}
