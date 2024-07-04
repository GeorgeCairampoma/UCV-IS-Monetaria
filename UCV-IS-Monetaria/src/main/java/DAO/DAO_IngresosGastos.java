package DAO;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class DAO_IngresosGastos extends ConectarBD {

    public DAO_IngresosGastos() {
        super(); // Asegurarse de llamar al constructor de la clase base
    }

    // Método para obtener solo los gastos del usuario
    public double obtenerGastosRegistrados(int idUsuario) {
        double[] ingresosYGastos = obtenerIngresosYGastos(idUsuario);
        return ingresosYGastos[1]; // Devuelve solo los gastos
    }

    // Método para guardar ingresos y gastos utilizando un procedimiento almacenado
    public boolean guardarIngresosGastos(double ingresos, double gastos, int idUsuario) {
        try (CallableStatement cs = conexion.prepareCall("{call GuardarIngresosGastos(?, ?, ?, ?)}")) {
            cs.setDouble(1, ingresos);
            cs.setDouble(2, gastos);
            cs.setInt(3, idUsuario);
            cs.registerOutParameter(4, java.sql.Types.BOOLEAN); // Parámetro de salida para éxito/fallo
            cs.execute();
            return cs.getBoolean(4); // Retorna true si la operación fue exitosa, false si no
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para modificar ingresos y gastos utilizando un procedimiento almacenado
    public boolean modificarIngresosGastos(int idUsuario, double nuevosIngresos, double nuevosGastos) {
        try (CallableStatement cs = conexion.prepareCall("{call ModificarIngresosGastos(?, ?, ?, ?)}")) {
            cs.setDouble(1, nuevosIngresos);
            cs.setDouble(2, nuevosGastos);
            cs.setInt(3, idUsuario);
            cs.registerOutParameter(4, java.sql.Types.BOOLEAN); // Parámetro de salida para éxito/fallo
            cs.execute();
            return cs.getBoolean(4); // Retorna true si la operación fue exitosa, false si no
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener ingresos y gastos utilizando un procedimiento almacenado
    public double[] obtenerIngresosYGastos(int idUsuario) {
        double[] ingresosYGastos = new double[2];
        try (CallableStatement cs = conexion.prepareCall("{call ObtenerIngresosYGastos(?, ?, ?)}")) {
            cs.setInt(1, idUsuario);
            cs.registerOutParameter(2, java.sql.Types.DECIMAL);
            cs.registerOutParameter(3, java.sql.Types.DECIMAL);
            cs.execute();
            ingresosYGastos[0] = cs.getDouble(2);
            ingresosYGastos[1] = cs.getDouble(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingresosYGastos;
    }
}
