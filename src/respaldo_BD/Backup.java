package respaldo_BD;

//import alertas.CargandoBackup;
//import alertas.SuccessAlert;
//import alertas.WarningAlert;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import principal.conectar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 4L3
 */
public class Backup {

     conectar cc = new conectar();
     Connection con = cc.conexion();

    private String obtenerFecha() {
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String Fehca = dia + "-" + (mes + 1) + "-" + año;
        return Fehca;
    }

    public void exportDB(String nombreDB, String dir) {

         // String de ruta de carpeta
             String ruta = dir+"\\" + nombreDB + " " + obtenerFecha();
             // String de ruta de archivo
             String path = dir+"\\" + nombreDB + " " + obtenerFecha()+"\\" + nombreDB+".zip";
             // ruta carpeta
             File existe_ruta = new File(ruta);
             // ruta archivo
             File existe = new File(path);
             // pregunto si existe ruta de carpeta
             if (existe_ruta.exists()) {
                 if(existe.exists()){
                     // sobreescribo
                     generaBackup(path,nombreDB);
                 }else{
                     // escribo
                     generaBackup(path,nombreDB);
                 }
             } else {
                 // creo ruta de carpeta
                 existe_ruta.mkdir();
                 if(existe.exists()){
                     // sobreescribo
                     generaBackup(path,nombreDB);
                 }else{
                     // escribo
                     generaBackup(path,nombreDB);
                 }
             }
             
    }

    public void generaBackup(String respaldo, String nombreBD)  {
         try {
             // crear backup en directorio raiz de la aplicacion
             cc.conexion().createStatement().execute("backup to'"+nombreBD+".zip'");
             // detener por completo la conexion (sin retorno)
             //cc.conexion().createStatement().execute("shutdown");
             // rutas
             File origen = new File(nombreBD+".zip");
             File destino = new File(respaldo);
             // copiar
             try {
                 InputStream in = new FileInputStream(origen);
                 OutputStream out = new FileOutputStream(destino);
                 
                 byte[] buf = new byte[1024];
                 int len;
                 
                 while ((len = in.read(buf)) > 0) {
                     out.write(buf, 0, len);
                 }
                 in.close();
                 out.close();
                 // borrar archivo creado en la raiz de la aplicacion pa dejar solo el archivo guardado con una ruta dada
                 origen.delete();
                 JOptionPane.showMessageDialog(null, "Respaldo éxitoso.", "Respaldo de Base de Datos", 0,
                         new ImageIcon(getClass().getResource("/imagenes/backup_bd_mje.png")));
                 new conectar().conexion();
             } catch (IOException ioe) {
                 ioe.printStackTrace();
             }
         } catch (SQLException ex) {
             Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
