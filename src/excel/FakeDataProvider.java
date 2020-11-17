package excel;

import java.sql.Connection;
import principal.conectar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data provider for excel
 *
 * @author angel
 */
public final class FakeDataProvider {

    static conectar cc = new conectar();
    static Connection cn = cc.conexion();
    static PreparedStatement ps;

    /**
     * Return the columns name for the table
     */
    public static List<String> getTableHeaders() {
        List<String> tableHeader = new ArrayList<String>();
        tableHeader.add("CÓDIGO");
        tableHeader.add("TIPO PRODUCTO");
        tableHeader.add("PRODUCTO");
        tableHeader.add("CANTIDAD");
        tableHeader.add("PRECIO COMPRA");
        tableHeader.add("PRECIO VENTA");
        tableHeader.add("GANANCIA");

        return tableHeader;
    }

    /**
     * Return values for the table
     *
     * @param numberOfRows Number of rows we want to receive
     *
     * @return Values
     */
    public static List<List<String>> getTableContent(int numberOfRows) {
        try {
            if (numberOfRows <= 0) {
                throw new IllegalArgumentException("The number of rows must be a positive integer");
            }

            List<List<String>> tableContent = new ArrayList<List<String>>();

            String SQL = "SELECT * FROM alimentos";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            int i = 0;
            List<String> row = null;
            while (rs.next()) {
                tableContent.add(row = new ArrayList<String>());

                row.add(rs.getString("codigo_al"));
                row.add(rs.getString("tipo_al"));
                row.add(rs.getString("nombre_al"));
                row.add(rs.getString("cantidad_al"));
                row.add(rs.getString("precio_compra"));
                row.add(rs.getString("precio_venta"));
                row.add(rs.getString("ganancia"));

                i++;
            }
            return tableContent;
        } catch (SQLException ex) {
            Logger.getLogger(FakeDataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
