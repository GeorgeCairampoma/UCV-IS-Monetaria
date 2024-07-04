package Procesos;

import Vista.*;

/**
 *
 * @author George Cairampoma Hernandez
 */
public class ProcesosFrmResumen {

    public static void Presentacion(frmResumen frmResumen) {
        frmResumen.setTitle("Resumen Cliente");
        frmResumen.setVisible(true);
    }

    public static void Apagar(frmResumen frmResumen) {
        frmResumen.setVisible(false);
    }

}
