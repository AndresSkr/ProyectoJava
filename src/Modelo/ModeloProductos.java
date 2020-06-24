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
public class ModeloProductos extends Conexion {

    public boolean InstertarProductos(Producto Prod) {
        try {
            Connection cn = getConnection();
            PreparedStatement pps = cn.prepareStatement("insert into productos values(null,'" + Prod.getNombre() + "'," + Prod.getPrecio() + "," + Prod.getCantidad() + ")");
            pps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "fallo el ingreso: " + e);
            return false;
        }
    }

    public boolean ActuaizarProductos(String nombre, float precio, String id, int cantidad) {
        try {
            Connection cn = getConnection();
            String query = "update productos set nombreProducto='" + nombre + "',precio=" + precio + ", cantidad =" + cantidad + "  where id_Producto =" + id;
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

    public void EliminarProducto(String id) {
        try {
            Connection cn = getConnection();
            String query = "Delete from productos where id_Producto=" + id;
            PreparedStatement ps = cn.prepareStatement(query);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "registro eliminado con exito");
        } catch (Exception e) {
            Desconectar();
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }

    public DefaultTableModel consultarProductos() {
        try {
            Connection cn = getConnection();
            String[] titulos = {"ID", "Nombre", "Precio", "Cantidad"};
            String query = "SELECT * FROM productos";
            DefaultTableModel model = new DefaultTableModel(null, titulos) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            };

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            String registros[] = new String[4];

            while (rs.next()) {
                registros[0] = rs.getString("id_Producto");
                registros[1] = rs.getString("nombreProducto");
                registros[2] = rs.getString("precio");
                registros[3] = rs.getString("cantidad");
                model.addRow(registros);
            }
            return model;

        } catch (Exception e) {
            DefaultTableModel model = null;
            return model;
        }
    }

    public DefaultTableModel filtrarProductos(String busqueda) {
        try {
            Connection cn = getConnection();
            String[] titulos = {"ID", "Nombre", "Precio", "Cantidad"};
            String query = "SELECT * FROM productos where nombreProducto like  '%" + busqueda + "%'";
            DefaultTableModel model = new DefaultTableModel(null, titulos) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            };

            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery(query);
            String registros[] = new String[4];

            while (rs.next()) {
                registros[0] = rs.getString("id_Producto");
                registros[1] = rs.getString("nombreProducto");
                registros[2] = rs.getString("precio");
                registros[3] = rs.getString("cantidad");
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
