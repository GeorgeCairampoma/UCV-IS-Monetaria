package Controlador;

import Vista.frmNotificaciones;
import DAO.DAO_IngresosGastos;
import DAO.DAO_RegistrosGastos;
import Procesos.ProcesosFrmNotificaciones;
import Modelo.RegistroGasto;
import javax.swing.JOptionPane;
import java.util.List;

public class ControladorNotificaciones {
    private frmNotificaciones vista;
    private int idUsuario; // ID del usuario actual
    private DAO_IngresosGastos daoIngresosGastos;
    private DAO_RegistrosGastos daoRegistrosGastos; // DAO para manejar los gastos registrados

    public ControladorNotificaciones(frmNotificaciones vista, int idUsuario) {
        this.vista = vista;
        this.idUsuario = idUsuario;
        this.daoIngresosGastos = new DAO_IngresosGastos(); // Instanciamos el DAO de ingresos y gastos
        this.daoRegistrosGastos = new DAO_RegistrosGastos(); // Instanciamos el DAO de registros de gastos

        // Llamamos al método de presentación de ProcesosFrmNotificaciones para mostrar el JInternalFrame
        ProcesosFrmNotificaciones.Presentacion(vista);

        // Actualizamos las notificaciones
        actualizarNotificaciones();
    }

    // Método para actualizar y mostrar las notificaciones en el JTextArea txaNotificaciones
    public void actualizarNotificaciones() {
        // Limpiamos el contenido del JTextArea
        vista.txaNotificaciones.setText("");

        // Obtenemos el saldo disponible y los gastos básicos
        double saldoDisponible = calcularSaldoDisponible();
        double gastosBasicos = obtenerGastosBasicos();

        // Calculamos los gastos registrados por el usuario
        double gastosRegistrados = daoIngresosGastos.obtenerGastosRegistrados(idUsuario);
        List<RegistroGasto> gastosExtras = daoRegistrosGastos.obtenerGastosPorUsuario(idUsuario);

        // Sumamos los gastos extras registrados por el usuario
        for (RegistroGasto gasto : gastosExtras) {
            gastosRegistrados += gasto.getCantidad() * gasto.getPrecioUnitario();
        }

        // Calculamos el saldo final restando los gastos básicos y los gastos registrados
        double saldoFinal = saldoDisponible - gastosBasicos - gastosRegistrados;

        // Generamos las notificaciones basadas en el saldo final
        String mensaje = generarMensajeNotificacion(saldoFinal);

        // Agregamos los gastos registrados al mensaje de notificación
        mensaje += "\n\nGastos Registrados:\n";
        mensaje += "---------------------------------\n";
        mensaje += String.format("Gastos Registrados: %.2f\n", gastosRegistrados);

        // Actualizamos el JTextArea con el mensaje de notificación
        vista.txaNotificaciones.setText(mensaje);
    }

    // Método para calcular el saldo disponible del usuario
    private double calcularSaldoDisponible() {
        double[] ingresosYGastos = daoIngresosGastos.obtenerIngresosYGastos(idUsuario);
        double ingresos = ingresosYGastos[0];
        double gastos = ingresosYGastos[1];
        return ingresos - gastos;
    }

    // Método para obtener los gastos básicos del usuario
    private double obtenerGastosBasicos() {
        // Aquí deberías implementar la lógica para obtener los gastos básicos del usuario
        // Puedes hacer una consulta a la base de datos o usar valores predefinidos
        return 1000.0; // Ejemplo de valor estático para los gastos básicos
    }

    // Método para generar el mensaje de notificación
    private String generarMensajeNotificacion(double saldoFinal) {
        if (saldoFinal >= 0) {
            return "Su presupuesto está en buen estado.";
        } else {
            return "Su presupuesto está en déficit. Considere ajustar sus gastos.";
        }
    }
}
