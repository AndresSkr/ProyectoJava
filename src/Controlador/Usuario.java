package Controlador;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Modelo.Persona;

/**
 *
 * @author Andres
 */
public class Usuario {

    private Conexion con = null;
    private Connection cn = null;

    public Usuario() {
        Conexion con = new Conexion();
        this.con = con;
        this.cn = con.getConnection();
        System.out.println("Conexion con: " + con + " Conexion cn: " + cn);
    }

    public Persona Ingreso(String usuario, String clave) {
        try {
            Persona p = null;
            String query = "SELECT * FROM Usuarios where usuario= '" + usuario + "' ";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                int id = rs.getInt("idUsuario");
                String usuarioCons = rs.getString("usuario");
                String claveCons = rs.getString("clave");
                int cargoCons = rs.getInt("cargo");

                if (claveCons.equals(clave)) {
                    p = new Persona();
                    p.setCargo(cargoCons);
                    query = "SELECT * FROM Usuarios inner join empleados where fk_idUsuario=" + id;
                    rs = st.executeQuery(query);
                    if (rs.next()) {
                        p.setNombre(rs.getString("nombres"));
                        p.setApellido(rs.getString("apellidos"));
                        p.setTelefono(rs.getString("telefono"));
                        p.setCiudad(rs.getString("ciudad"));
                        p.setId(rs.getString("Id_empleado"));
                        //  JOptionPane.showMessageDialog(null,  p.toString());
                    }
                    st.close();
                    con.Desconectar();
                    return p;
                } else {
                    con.Desconectar();
                    st.close();
                    return p;
                }

            } else {
                con.Desconectar();
                st.close();
                return p;
            }

        } catch (Exception e) {
            con.Desconectar();
            System.out.println("Error: " + e);
            Persona p = null;
            return p;
        }

    }
}
