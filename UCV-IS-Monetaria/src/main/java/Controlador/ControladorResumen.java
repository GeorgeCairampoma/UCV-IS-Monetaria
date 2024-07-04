package Controlador;

import Vista.frmResumen;
import DAO.DAO_IngresosGastos;
import DAO.DAO_RegistrosGastos;
import DAO.DAO_Usuarios;
import Modelo.RegistroGasto;
import Procesos.ProcesosFrmResumen;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ControladorResumen {
    private frmResumen ventanaResumen;
    private DAO_IngresosGastos daoIngresosGastos;
    private DAO_RegistrosGastos daoRegistroGastos; 
    private DAO_Usuarios daoUsuarios;
    private String nombreUsuario;

    public ControladorResumen(frmResumen ventanaResumen, String nombreUsuario) {
        this.ventanaResumen = ventanaResumen;
        this.daoIngresosGastos = new DAO_IngresosGastos();
        this.daoRegistroGastos = new DAO_RegistrosGastos(); 
        this.daoUsuarios = new DAO_Usuarios();
        this.nombreUsuario = nombreUsuario;
        cargarDatosCliente();
        ProcesosFrmResumen.Presentacion(ventanaResumen);
    }
    
public void cargarGastos() {
    // Obtener la lista de gastos del usuario actual
    List<RegistroGasto> gastos = daoRegistroGastos.recuperarGastos(obtenerIdUsuario(nombreUsuario));

    // Limpiar la tabla actual
    DefaultTableModel model = (DefaultTableModel) ventanaResumen.jtblResumen.getModel();
    model.setRowCount(0);

    // Agregar los datos a la tabla
    for (RegistroGasto gasto : gastos) {
        model.addRow(new Object[]{
            gasto.getFecha().toString(), 
            gasto.getCategoria(), 
            gasto.getNombreProducto(), 
            (int) gasto.getCantidad(),  
            gasto.getPrecioUnitario()
        });
    }
}



    private void cargarDatosCliente() {
        // Obtener el ID del usuario actual usando el nombre de usuario
        int idUsuario = obtenerIdUsuario(nombreUsuario);

        // Consultar la base de datos para obtener los ingresos y gastos del usuario
        double[] ingresosYGastos = obtenerIngresosYGastos(idUsuario);

        // Mostrar el nombre del usuario en el JLabel de bienvenida
        ventanaResumen.jlbBienvenida.setText("Bienvenido: " + nombreUsuario + "!!");

        // Mostrar los ingresos y gastos en los JLabels correspondientes
        ventanaResumen.jlbIngresos.setText("Tus ingresos son los siguientes: $" + ingresosYGastos[0]);
        ventanaResumen.jlbGastos.setText("Tus gastos son los siguientes: $" + ingresosYGastos[1]);
        ventanaResumen.jlbTextotabla.setText("Estos son los gastos extras que vas haciendo:");

    }

    private int obtenerIdUsuario(String nombreUsuario) {
        return daoUsuarios.obtenerIdUsuario(nombreUsuario);
    }

    private double[] obtenerIngresosYGastos(int idUsuario) {
        return daoIngresosGastos.obtenerIngresosYGastos(idUsuario);
    }
    
    public double sueldoAhorro(String nombreUsuario) {
    // Obtener el ID del usuario actual usando el nombre de usuario
    int idUsuario = obtenerIdUsuario(nombreUsuario);

    // Consultar la base de datos para obtener los ingresos y gastos del usuario
    double[] ingresosYGastos = obtenerIngresosYGastos(idUsuario);

    // Calcular el saldo de ahorro
    double ingresos = ingresosYGastos[0];
    double gastos = ingresosYGastos[1];
    return ingresos - gastos;
}


}



