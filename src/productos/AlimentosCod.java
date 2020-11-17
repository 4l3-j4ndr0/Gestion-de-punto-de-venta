/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import javax.print.DocFlavor;

/**
 *
 * @author 4L3
 */
public class AlimentosCod {
    
    public static String LISTAR = "SELECT * FROM alimentos ORDER BY nombre_al";
    
    public static String REGISTRAR = "INSERT INTO alimentos(codigo_al, tipo_al, nombre_al, cantidad_al, precio_compra, precio_venta,ganancia,stock_minimo, stock_maximo) "
            + "VALUES(?,?,?,?,?,?,?,?,?)";
    
    public static String REGISTRAR_MAC = "INSERT INTO mac(mac) "
            + "VALUES(?)";
    
    public static String REGISTRAR2 = "INSERT INTO alimentos(codigo_al, tipo_al, nombre_al, precio_compra, precio_venta, ganancia) "
            + "VALUES(?,?,?,?,?,?)";
    
    public static String ACTUALIZAR = "UPDATE alimentos SET tipo_al=?, nombre_al=?, cantidad_al=?, precio_compra=?, precio_venta=?, ganancia=? , stock_minimo=? , stock_maximo=? WHERE codigo_al=?";
    
    public static String ELIMINAR = "DELETE FROM alimentos WHERE codigo_al = ?";
    
    public static String ELIMINAR_POR_TIPO = "DELETE FROM alimentos WHERE tipo_al = ?";
    
    public static String ELIMINAR_TODO = "DELETE FROM alimentos";
    
    private String primaryKey;
    private String tipoal;
    private String nombre;
    private String cantidad;
    private String precio_compra;
    private String precio_venta;
    private String stock_minimo;
    private String stock_maximo;
     
    public AlimentosCod(){
        
    }
    
    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getTipoal() {
        return tipoal;
    }

    public void setTipoal(String tipoal) {
        this.tipoal = tipoal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(String precio_compra) {
        this.precio_compra = precio_compra;
    }

    public String getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(String precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getStock_minimo() {
        return stock_minimo;
    }

    public void setStock_minimo(String stock_minimo) {
        this.stock_minimo = stock_minimo;
    }

    public String getStock_maximo() {
        return stock_maximo;
    }

    public void setStock_maximo(String stock_maximo) {
        this.stock_maximo = stock_maximo;
    }

    
    
}
