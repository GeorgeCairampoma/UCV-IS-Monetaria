package Controlador;

import DAO.*;
import Formatos.CentrarForma;
import Modelo.Registro;
import Procesos.*;
import Vista.*;
import Principal.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ControladorRegistro implements ActionListener {
    frmRegistroUsuario vista;
    DAO_Usuarios crud;
    Registro reg;
    
    public ControladorRegistro(frmRegistroUsuario l){ 
        vista = l;
        vista.btnCrearRegistro.addActionListener(this);
        vista.btnRegresarRegistro.addActionListener(this);
        ProcesosFrmRegistros.PresentacionUsuario(l);
        centrarVentana(l);
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
        if (e.getSource() == vista.btnCrearRegistro) {
            reg = ProcesosFrmRegistros.LeerDatosRegistro(vista);
            
            // Verificar si la contraseña y la confirmación de la contraseña coinciden
            if (!reg.getContraseña().equals(vista.txtContraseñaRegistro2.getText())) {
                JOptionPane.showMessageDialog(null, "La confirmación de contraseña no coincide", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si las contraseñas no coinciden
            }
            
            crud = new DAO_Usuarios();
            if (crud.registrarUsuario(reg.getUsuario(), reg.getContraseña())) {
                // Si el registro es exitoso, crea una instancia de la ventana principal y hazla visible
                Main.prin = new frmPrincipal();
                // Pasar el nombre del usuario al controlador del menú
                Main.controlMenu = new ControladorMenu(Main.prin, reg.getUsuario());
                Main.prin.setVisible(true); // Mostramos la ventana principal
                
                // Obtener el ID del usuario recién registrado
                int idUsuario = crud.obtenerIdUsuario(reg.getUsuario());
                
                // Crear una instancia del formulario de categorías y su controlador
                Main.cat = new frmCategorias();
                Main.controlcat = new ControladorFrmCategorias(Main.cat, idUsuario); // Pasar el ID del usuario
                Main.prin.jdpnContenedor.add(Main.cat);          
                CentrarForma.CPanel(Main.prin.jdpnContenedor, Main.cat, 2);
                
                // Luego, oculta la ventana de registro de usuarios
                vista.setVisible(false);
            } else {
                // Si hay un error en el registro, muestra un mensaje de error al usuario
                JOptionPane.showMessageDialog(null, "Error al registrar usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    
        if (e.getSource() == vista.btnRegresarRegistro) {
            // Si se presiona el botón "Regresar", crea una nueva instancia de la ventana de inicio de sesión y su controlador
            Main.lg = new frmLogin();
            Main.controllg = new ControladorLogin(Main.lg);
            vista.setVisible(false);
        }
    }
}


