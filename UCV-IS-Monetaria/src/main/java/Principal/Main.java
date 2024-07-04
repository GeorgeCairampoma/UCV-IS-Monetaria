package Principal;

import Vista.*;
import Controlador.*;

public class Main {
    public static frmLogin lg;
    public static ControladorLogin controllg;
    
    public static frmRegistroUsuario rg;
    public static ControladorRegistro controlrg;
    
    public static frmPrincipal prin;
    public static ControladorMenu controlMenu;
    
    public static frmCategorias cat;
    public static ControladorFrmCategorias controlcat;
    
    public static frmIngresosGastos ing;
    public static ControladorFrmIngresosGastos controling;
    
    public static frmResumen res;
    public static ControladorResumen controlres;
    
    public static frmRegistrarGastoExtra gasex;
    public static ControladorRegistrarGastoExtra controlgasex;
    
    public static frmReporte repor;
    public static ControladorReporte controlrepor;
    
    public static frmNotificaciones noti;
    public static ControladorNotificaciones controlnoti;
    
    public static void main(String[] args) {
        lg = new frmLogin();
        controllg = new ControladorLogin(lg);
    }
}