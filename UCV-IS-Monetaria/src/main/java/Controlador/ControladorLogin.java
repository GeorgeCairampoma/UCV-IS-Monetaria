package Controlador;

import Vista.*;
import DAO.*;
import Procesos.*;
import Modelo.Registro;
import Principal.Main;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ControladorLogin implements ActionListener {
    frmLogin vista;
    DAO_Usuarios crud;
    Registro c;

    public ControladorLogin(frmLogin l) {
        vista = l;

        // Asociar ActionListener al botón "Ingresar"
        vista.btnIngresar.addActionListener(this);

        // Asociar MouseListener al label "Regístrate"
        vista.jlblRegistrate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Mostrar la ventana de registro de usuarios y crear su controlador
                Main.rg = new frmRegistroUsuario();
                Main.controlrg = new ControladorRegistro(Main.rg);
                // Ocultar la ventana de inicio de sesión
                vista.setVisible(false);
            }
        });

        ProcesosFrmLogin.PresentacionLogin(l);

        // Centrar la ventana de inicio de sesión
        centrarVentana(vista);
    }

    private void centrarVentana(JFrame frame) {
        // Obtener la dimensión de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Obtener el ancho y alto de la ventana
        int frameWidth = frame.getSize().width;
        int frameHeight = frame.getSize().height;
        // Calcular la posición x, y para centrar la ventana
        int x = (screenSize.width - frameWidth) / 2;
        int y = (screenSize.height - frameHeight) / 2;
        // Establecer la posición de la ventana
        frame.setLocation(x, y);
    }

    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == vista.btnIngresar) {
        // Realizar la autenticación del usuario
        c = ProcesosFrmLogin.LeerLogin(vista);
        crud = new DAO_Usuarios();
        boolean autenticado = crud.autenticarUsuario(c.getUsuario(), c.getContraseña());
        if (autenticado) {
            // Si la autenticación es exitosa, mostramos la ventana principal
            vista.setVisible(false); // Ocultamos la ventana de inicio de sesión
            // Creamos e inicializamos la ventana principal
            Main.prin = new frmPrincipal();
            // Pasamos el nombre de usuario al controlador del menú
            Main.controlMenu = new ControladorMenu(Main.prin, c.getUsuario());

            // Cargar los gastos del usuario
            Main.res = new frmResumen();
            Main.controlres = new ControladorResumen(Main.res, c.getUsuario());
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
        }
    }
}


}





