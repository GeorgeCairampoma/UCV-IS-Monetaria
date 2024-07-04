package DAO;

import Formatos.*;
import Vista.frmNotificaciones;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO_Categorias extends ConectarBD {

    public DAO_Categorias() {
        super();
    }

    // Método para agregar una nueva categoría a la base de datos utilizando un procedimiento almacenado
    public boolean agregarCategoria(String nombreCategoria, int usuarioID) {
        String procedureCall = "{call AgregarCategoria(?, ?, ?)}";
        try (CallableStatement cs = conexion.prepareCall(procedureCall)) {
            cs.setString(1, nombreCategoria);
            cs.setInt(2, usuarioID);
            cs.registerOutParameter(3, java.sql.Types.BOOLEAN);
            cs.execute();
            return cs.getBoolean(3); // Si la operación fue exitosa, retorna true
        } catch (SQLException e) {
            Mensajes.M1("Error al agregar categoría: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener el ID de una categoría por su nombre y el ID del usuario utilizando un procedimiento almacenado
    public int obtenerIdCategoriaPorNombre(String nombreCategoria, int usuarioID) {
        String procedureCall = "{call ObtenerIdCategoriaPorNombre(?, ?, ?)}";
        try (CallableStatement cs = conexion.prepareCall(procedureCall)) {
            cs.setString(1, nombreCategoria);
            cs.setInt(2, usuarioID);
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.execute();
            return cs.getInt(3);
        } catch (SQLException e) {
            Mensajes.M1("Error al obtener ID de categoría: " + e.getMessage());
            return -1;
        }
    }

    // Método para obtener las categorías de un usuario utilizando un procedimiento almacenado
    public List<String> obtenerCategoriasPorUsuario(int idUsuario) {
        List<String> categorias = new ArrayList<>();
        String procedureCall = "{call ObtenerCategoriasPorUsuario(?)}";
        try (CallableStatement cs = conexion.prepareCall(procedureCall)) {
            cs.setInt(1, idUsuario);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    categorias.add(rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            Mensajes.M1("Error al obtener categorías por usuario: " + e.getMessage());
        }
        return categorias;
    }
    

    // Método para eliminar una categoría
    public boolean eliminarCategoria(int idCategoria, int usuarioID) {
        String procedureCall = "{call EliminarCategoria(?, ?, ?)}";
        try (CallableStatement cs = conexion.prepareCall(procedureCall)) {
            cs.setInt(1, idCategoria);
            cs.setInt(2, usuarioID);
            cs.registerOutParameter(3, java.sql.Types.BOOLEAN);
            cs.execute();
            return cs.getBoolean(3); // Si la operación fue exitosa, retorna true
        } catch (SQLException e) {
            Mensajes.M1("Error al eliminar categoría: " + e.getMessage());
            return false;
        }
    }
}