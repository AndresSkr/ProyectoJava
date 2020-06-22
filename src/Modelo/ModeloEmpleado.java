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
public class ModeloEmpleado extends Conexion {

    public boolean InstertarEmpleado(Persona p, String Usuario, String Pass) {
        try {
            Connection cn = getConnection();
            PreparedStatement pps = cn.prepareStatement("insert into usuarios values(null,'" + Usuario + "','" + Pass + "',"+p.getCargo()+")");
            pps.executeUpdate();
            
            String query = "SELECT * FROM usuarios where usuario = '"+Usuario+"'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            int idusu = 0;
            while (rs.next()) {
                idusu = rs.getInt("idUsuario");
            }
            PreparedStatement pps2 = cn.prepareStatement("insert into empleados values('" + p.getId() + "','" + p.getNombre() + "','" + p.getApellido() + "','" + p.getCiudad() + "'," + p.getTelefono() + ","+idusu+")");
            pps2.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "fallo el ingreso: " + e);
            return false;
        }
    }

    public boolean ActuaizarEmpleado(String id, String nombre, String apellidos,String ciudad,String telefono) {
        try {
            Connection cn = getConnection();
            String query = "update empleados set nombres='" + nombre + "',apellidos='" + apellidos+ "',ciudad='" + ciudad + "',telefono='" + telefono+ "' where Id_empleado=" + id;
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

    public void EliminarEmpleado(String id) {
        try {
            Connection cn = getConnection();
            String query = "Delete from usuarios where idUsuario=" + id;
            PreparedStatement ps = cn.prepareStatement(query);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "registro eliminnado con exito");
        } catch (Exception e) {
            Desconectar();
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }

    public DefaultTableModel consultarEmpleado() {
        try {
            Connection cn = getConnection();
            String[] titulos = {"ID", "Nombres", "Apellidos","Ciudad","Telefono","Id del usuario"};
            String query = "SELECT * FROM Empleados";
            DefaultTableModel model = new DefaultTableModel(null, titulos);

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            String registros[] = new String[6];
            while (rs.next()) {
                registros[0] = rs.getString("Id_empleado");
                registros[1] = rs.getString("nombres");
                registros[2] = rs.getString("apellidos");
                registros[3] = rs.getString("ciudad");
                registros[4] = rs.getString("telefono");
                registros[5] = rs.getString("fk_IdUsuario");
                model.addRow(registros);
            }
            return model;

        } catch (Exception e) {
            DefaultTableModel model = null;
            return model;
        }
    }

    public DefaultTableModel filtrarEmpleados(String busqueda) {
        try {
            Connection cn = getConnection();
             String[] titulos = {"ID", "Nombres", "Apellidos","Ciudad","Telefono"};
            String query = "SELECT * FROM empleados where nombres like  '%" + busqueda + "%'";
            DefaultTableModel model = new DefaultTableModel(null, titulos);

            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery(query);
            String registros[] = new String[5];

            while (rs.next()) {
                 registros[0] = rs.getString("Id_empleado");
                registros[1] = rs.getString("nombres");
                registros[2] = rs.getString("apellidos");
                registros[3] = rs.getString("ciudad");
                registros[4] = rs.getString("telefono");
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
