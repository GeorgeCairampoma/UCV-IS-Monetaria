package Controlador;

import Vista.frmReporte;
import DAO.DAO_RegistrosGastos;
import Modelo.RegistroGasto;
import Procesos.ProcesosFrmReporte;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JOptionPane;
import DAO.DAO_Usuarios;
import Principal.Main;

public class ControladorReporte implements ActionListener {
    private frmReporte vista;
    private DAO_RegistrosGastos daoGastos;
    private DAO_Usuarios daoUsuarios;
    private int idUsuario; // Variable para almacenar el ID del usuario

    public ControladorReporte(frmReporte vista, int idUsuario) {
        this.vista = vista;
        this.idUsuario = idUsuario;
        this.daoGastos = new DAO_RegistrosGastos();
        this.daoUsuarios = new DAO_Usuarios();
        // Añadir listeners a los botones
        vista.btnExportarPDF.addActionListener(this);
        ProcesosFrmReporte.Presentacion(vista);
        // Generar el reporte al inicializar
        generarReporte();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnExportarPDF) {
            exportarPDF();
        }
    }

private void generarReporte() {
    // Obtener el nombre de usuario asociado al idUsuario
    String nombreUsuario = daoUsuarios.obtenerNombreUsuario(idUsuario);

    // Obtener el sueldo ahorro del controladorResumen usando el nombre de usuario
    double sueldoAhorro = Main.controlres.sueldoAhorro(nombreUsuario);

    // Obtener los gastos del usuario
    List<RegistroGasto> gastos = daoGastos.obtenerGastosPorUsuario(idUsuario);

    // Construir el reporte en formato de texto
    StringBuilder reporte = new StringBuilder();
    reporte.append("Bienvenido ").append(nombreUsuario).append(", este es el resumen de tus gastos que has realizado:\n");
    reporte.append("----------------------------------------\n");

    double totalGastos = 0; // Variable para almacenar el total de gastos

    int numeroReporte = 1; // Inicializar el contador de reportes

    for (RegistroGasto gasto : gastos) {
        reporte.append("REPORTE N° ").append(numeroReporte++).append("\n"); // Agregar el número de reporte
        reporte.append("Fecha: ").append(gasto.getFecha()).append("\n");
        reporte.append("Categoría: ").append(gasto.getCategoria()).append("\n");
        reporte.append("Nombre del Producto: ").append(gasto.getNombreProducto()).append("\n");
        reporte.append("Cantidad: ").append((int) gasto.getCantidad()).append("\n"); // Formatear la cantidad como entero
        reporte.append("Precio Unitario: ").append(gasto.getPrecioUnitario()).append("\n");
        
        // Calcular el total individual de cada reporte
        double totalIndividual = gasto.getCantidad() * gasto.getPrecioUnitario();
        reporte.append("Total: ").append(totalIndividual).append("\n");
        
        // Sumar al total de gastos
        totalGastos += totalIndividual;

        reporte.append("----------------------------------------\n");
    }

    // Restar el total de gastos del sueldoAhorro
    double saldoFinal = sueldoAhorro - totalGastos;

    // Agregar el total de gastos y el saldo final al reporte
    reporte.append("Total de Gastos: ").append(totalGastos).append("\n");
    reporte.append("Saldo Final: ").append(saldoFinal).append("\n");

    // Mostrar el reporte en la caja de texto
    vista.jtxaReporte.setText(reporte.toString());
}





public void abrirReportePDF(String nombreUsuario) {
    // Obtener los gastos del usuario
    List<RegistroGasto> gastos = daoGastos.obtenerGastosPorUsuario(idUsuario);

    // Construir el reporte en formato de texto
    StringBuilder reporte = new StringBuilder();
    reporte.append("Bienvenido: ").append(nombreUsuario).append(", este es el resumen de tus gastos que has realizado:\n");
    reporte.append("----------------------------------------\n");
    int numeroReporte = 1; // Inicializar el contador de reportes
    
    double totalGastos = 0; // Variable para almacenar el total de gastos

    for (RegistroGasto gasto : gastos) {
        reporte.append("REPORTE N° ").append(numeroReporte++).append("\n"); // Agregar el número de reporte
        reporte.append("Fecha: ").append(gasto.getFecha()).append("\n");
        reporte.append("Categoría: ").append(gasto.getCategoria()).append("\n");
        reporte.append("Nombre del Producto: ").append(gasto.getNombreProducto()).append("\n");
        reporte.append("Cantidad: ").append((int) gasto.getCantidad()).append("\n"); // Formatear la cantidad como entero
        reporte.append("Precio Unitario: ").append(gasto.getPrecioUnitario()).append("\n");

        // Calcular el precio total del producto y agregarlo al reporte
        double precioTotal = gasto.getCantidad() * gasto.getPrecioUnitario();
        reporte.append("Precio Total: ").append(precioTotal).append("\n");
        
        // Sumar al total de gastos
        totalGastos += precioTotal;

        reporte.append("----------------------------------------\n");
    }

    // Crear una instancia de frmReporte
    frmReporte reporteVista = new frmReporte();
    
    // Crear una instancia de ControladorReporte y pasarle la vista y el ID del usuario
    ControladorReporte controladorReporte = new ControladorReporte(reporteVista, idUsuario);

    // Establecer el texto del reporte en la caja de texto de frmReporte
    reporteVista.jtxaReporte.setText(reporte.toString());

    // Mostrar la vista de reporte
    reporteVista.setVisible(true);
}



private void exportarPDF() {
    // Obtener el directorio del escritorio del usuario
    String desktopPath = System.getProperty("user.home") + "/Desktop/";

    // Nombre del archivo PDF
    String fileName = "reporte.pdf";

    // Ruta completa del archivo PDF
    String filePath = desktopPath + fileName;

    Document document = new Document();
    try {
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        // Obtener el contenido del reporte desde la caja de texto
        String reporteTexto = vista.jtxaReporte.getText();

        // Añadir el contenido al PDF
        document.add(new Paragraph(reporteTexto));
        document.close();

        JOptionPane.showMessageDialog(null, "PDF generado exitosamente!");
    } catch (FileNotFoundException | DocumentException e) {
        e.printStackTrace();
    }
}

}