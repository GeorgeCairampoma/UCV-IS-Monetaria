package DAO;

import Formatos.*;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DAO_Usuarios extends ConectarBD {

    public DAO_Usuarios() {
    }

    // Método para autenticar a un usuario utilizando procedimientos almacenados
    public boolean autenticarUsuario(String usuario, String contraseña) {
        try (CallableStatement cs = conexion.prepareCall("{call AutenticarUsuario(?, ?, ?)}")) {
            cs.setString(1, usuario);
            cs.setString(2, contraseña);
            cs.registerOutParameter(3, Types.BOOLEAN);
            cs.execute();
            return cs.getBoolean(3); // Si hay resultados, el usuario y la contraseña son correctos
        } catch (SQLException e) {
            Mensajes.M1("Error al autenticar usuario: " + e);
            return false;
        }
    }

    // Método para registrar un nuevo usuario utilizando procedimientos almacenados
    public boolean registrarUsuario(String usuario, String contraseña) {
        try (CallableStatement cs = conexion.prepareCall("{call RegistrarUsuario(?, ?, ?)}")) {
            cs.setString(1, usuario);
            cs.setString(2, contraseña);
            cs.registerOutParameter(3, Types.BOOLEAN);
            cs.execute();
            return cs.getBoolean(3); // Si el registro fue exitoso, retorna true
        } catch (SQLException e) {
            Mensajes.M1("Error al registrar usuario: " + e);
            return false;
        }
    }

    // Método para obtener el ID de un usuario por su nombre de usuario
    public int obtenerIdUsuario(String nombreUsuario) {
        int idUsuario = -1; // Valor predeterminado en caso de que no se encuentre el usuario
        try (CallableStatement cs = conexion.prepareCall("{call ObtenerIdUsuario(?, ?)}")) {
            cs.setString(1, nombreUsuario);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            idUsuario = cs.getInt(2);
        } catch (SQLException e) {
            Mensajes.M1("Error al obtener ID de usuario: " + e);
        }
        return idUsuario;
    }

    // Método para obtener el nombre de usuario por su ID de usuario
    public String obtenerNombreUsuario(int idUsuario) {
        String nombreUsuario = null;
        try (CallableStatement cs = conexion.prepareCall("{call ObtenerNombreUsuario(?, ?)}")) {
            cs.setInt(1, idUsuario);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();
            nombreUsuario = cs.getString(2);
        } catch (SQLException e) {
            Mensajes.M1("Error al obtener nombre de usuario: " + e);
        }
        return nombreUsuario;
    }
}
