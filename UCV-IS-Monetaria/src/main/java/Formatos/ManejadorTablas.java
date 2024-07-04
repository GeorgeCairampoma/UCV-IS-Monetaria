
package Formatos;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
public class ManejadorTablas {
    
    //Metodo que especifica un ancho de las columnas de tabla    
    public static void AnchoColumnas(JTable t, int numcolumna,int ancho) {
        TableColumn column;
        column = t.getColumnModel().getColumn(numcolumna);
        column.setPreferredWidth(ancho);       
    }
    
    //metodo que justifica los datos de una columna
   public static void JustificarCelda(JTable t,int numcolumna){
       DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
       modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
       t.getColumnModel().getColumn(numcolumna).setCellRenderer(modelocentrar);       
   }
   
   //metodo que la da formato a la JTable del tabla Categorias
   public static void FormatoTablaEstudiantes(JTable tabla){
       AnchoColumnas(tabla,0,40);
       JustificarCelda(tabla,0);
       AnchoColumnas(tabla,1,40);
       JustificarCelda(tabla,1);
       AnchoColumnas(tabla,2,150);
       JustificarCelda(tabla,2);
       AnchoColumnas(tabla,3,200);  
       JustificarCelda(tabla,3);
       AnchoColumnas(tabla,4,150);  
       JustificarCelda(tabla,4);
       AnchoColumnas(tabla,5,200);  
       JustificarCelda(tabla,5);
       AnchoColumnas(tabla,6,250);
       JustificarCelda(tabla,6);
       AnchoColumnas(tabla,7,200);
       JustificarCelda(tabla,7);
       AnchoColumnas(tabla,8,180);
       JustificarCelda(tabla,8);   
   }
   
   //metodo que da formato al JTable de la tabla Productos
   public static void FormatoTablaDocentes(JTable tabla){
       AnchoColumnas(tabla,0,80);
       JustificarCelda(tabla,0);
       AnchoColumnas(tabla,1,80);
       JustificarCelda(tabla,1);
       AnchoColumnas(tabla,2,200);
       JustificarCelda(tabla,2);
       AnchoColumnas(tabla,3,250);  
       JustificarCelda(tabla,3);
       AnchoColumnas(tabla,4,250);  
       JustificarCelda(tabla,4);
       AnchoColumnas(tabla,5,250);  
       JustificarCelda(tabla,5);
   }
   
      public static void FormatoTablaSalon(JTable tabla){
       AnchoColumnas(tabla,0,80);
       JustificarCelda(tabla,0);
       AnchoColumnas(tabla,1,80);
       JustificarCelda(tabla,1);
       AnchoColumnas(tabla,2,200);
       JustificarCelda(tabla,2);
       AnchoColumnas(tabla,3,250);  
       JustificarCelda(tabla,3);
   }
      
public static void FormatoTablaHorarios(JTable tabla) {
    // Ancho de columnas
    AnchoColumnas(tabla, 0, 80);  // ID Horario
    JustificarCelda(tabla, 0);
    AnchoColumnas(tabla, 1, 150);  // Curso
    JustificarCelda(tabla, 1);
    AnchoColumnas(tabla, 2, 180);  // Estudiante
    JustificarCelda(tabla, 2);
    AnchoColumnas(tabla, 3, 150);  // Estudiante
    JustificarCelda(tabla, 3);
    AnchoColumnas(tabla, 4, 150);  // Docente
    JustificarCelda(tabla, 4);
    AnchoColumnas(tabla, 5, 80);  // Salón
    JustificarCelda(tabla, 5);
    AnchoColumnas(tabla, 6, 100);  // Día
    JustificarCelda(tabla, 6);
    AnchoColumnas(tabla, 7, 120);  // Hora Inicio
    JustificarCelda(tabla, 7);
}

public static void FormatoTablaMatricula(JTable tabla) {
    // Ancho de columnas
    AnchoColumnas(tabla, 0, 80);  // Código Estudiante
    JustificarCelda(tabla, 0);
    AnchoColumnas(tabla, 1, 150);  // Nombre Estudiante
    JustificarCelda(tabla, 1);
    AnchoColumnas(tabla, 2, 100);  // DNI Estudiante
    JustificarCelda(tabla, 2);
    AnchoColumnas(tabla, 3, 120);  // Fecha Nacimiento
    JustificarCelda(tabla, 3);
    AnchoColumnas(tabla, 4, 100);  // Grado Académico
    JustificarCelda(tabla, 4);
    AnchoColumnas(tabla, 5, 80);  // Sexo Estudiante
    JustificarCelda(tabla, 5);
    AnchoColumnas(tabla, 6, 150);  // Correo Estudiante
    JustificarCelda(tabla, 6);
    AnchoColumnas(tabla, 7, 100);  // Celular Estudiante
    JustificarCelda(tabla, 7);
    AnchoColumnas(tabla, 8, 100);  // Aula
    JustificarCelda(tabla, 8);
    AnchoColumnas(tabla, 9, 100);  // Año de Matrícula
    JustificarCelda(tabla, 9);
}
       
public static void FormatoBusquedaDocente(JTable tabla) {
    AnchoColumnas(tabla, 0, 30);  // Código Estudiante
    JustificarCelda(tabla, 0);
    AnchoColumnas(tabla, 1, 250);  // Nombre Estudiante
    JustificarCelda(tabla, 1);
    AnchoColumnas(tabla, 2, 80);  // DNI Estudiante
    JustificarCelda(tabla, 2);
    AnchoColumnas(tabla, 3, 80);  // Fecha Nacimiento
    JustificarCelda(tabla, 3);
    AnchoColumnas(tabla, 4, 80);  // Grado Académico
    JustificarCelda(tabla, 4);
    AnchoColumnas(tabla, 5, 80);  // Sexo Estudiante
    JustificarCelda(tabla, 5);
    }

   
}
