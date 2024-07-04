package Controlador;

import Formatos.CentrarForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.*;
import Principal.*;
import DAO.DAO_Usuarios;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ControladorMenu implements ActionListener {
    frmPrincipal vista;
    String nombreUsuario;
    private DAO_Usuarios daoUsuarios; // Mover la declaración aquí

    public ControladorMenu(frmPrincipal prin, String nombreUsuario) {
        this.vista = prin;
        this.nombreUsuario = nombreUsuario;
        this.daoUsuarios = new DAO_Usuarios(); // Inicializar DAO_Usuarios
        vista.ResumenUsuario.addActionListener(this);
        vista.Registrar.addActionListener(this); 
        vista.AbrirReporte.addActionListener(this);
        vista.JMCategorias.addActionListener(this);
        vista.JMIngresosGastos.addActionListener(this);
        vista.JMNotificaciones.addActionListener(this);
        prin.setExtendedState(JFrame.MAXIMIZED_BOTH);
        prin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prin.setVisible(true);
        prin.setTitle("Aplicación Monetaria de Estudiantes");
    }

    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == vista.ResumenUsuario) {
        // Mostrar el resumen del usuario
        Main.res = new frmResumen();
        Main.controlres = new ControladorResumen(Main.res, nombreUsuario);
        Main.controlres.cargarGastos();
        vista.jdpnContenedor.add(Main.res);
        CentrarForma.CPanel(vista.jdpnContenedor, Main.res, 1);
    } else if (e.getSource() == vista.Registrar) {
        // Mostrar el formulario para registrar gastos extras
        Main.gasex = new frmRegistrarGastoExtra();
        Main.controlgasex = new ControladorRegistrarGastoExtra(Main.gasex, nombreUsuario);
        vista.jdpnContenedor.add(Main.gasex);
        CentrarForma.CPanel(vista.jdpnContenedor, Main.gasex, 3);
    } else if (e.getSource() == vista.AbrirReporte) {
        // Abrir el reporte
        int idUsuario = daoUsuarios.obtenerIdUsuario(nombreUsuario);
        Main.repor = new frmReporte();
        Main.controlrepor = new ControladorReporte(Main.repor, idUsuario);
        Main.controlrepor.abrirReportePDF(nombreUsuario);
        vista.jdpnContenedor.add(Main.repor);
        CentrarForma.CPanel(vista.jdpnContenedor, Main.repor, 2);
    } else if (e.getSource() == vista.JMNotificaciones) {
    int idUsuario = daoUsuarios.obtenerIdUsuario(nombreUsuario);
    Main.noti = new frmNotificaciones();
    Main.controlnoti = new ControladorNotificaciones(Main.noti, idUsuario); 
    Main.prin.jdpnContenedor.add(Main.noti);        
    CentrarForma.CPanel(Main.prin.jdpnContenedor, Main.noti, 2);
}
    else if (e.getSource() == vista.JMIngresosGastos) {
        // Mostrar el formulario de ingresos y gastos
        int idUsuario = daoUsuarios.obtenerIdUsuario(nombreUsuario);
        Main.ing = new frmIngresosGastos();
        Main.controling = new ControladorFrmIngresosGastos(Main.ing, idUsuario);
        vista.jdpnContenedor.add(Main.ing);          
        CentrarForma.CPanel(vista.jdpnContenedor, Main.ing, 2); 
    } else if (e.getSource() == vista.JMCategorias) {
        // Mostrar formulario de categorías si el usuario desea programar sus categorías
        String[] options = {"Si", "Regresar"};
        int choice = JOptionPane.showOptionDialog(
            null, 
            "¿Desea programar sus categorias?", 
            "Gestionar Categoría", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            options, 
            options[0]);

        if (choice == 0) { // Si elige "Si"
            int idUsuario = daoUsuarios.obtenerIdUsuario(nombreUsuario);
            Main.cat = new frmCategorias();
            Main.controlcat = new ControladorFrmCategorias(Main.cat, idUsuario); 
            vista.jdpnContenedor.add(Main.cat);        
            CentrarForma.CPanel(vista.jdpnContenedor, Main.cat, 2);
        } else if (choice == 1) { // Si elige "Regresar" o cualquier otra opción
            // Puedes agregar lógica adicional si es necesario
        }
    }
}

}