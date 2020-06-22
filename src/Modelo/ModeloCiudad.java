package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andres
 */
public class ModeloCiudad extends Conexion {

    public boolean InstertarCiudad(Ciudad ciu) {
        try {
            Connection cn = getConnection();
            PreparedStatement pps = cn.prepareStatement("insert into ciudades values(null,'" + ciu.getNombre() + "'," + ciu.getPoblacion() + ")");
            JOptionPane.showMessageDialog(null,pps.executeUpdate());
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "fallo el ingreso: " + e);
            return false;
        }
    }

    public boolean ActuaizarCiudades(String nombre, String poblacion, String id) {
        try {
            Connection cn = getConnection();
            String query = "update ciudades set nombre='" + nombre + "',poblacion='" + poblacion + "' where id_ciudad=" + id;
            PreparedStatement ps = cn.prepareStatement(query);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "registro actualizado con exito");
            return true;
        } catch (Exception e) {
            Desconectar();
            JOptionPane.showMessageDialog(null, "Error: " + e);
            return false;

        }
    }
        public void EliminarCiudad(String id) {
        try {
             Connection cn = getConnection();
            String query = "Delete from ciudades where id_ciudad=" + id;
            PreparedStatement ps = cn.prepareStatement(query);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "registro eliminnado con exito");
        } catch (Exception e) {
            Desconectar();
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }


    public DefaultTableModel consultarCiudades() {
        try {
            Connection cn = getConnection();
            String[] titulos = {"ID", "Nombre", "Poblacion"};
            String query = "SELECT * FROM ciudades";
            DefaultTableModel model = new DefaultTableModel(null, titulos);

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            String registros[] = new String[3];

            while (rs.next()) {
                registros[0] = rs.getString("id_ciudad");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("poblacion");
                model.addRow(registros);
            }
            return model;

        } catch (Exception e) {
            DefaultTableModel model = null;
            return model;
        }
    }

    public DefaultTableModel filtrarCiudades(String busqueda) {
        try {
            Connection cn = getConnection();
            String[] titulos = {"ID", "Nombre", "Poblacion"};
            String query = "SELECT * FROM ciudades where nombre like  '%" + busqueda + "%'";
            DefaultTableModel model = new DefaultTableModel(null, titulos);

            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery(query);
            String registros[] = new String[3];

            while (rs.next()) {
                registros[0] = rs.getString("id_ciudad");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("poblacion");
                model.addRow(registros);
            }

            return model;

        } catch (Exception e) {
            Desconectar();
            System.out.println(e);
            DefaultTableModel model = null;
            return model;
        }
    }

}
