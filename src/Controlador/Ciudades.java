package Controlador;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Modelo.Persona;
/**
 *
 * @author Andres
 */
public class Ciudades {

    private Conexion con = null;
    private Connection cn = null;

    public Ciudades() {
        Conexion con = new Conexion();
        this.con = con;
        this.cn = con.getConnection();
        System.out.println("Conexion con: " + con + " Conexion cn: " + cn);
    }

    public Ciudades(Persona p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void InstertarCiudad(String nombre, int poblacion) {
        try {
            cn = con.getConnection();
            PreparedStatement pps = cn.prepareStatement("insert into ciudades values(null,'" + nombre + "'," + poblacion + ")");
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Ingreso correcto");
            con.Desconectar();
            con = null;
            cn = null;
            System.out.println("Conexion con: " + con + " Conexion cn: " + cn);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "fallo el ingreso: " + e);
            con.Desconectar();
            System.out.println("Conexion con: " + con + " Conexion cn: " + cn);
        }
    }

    public DefaultTableModel consultarCiudades() {
        try {
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
            con.Desconectar();
            return model;

        } catch (Exception e) {
            con.Desconectar();
            DefaultTableModel model = null;
            return model;
        }
    }
    public DefaultTableModel filtrarCiudades(String busqueda) {
        try {
            String[] titulos = {"ID", "Nombre", "Poblacion"};
            String query = "SELECT * FROM ciudades where nombre like  '%"+busqueda+"%'";
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
            con.Desconectar();
            return model;

        } catch (Exception e) {
            con.Desconectar();
            DefaultTableModel model = null;
            return model;
        }
    }

    public void ActuaizarCiudades(String nombre, String poblacion, String id) {
        try {
            
            String query = "update ciudades set nombre='" + nombre + "',poblacion='" + poblacion + "' where id_ciudad=" + id;
            PreparedStatement ps = cn.prepareStatement(query);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "registro actualizado con exito");
            con.Desconectar();
        } catch (Exception e) {
            con.Desconectar();
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }
    
        public void EliminarCiudad(String id) {
        try {
            String query = "Delete from ciudades where id_ciudad=" + id;
            PreparedStatement ps = cn.prepareStatement(query);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "registro eliminnado con exito");
            con.Desconectar();
        } catch (Exception e) {
            con.Desconectar();
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }


}
