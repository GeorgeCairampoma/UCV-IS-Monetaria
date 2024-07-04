package Procesos;
import Modelo.*;
import Vista.frmRegistroUsuario;
/**
 *
 * @author George Cairampoma Hernandez
 */
public class ProcesosFrmRegistros {
    
    public static void PresentacionUsuario(frmRegistroUsuario r){
        r.setTitle("CREAR TU CUENTA");
        r.setVisible(true);
    }
    
    public static void LimpiarEntradasRegistro (frmRegistroUsuario rg){
        rg.txtUsuarioRegistro.setText("");
        rg.txtContraseñaRegistro.setText("");
        rg.txtContraseñaRegistro2.setText("");
        rg.txtUsuarioRegistro.requestFocus();
    }

    public static Registro LeerDatosRegistro(frmRegistroUsuario rg){
        Registro r = new Registro();
        r.setUsuario(rg.txtUsuarioRegistro.getText());
        r.setContraseña(rg.txtContraseñaRegistro.getText());
        r.setContraseñaex(rg.txtContraseñaRegistro2.getText());
        return r;
    }
}
