package Procesos;
import Modelo.Registro;
import Vista.frmLogin;
/**
 *
 * @author George Cairampoma Hernandez
 */
public class ProcesosFrmLogin {
    
    public static void PresentacionLogin(frmLogin f){
        f.setTitle("INICIAR SESION");
        f.setVisible(true);
    }
    
    public static void LimpiarEntradasLogin (frmLogin L){
        L.txtUsuario.setText(" ");
        L.txtContraseña.setText(" ");
        L.txtUsuario.requestFocus();
    }
    
    public static Registro LeerLogin(frmLogin lg){
        Registro r = new Registro();
        r.setUsuario(lg.txtUsuario.getText());
        r.setContraseña(lg.txtContraseña.getText());
        return r;
    }
}