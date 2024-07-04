package Controlador;

import Vista.frmRegistrarGastoExtra;
import DAO.DAO_Categorias;
import DAO.DAO_RegistrosGastos;
import DAO.DAO_Usuarios;
import Principal.Main;
import Procesos.ProcesosFrmRegistrarGastoExtra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ControladorRegistrarGastoExtra implements ActionListener {
    private frmRegistrarGastoExtra vista;
    private DAO_RegistrosGastos daoGastos;
    private DAO_Categorias daoCategorias;
    private String nombreUsuario;
    private int idUsuario; // Variable para almacenar el ID del usuario

    public ControladorRegistrarGastoExtra(frmRegistrarGastoExtra vista, String nombreUsuario) {
        this.vista = vista;
        this.nombreUsuario = nombreUsuario;
        this.daoGastos = new DAO_RegistrosGastos();
        this.daoCategorias = new DAO_Categorias();

        // Obtener el ID del usuario al inicializar el controlador
        this.idUsuario = obtenerIdUsuario(nombreUsuario);

        vista.btnRegistrarGasto.addActionListener(this);
        ProcesosFrmRegistrarGastoExtra.Presentacion(vista);
        cargarCategorias();
    }

    public void cargarCategorias() {
        // Cargar las categorías en el ComboBox desde la base de datos
        java.util.List<String> categorias = daoCategorias.obtenerCategoriasPorUsuario(idUsuario);
        for (String categoria : categorias) {
            vista.cbxCategoria.addItem(categoria);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnRegistrarGasto) {
            registrarGasto();
        }
    }

private void registrarGasto() {
    String categoria = (String) vista.cbxCategoria.getSelectedItem();
    String nombreProducto = vista.txtNombreProducto.getText();
    int cantidad = Integer.parseInt(vista.txtCantidad.getText()); // Parsear la cantidad como entero
    double precioUnitario = Double.parseDouble(vista.txtPrecio.getText());

    // Obtener la fecha actual
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String fecha = formatter.format(new Date());

    // Registrar el gasto en la base de datos
    int categoriaId = obtenerIdCategoria(categoria);
    daoGastos.registrarGasto(cantidad, precioUnitario, fecha, categoriaId, idUsuario, nombreProducto);

    // Actualizar la tabla en frmResumen
    DefaultTableModel model = (DefaultTableModel) Main.res.jtblResumen.getModel();
    model.addRow(new Object[]{fecha, categoria, nombreProducto, cantidad, precioUnitario});

    // Limpiar campos después de registrar
    vista.txtNombreProducto.setText("");
    vista.txtCantidad.setText("");
    vista.txtPrecio.setText("");
    vista.cbxCategoria.setSelectedIndex(0);
}


    // Método para obtener el ID de la categoría
    private int obtenerIdCategoria(String categoria) {
        return daoCategorias.obtenerIdCategoriaPorNombre(categoria, idUsuario);
    }

    // Método para obtener el ID del usuario
    private int obtenerIdUsuario(String nombreUsuario) {
        DAO_Usuarios daoUsuarios = new DAO_Usuarios();
        return daoUsuarios.obtenerIdUsuario(nombreUsuario);
    }
}





