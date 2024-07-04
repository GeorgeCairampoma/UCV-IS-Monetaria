
package Procesos;

import Vista.frmRegistrarGastoExtra;

public class ProcesosFrmRegistrarGastoExtra {
        public static void Presentacion(frmRegistrarGastoExtra frmRegistrarGastoExtra) {
        frmRegistrarGastoExtra.setTitle("Ingreso de Gastos");
        frmRegistrarGastoExtra.setVisible(true);
    }

    public static void Apagar(frmRegistrarGastoExtra frmRegistrarGastoExtra) {
        frmRegistrarGastoExtra.setVisible(false);
    }
}
