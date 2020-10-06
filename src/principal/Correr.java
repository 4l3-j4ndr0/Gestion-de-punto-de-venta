/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import MAC.mac;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import productos.AlimentosCod;
import productos.OpcionesAl;
import static java.awt.image.ImageObserver.ERROR;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import static principal.SplashScreen.cn;

/**
 *
 * @author 4L3
 */
public class Correr {

    static conectar cc = new conectar();
    static Connection con = cc.conexion();
    static PreparedStatement ps;

    public static int registrar_mac(mac m) throws SocketException {
        int rsu = 0;
        String sql = AlimentosCod.REGISTRAR_MAC;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,String.valueOf( m.conseguirMAC()));
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }

    public static int registrar_intento() {
        int rsu = 0;
        String sql = "INSERT INTO `intentos`(`numero`) VALUES (?)";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,ultimo_intento()+1);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }
    
    public static int tabla_mac_vacia() {
        String c = null;
        String sql = "SELECT count(*) FROM mac";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                c = rs.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Correr.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(sql);
        return Integer.parseInt(c);
    }

    public static boolean existe_mac(String mac) {
        String c = null;
        boolean existe = false;
        String sql = "SELECT * FROM `mac` WHERE `mac`='" + mac + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                c = rs.getString(1);
            }
            if (c != null) {
                existe = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Correr.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
    
    public static int ultimo_intento() {
        String c = null;
        int intento = 0;
        String sql = "SELECT * FROM `intentos` ORDER BY numero DESC ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                c = rs.getString(1);
            }
            if (c != null) {
                intento = Integer.parseInt(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Correr.class.getName()).log(Level.SEVERE, null, ex);
        }
        return intento;
    }

    public static int limpiar_tabla_mac() {
        int rsu = 0;
        String sql = "DELETE FROM mac";
        try {
            ps = cn.prepareStatement(sql);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en en Look and Feel", "Error", ERROR);
                }
                mac m = new mac();
             /*   if(ultimo_intento()<30){
                    registrar_intento();
                    new SplashScreen().setVisible(true); */
                if (tabla_mac_vacia() != 0) {
                    try {
                        if (existe_mac(m.conseguirMAC().toString())) {
                            new SplashScreen().setVisible(true);
                        } else {
                            String[] opciones = {"Continuar como desarrollador", "Entiendo"};
                            int opcion = JOptionPane.showOptionDialog(null, "Usted no ha pagado por el software o lo esta utilizando en una PC distinta, en\n"
                                    + "cualquiera de los dos casos, si desea continuar utilizando el software, contacte con el desarrollador.\n"
                                    + "Telefono celular: 55801933\n"
                                    + "Telefono fijo: 48522730", "Control de Distribución", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, new ImageIcon(getClass().getResource("/imagenes/policia-imagen-animada-0017.gif")), opciones, 1);
                            switch (opcion) {
                                case 0:
                                    JPanel panel = new JPanel();
                                    JLabel label = new JLabel("Enter a password:");
                                    JPasswordField pass = new JPasswordField(15);
                                    panel.add(label);
                                    panel.add(pass);
                                    
                                    String[] options = new String[]{"OK", "Cancel"};
                                    int option = JOptionPane.showOptionDialog(null, panel, "Control de Distribución",
                                            JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                            new ImageIcon(getClass().getResource("/imagenes/policia_buscando.gif")), options, options[1]);
                                    if (option == 0) // pressing OK button
                                    {
                                        String contraseña = String.valueOf(pass.getPassword());
                                        if(contraseña.equals("meracoz5adrian..1255801933")){
                                            limpiar_tabla_mac();
                                            registrar_mac(m);
                                            new SplashScreen().setVisible(true);
                                        }else{
                                            JOptionPane.showMessageDialog(null,"Contraseña incorrecta","Error",JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                    break;
                                case 1:
                                    break;
                                default:
                                    break;
                            }
                        }
                    } catch (SocketException ex) {
                        Logger.getLogger(Correr.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        registrar_mac(m);
                        new SplashScreen().setVisible(true);
                    } catch (SocketException ex) {
                        Logger.getLogger(Correr.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
          /*  }else{
                    JOptionPane.showMessageDialog(null, "Su version de prueba de 30 usos ha expirado.\nContacte con el desarrollador del software para adquirir la versión completa", "Información", JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon(getClass().getResource("/imagenes/policia-imagen-animada-0017.gif")));
                }*/
                  
            }
        });
    }
}
