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
public class Campo_tabla_deudas_agrupado {
    String titular,total;

    public Campo_tabla_deudas_agrupado(String titular, String total) {
        this.titular = titular;
        
        this.total = total;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}
