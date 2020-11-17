package respaldo_BD;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
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
public class Restore {
conectar cc = new conectar();
     Connection con = cc.conexion();
    
    public void desc(String archivo, String destino) throws ZipException{
    try {
        // detener por completo la conexion (sin retorno)
        cc.conexion().createStatement().execute("shutdown");
         File uno = new File("BD/uruguay.mv.db");
          File dos = new File("BD/uruguay.trace.db");
          uno.delete();
          if(dos.exists()){
              dos.delete();
          }
        ZipFile zipe=new ZipFile(archivo);
        zipe.extractAll(destino);
        JOptionPane.showMessageDialog(null, "Respaldo cargado éxitosamente. La aplicación se cerrará automaticamente\nPor favor vuelva a abrir la aplicación para cargar los cambios correctamente.", "Restauración de Base de Datos", 0,
                         new ImageIcon(getClass().getResource("/imagenes/backup_bd_mje.png")));
        System.exit(0);
    } catch (SQLException ex) {
        Logger.getLogger(Restore.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public static void restartAplication() throws Exception {
       reiniciar r=new reiniciar();
       r.restart();
    }
}
