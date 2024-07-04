package Controlador;

import Vista.*;
import DAO.*;
import Procesos.ProcesosFrmIngresosGastos;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorFrmIngresosGastos implements ActionListener {
    private frmIngresosGastos ventanaIngresosGastos;
    private DAO_IngresosGastos daoIngresosGastos;
    private int idUsuario; // Variable para almacenar el ID del usuario

    // Modificar el constructor para aceptar el ID del usuario como parámetro
    public ControladorFrmIngresosGastos(frmIngresosGastos ventanaIngresosGastos, int idUsuario) {
        this.ventanaIngresosGastos = ventanaIngresosGastos;
        this.daoIngresosGastos = new DAO_IngresosGastos(); // Instancia del DAO para manejar los datos
        this.idUsuario = idUsuario; // Almacenar el ID del usuario

        ventanaIngresosGastos.btnGuardar.addActionListener(this);
        ProcesosFrmIngresosGastos.Presentacion(ventanaIngresosGastos);
    }

@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == ventanaIngresosGastos.btnGuardar) {
        try {
            // Obtener los datos de la ventana
            double ingresos = Double.parseDouble(ventanaIngresosGastos.txtIngresos.getText());
            double gastos = Double.parseDouble(ventanaIngresosGastos.txtGastos.getText());

            // Validar que los valores sean números positivos
            if (ingresos < 0 || gastos < 0) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese valores válidos para ingresos y gastos.");
                return; // Salir del método si los valores no son válidos
            }

            // Guardar los datos en la base de datos, pasando el ID del usuario almacenado
            boolean guardado = daoIngresosGastos.guardarIngresosGastos(ingresos, gastos, idUsuario);

            // Mostrar mensaje de éxito o error
            if (guardado) {
                JOptionPane.showMessageDialog(null, "Datos ingresados correctamente");
                ventanaIngresosGastos.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar los datos de ingresos y gastos");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese valores numéricos válidos para ingresos y gastos.");
        }
    }
}

}



