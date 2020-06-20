package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Andres
 */
public class ModeloCiudad extends Conexion{

    public boolean InstertarCiudad(Ciudad ciu) {
        try {
            Connection cn = getConnection();
            PreparedStatement pps = cn.prepareStatement("insert into ciudades values(null,'" + ciu.getNombre()+ "'," + ciu.getPoblacion() + ")");
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Ingreso correcto");
            Desconectar();
           return  true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "fallo el ingreso: " + e);
            return  false;
        }
    }

}
