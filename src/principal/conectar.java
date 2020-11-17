package principal;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/** 
 *
 * @author 4L3
 */

public class conectar {

   public static Connection conect = null;
    

    public Connection conexion() {
        try {
            // pa conectar sin tener instalado el xammp
            Class.forName("org.h2.Driver");
             conect = DriverManager.getConnection("jdbc:h2:./BD/uruguay","uruguay","uruguay");
 // pa conectar teniendo instalado el xammp
//           Class.forName("com.mysql.jdbc.Driver");
//             conect = DriverManager.getConnection("jdbc:mysql://localhost/uruguay", "uruguay", "uruguay"); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión" + e);
        }
        return conect;
    }  
    
}
