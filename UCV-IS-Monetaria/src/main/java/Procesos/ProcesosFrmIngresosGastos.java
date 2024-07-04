
package Procesos;

import Vista.*;

/**
 *
 * @author George Cairampoma Hernandez
 */
public class ProcesosFrmIngresosGastos {

    public static void Presentacion(frmIngresosGastos frmIngresosGastos) {
        frmIngresosGastos.setTitle("Ingreso de Gastos");
        frmIngresosGastos.setVisible(true);
    }

    public static void Apagar(frmIngresosGastos frmIngresosGastos) {
        frmIngresosGastos.setVisible(false);
    }

public static void LimpiarEntradas(frmIngresosGastos frmIngresosGastos) {
    frmIngresosGastos.txtGastos.setText("");
    frmIngresosGastos.txtIngresos.setText("");
}

}

