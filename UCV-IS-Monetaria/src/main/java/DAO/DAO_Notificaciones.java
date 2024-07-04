package DAO;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class DAO_Notificaciones extends ConectarBD {

    public DAO_Notificaciones() {
        super(); // Asegurarse de llamar al constructor de la clase base
    }

    // Método para obtener el sueldo mensual del usuario usando el procedimiento almacenado
    public double obtenerSueldoMensual(int idUsuario) {
        double sueldo = 0;
        try (CallableStatement cs = conexion.prepareCall("{call ObtenerSueldoMensual(?, ?)}")) {
            cs.setInt(1, idUsuario);
            cs.registerOutParameter(2, java.sql.Types.DOUBLE);
            cs.execute();
            sueldo = cs.getDouble(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sueldo;
    }

    // Método para obtener los gastos básicos del usuario usando el procedimiento almacenado
    public double obtenerGastosBasicos(int idUsuario) {
        double gastosBasicos = 0;
        try (CallableStatement cs = conexion.prepareCall("{call ObtenerGastosBasicos(?, ?)}")) {
            cs.setInt(1, idUsuario);
            cs.registerOutParameter(2, java.sql.Types.DOUBLE);
            cs.execute();
            gastosBasicos = cs.getDouble(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gastosBasicos;
    }

    // Método para calcular el presupuesto restante usando procedimientos almacenados
    public double calcularPresupuestoRestante(int idUsuario) {
        double sueldo = obtenerSueldoMensual(idUsuario);
        double gastosBasicos = obtenerGastosBasicos(idUsuario);
        double[] ingresosYGastosActuales = obtenerIngresosYGastos(idUsuario);
        double ingresosActuales = ingresosYGastosActuales[0];
        double gastosActuales = ingresosYGastosActuales[1];

        // Calcular el presupuesto restante
        double presupuestoRestante = sueldo - gastosBasicos - gastosActuales;

        return presupuestoRestante;
    }

    // Método para verificar si se ha superado el presupuesto usando procedimientos almacenados
    public boolean verificarPresupuestoSuperado(int idUsuario) {
        double presupuestoRestante = calcularPresupuestoRestante(idUsuario);
        return presupuestoRestante < 0;
    }

    // Método para obtener ingresos y gastos actuales usando procedimientos almacenados
    public double[] obtenerIngresosYGastos(int idUsuario) {
        double[] ingresosYGastos = new double[2];
        try (CallableStatement cs = conexion.prepareCall("{call ObtenerIngresosYGastos(?, ?, ?)}")) {
            cs.setInt(1, idUsuario);
            cs.registerOutParameter(2, java.sql.Types.DOUBLE);
            cs.registerOutParameter(3, java.sql.Types.DOUBLE);
            cs.execute();
            ingresosYGastos[0] = cs.getDouble(2); // Ingresos
            ingresosYGastos[1] = cs.getDouble(3); // Gastos
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingresosYGastos;
    }
}
