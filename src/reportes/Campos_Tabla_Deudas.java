/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

/**
 *
 * @author 4L3
 */
public class Campos_Tabla_Deudas {
    String titular,producto,cantidad,total,fecha;

    public Campos_Tabla_Deudas(String titular, String producto, String cantidad, String total, String fecha) {
        this.titular = titular;
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = total;
        this.fecha = fecha;
    }

    

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
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
    
    
}
