package Controlador;

import Vista.*;
import DAO.*;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Formatos.*;
import Principal.Main;
import Procesos.*;

public class ControladorFrmCategorias implements ActionListener {
    private frmCategorias ventanaCategorias;
    private int idUsuario; // Variable para almacenar el ID del usuario

    public ControladorFrmCategorias(frmCategorias ventanaCategorias, int idUsuario) {
        this.idUsuario = idUsuario;
        this.ventanaCategorias = ventanaCategorias;
        // Asignar ActionListener a cada botón de categoría
        ventanaCategorias.btnComida.addActionListener(this);
        ventanaCategorias.btnEntretenimiento.addActionListener(this);
        ventanaCategorias.btnDeporte.addActionListener(this);
        ventanaCategorias.btnCamping.addActionListener(this);
        ventanaCategorias.btnMuebles.addActionListener(this);
        ventanaCategorias.btnSalud.addActionListener(this);
        ventanaCategorias.btnEducacion.addActionListener(this);
        ventanaCategorias.btnAhorro.addActionListener(this);
        ventanaCategorias.btnCuidadoP.addActionListener(this);
        ventanaCategorias.btnDeudas.addActionListener(this);
        ventanaCategorias.btnCerrar.addActionListener(this);
        ProcesosFrmCategorias.Presentacion(ventanaCategorias); // Pasar la instancia de ventanaCategorias
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Verificar cuál botón se ha presionado y realizar la acción correspondiente
        if (e.getSource() == ventanaCategorias.btnComida) {
            gestionarCategoria("Comida");
        } else if (e.getSource() == ventanaCategorias.btnEntretenimiento) {
            gestionarCategoria("Entretenimiento");
        } else if (e.getSource() == ventanaCategorias.btnDeporte) {
            gestionarCategoria("Deporte");
        } else if (e.getSource() == ventanaCategorias.btnCamping) {
            gestionarCategoria("Camping");
        } else if (e.getSource() == ventanaCategorias.btnMuebles) {
            gestionarCategoria("Muebles");
        } else if (e.getSource() == ventanaCategorias.btnSalud) {
            gestionarCategoria("Salud");
        } else if (e.getSource() == ventanaCategorias.btnEducacion) {
            gestionarCategoria("Educacion");
        } else if (e.getSource() == ventanaCategorias.btnAhorro) {
            gestionarCategoria("Ahorro");
        } else if (e.getSource() == ventanaCategorias.btnCuidadoP) {
            gestionarCategoria("Cuidado Personal");
        } else if (e.getSource() == ventanaCategorias.btnDeudas) {
            gestionarCategoria("Deudas");
        } else if (e.getSource() == ventanaCategorias.btnCerrar) {
            // Crear una instancia del formulario de ingresos y gastos
            Main.ing = new frmIngresosGastos();
            Main.controling = new ControladorFrmIngresosGastos(Main.ing, idUsuario);
            Main.prin.jdpnContenedor.add(Main.ing);          
            CentrarForma.CPanel(Main.prin.jdpnContenedor, Main.ing, 2);
            // Cerrar la ventana de registro de categorías
            ventanaCategorias.dispose();
        }
    }
    
    private void gestionarCategoria(String nombreCategoria) {
        String[] options = {"Agregar", "Eliminar"};
        int choice = JOptionPane.showOptionDialog(
                null, 
                "¿Desea agregar o eliminar la categoría " + nombreCategoria + "?", 
                "Gestionar Categoría", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                options, 
                options[0]);

        if (choice == 0) { // Agregar categoría
            agregarCategoria(nombreCategoria);
        } else if (choice == 1) { // Eliminar categoría
            eliminarCategoria(nombreCategoria);
        }
    }

    private void agregarCategoria(String nombreCategoria) {
        // Crear una instancia de DAO_Categorias
        DAO_Categorias daoCategorias = new DAO_Categorias();
        
        // Llamar al método del DAO para agregar la categoría a la base de datos
        boolean categoriaAgregada = daoCategorias.agregarCategoria(nombreCategoria, idUsuario);
        if (categoriaAgregada) {
            JOptionPane.showMessageDialog(null, "Categoría agregada correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar categoría");
        }
    }

    private void eliminarCategoria(String nombreCategoria) {
        // Crear una instancia de DAO_Categorias
        DAO_Categorias daoCategorias = new DAO_Categorias();
        // Obtener el ID de la categoría por su nombre y el ID del usuario
        int idCategoria = daoCategorias.obtenerIdCategoriaPorNombre(nombreCategoria, idUsuario);
        if (idCategoria != -1) {
            // Llamar al método del DAO para eliminar la categoría de la base de datos
            boolean categoriaEliminada = daoCategorias.eliminarCategoria(idCategoria, idUsuario);
            if (categoriaEliminada) {
                JOptionPane.showMessageDialog(null, "Categoría eliminada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar categoría");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Categoría no encontrada");
        }
    }
}