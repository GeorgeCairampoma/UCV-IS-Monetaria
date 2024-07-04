package DAO;

import java.sql.*;
import Formatos.*;

public class ConectarBD implements Parametros {
    protected Connection conexion;  
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;
    private ResultSetMetaData mdata;

    public ConectarBD() {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(RUTA, USUARIO, CLAVE);
            st = conexion.createStatement();
        } catch (ClassNotFoundException e) {
            Mensajes.M1("ERROR: No se encuentra el driver de la base de datos. " + e.getMessage());
        } catch (SQLException e) {
            Mensajes.M1("ERROR: No se puede conectar a la base de datos. " + e.getMessage());
        }
    }

    // Método para obtener la conexión
    public Connection getConnection() {
        return conexion;
    }

    // Método para cerrar la conexión
    public void close() {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (st != null && !st.isClosed()) {
                st.close();
            }
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            Mensajes.M1("ERROR: No se puede cerrar la conexión a la base de datos. " + e.getMessage());
        }
    }
}


