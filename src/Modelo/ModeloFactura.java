package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ModeloFactura extends Conexion {
    
   private int idFactura=0;

    public boolean InsertarFactura(Persona p, ArrayList<Producto> vectorProductos) {
        try {
            Connection cn = getConnection();

            PreparedStatement pps = cn.prepareStatement("insert into detalleventas values(null,"
                    + vectorProductos.get(0).getId() + ",'"
                    + p.getId() + "',"
                    + vectorProductos.get(0).getCantidad()
                    + "," + vectorProductos.get(0).getPrecio() + ")");
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null,"se ingreso el primer registro");
            vectorProductos.remove(0);

            String query = "SELECT * FROM detalleventas ORDER BY id_Detalle DESC LIMIT 1";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                 idFactura = rs.getInt("id_Detalle");
            }

            for (Producto listaProd : vectorProductos) {
                PreparedStatement pps2 = cn.prepareStatement("insert into detalleventas values("+idFactura+","
                        + listaProd.getId() + ",'"
                        + p.getId() + "',"
                        + listaProd.getCantidad()
                        + "," + listaProd.getPrecio() + ")");
                pps2.executeUpdate();
            }
            return true;
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "fallo el ingreso: " + e);
            return false;
        }
    }
    
    public int getfactura(){
        return idFactura;
    }

}
