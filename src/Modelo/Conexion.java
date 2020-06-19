package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Andres
 */
public class Conexion {

    private static Connection conn;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost:3306/ejercicio1";

    public Conexion() {

        conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                  System.out.println("Conexion establecida");
            }

        } catch (Exception e) {
            System.out.println("error al connectar: "+e);
        }
    }
    
    public Connection getConnection(){
        return conn;
    }

public void Desconectar(){
    conn=null;
    if(conn==null){
        System.out.println("conexion terminada");
    }
}
    
}
