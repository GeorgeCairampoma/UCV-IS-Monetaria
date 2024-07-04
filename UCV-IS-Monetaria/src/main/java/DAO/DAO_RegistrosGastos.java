package DAO;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import Modelo.RegistroGasto;
import java.sql.ResultSet;
import java.time.LocalDate;

public class DAO_RegistrosGastos extends ConectarBD {

    public DAO_RegistrosGastos() {
    }

    // Método para registrar un gasto utilizando un procedimiento almacenado
    public boolean registrarGasto(double cantidad, double precioUnitario, String fecha, int categoriaId, int usuarioId, String nombreProducto) {
        try (CallableStatement cs = conexion.prepareCall("{call RegistrarGasto(?, ?, ?, ?, ?, ?, ?)}")) {
            cs.setDouble(1, cantidad);
            cs.setDouble(2, precioUnitario);
            cs.setString(3, fecha);
            cs.setInt(4, categoriaId);
            cs.setInt(5, usuarioId);
            cs.setString(6, nombreProducto);
            cs.registerOutParameter(7, Types.BOOLEAN);
            cs.execute();
            return cs.getBoolean(7);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener los gastos de un usuario utilizando un procedimiento almacenado
    public List<RegistroGasto> obtenerGastosPorUsuario(int usuarioId) {
        List<RegistroGasto> gastos = new ArrayList<>();
        try (CallableStatement cs = conexion.prepareCall("{call ObtenerGastosPorUsuario(?)}")) {
            cs.setInt(1, usuarioId);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    RegistroGasto gasto = new RegistroGasto();
                    gasto.setId(rs.getInt("id"));
                    gasto.setCantidad(rs.getDouble("cantidad"));
                    gasto.setPrecioUnitario(rs.getDouble("precio_unitario"));
                    gasto.setFecha(rs.getDate("fecha").toLocalDate());
                    gasto.setCategoria(rs.getString("categoria"));
                    gasto.setUsuarioId(rs.getInt("usuario_id"));
                    gasto.setNombreProducto(rs.getString("nombreproducto"));
                    gastos.add(gasto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gastos;
    }

    // Método para recuperar los gastos de un usuario utilizando un procedimiento almacenado
    public List<RegistroGasto> recuperarGastos(int usuarioId) {
        List<RegistroGasto> gastos = new ArrayList<>();
        try (CallableStatement cs = conexion.prepareCall("{call RecuperarGastos(?)}")) {
            cs.setInt(1, usuarioId);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
                    String categoria = rs.getString("categoria");
                    double cantidad = rs.getDouble("cantidad");
                    double precioUnitario = rs.getDouble("precio_unitario");
                    String nombreProducto = rs.getString("nombreproducto");
                    RegistroGasto gasto = new RegistroGasto();
                    gasto.setFecha(fecha);
                    gasto.setCategoria(categoria);
                    gasto.setCantidad(cantidad);
                    gasto.setPrecioUnitario(precioUnitario);
                    gasto.setNombreProducto(nombreProducto);
                    gastos.add(gasto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gastos;
    }

}
