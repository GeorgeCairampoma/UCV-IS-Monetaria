package Procesos;

import Vista.frmNotificaciones;

public class ProcesosFrmNotificaciones {

    public static void Presentacion(frmNotificaciones frm) {
        frm.setTitle("Notificaciones");    
        frm.setVisible(true);
    }

    public static void Apagar(frmNotificaciones frm) {
        frm.setVisible(false);
    }

    public static void MostrarNotificacion(frmNotificaciones frm, String mensaje) {
        frm.txaNotificaciones.append(mensaje + "\n");
    }
}
