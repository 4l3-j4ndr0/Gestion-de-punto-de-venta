/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MAC;

/**
 *
 * @author 4L3
 */
import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JOptionPane;


public class mac {

    
     public ArrayList conseguirMAC() throws SocketException{
            final Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
       ArrayList a=new ArrayList();
    while (e.hasMoreElements()) {
        final byte [] mac = e.nextElement().getHardwareAddress();
        
        if (mac != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++)
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            a.add(sb.toString()+"\n");
            System.out.println(sb.toString());
            
        }
    }
    return a;
 }
}
